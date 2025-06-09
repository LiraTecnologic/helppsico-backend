package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.avaliacao.AvaliacaoInvalidaException;
import com.liratech.helppsico.application.exceptions.avaliacao.AvaliacaoJaCadastradaException;
import com.liratech.helppsico.application.exceptions.avaliacao.AvaliacaoNaoEncontradaException;
import com.liratech.helppsico.application.gateways.AvaliacaoGateway;
import com.liratech.helppsico.application.gateways.PsicologoGateway;
import com.liratech.helppsico.builders.AvaliacaoBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.builders.VinculoBuilder;
import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.validators.AvaliacaoValidator;
import lombok.AllArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

import static com.liratech.helppsico.application.usecases.AvaliacaoUseCase.*;

@ExtendWith(MockitoExtension.class)
@AllArgsConstructor
public class AvaliacaoUseCaseTest {

    @Mock
    private PacienteUseCase pacienteUseCase;

    @Mock
    private PsicologoUseCase psicologoUseCase;

    @Mock
    private VinculoUseCase vinculoUseCase;

    @Mock
    private AvaliacaoGateway gateway;

    @Captor
    private ArgumentCaptor<Avaliacao> captor;

    @InjectMocks
    private AvaliacaoUseCase useCase;

    private Avaliacao avaliacaoTeste;
    private Page<Avaliacao> avaliacaoPageTeste;
    private Psicologo psicologoTeste;
    private Paciente pacienteTeste;
    private Vinculo vinculoTeste;

    @BeforeEach
    void inicializar() {
        avaliacaoTeste = AvaliacaoBuilder.criarAvaliacao();

        avaliacaoPageTeste = AvaliacaoBuilder.criarPageDeAvaliacoes();
        avaliacaoPageTeste.forEach(avaliacao -> avaliacao.setPsicologo(psicologoTeste));

        psicologoTeste = PsicologoBuilder.criarPsicologo();
        psicologoTeste.setId(avaliacaoTeste.getPsicologo().getId());

        pacienteTeste = PacienteBuilder.criarPaciente();
        pacienteTeste.setId(avaliacaoTeste.getPaciente().getId());

        vinculoTeste = VinculoBuilder.criarVinculo();
        vinculoTeste.setPaciente(avaliacaoTeste.getPaciente());
        vinculoTeste.setPsicologo(avaliacaoTeste.getPsicologo());
    }

    @Test
    void testeCadastroDaAvaliacao(){
        avaliacaoTeste.setId(null);

        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(psicologoTeste);
        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(pacienteTeste);
        Mockito.when(vinculoUseCase.consultarAtivoPorPaciente(Mockito.any())).thenReturn(vinculoTeste);
        Mockito.when(gateway.consultarPorPacientePsicologo(Mockito.any(), Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(avaliacaoTeste);

        Avaliacao avaliacao = useCase.avaliar(avaliacaoTeste);

        Avaliacao avaliacaoCapturada = captor.getValue();

        Assertions.assertEquals(avaliacao.getId(), avaliacaoCapturada.getId());
        AvaliacaoValidator.validaAvaliacaoDomain(avaliacao, avaliacaoCapturada);
    }

    @Test
    void testeExceptionAvaliacaoInvalida() {
        avaliacaoTeste.setId(null);
        vinculoTeste.setPsicologo(PsicologoBuilder.criarPsicologo());

        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(psicologoTeste);
        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(pacienteTeste);
        Mockito.when(vinculoUseCase.consultarAtivoPorPaciente(Mockito.any())).thenReturn(vinculoTeste);
        Mockito.when(gateway.consultarPorPacientePsicologo(Mockito.any(), Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(avaliacaoTeste);

        AvaliacaoInvalidaException exception = Assertions.assertThrows(
                AvaliacaoInvalidaException.class,
                () -> useCase.avaliar(avaliacaoTeste)
        );

        Assertions.assertEquals(MENSAGEM_PSICOLOGO_PACIENTE_NAO_VINCULADOS, exception.getMessage());
    }

    @Test
    void testeExceptionAvaliacaoJaCadastrada(){
        Avaliacao avaliacaoTeste = AvaliacaoBuilder.criarAvaliacao();

        Mockito.when(gateway.consultarPorPacientePsicologo(Mockito.any(), Mockito.any())).thenReturn(Optional.of(avaliacaoTeste));

        AvaliacaoJaCadastradaException exception = Assertions
                .assertThrows(AvaliacaoJaCadastradaException.class,
                        () -> useCase.avaliar(avaliacaoTeste));

        Assertions.assertEquals(MENSAGEM_AVALIACAO_JA_CADASTRADA, exception.getMessage());
        Mockito.verify(gateway, Mockito.times(1)).consultarPorPacientePsicologo(avaliacaoTeste.getPaciente().getId(), avaliacaoTeste.getPsicologo().getId());
    }

    @Test
    void testeBuscarAvaliacaoPorId(){
        UUID idAvaliacao = avaliacaoTeste.getId();

        Mockito.when(gateway.buscarPorId(Mockito.any())).thenReturn(Optional.of(avaliacaoTeste));

        Avaliacao avaliacaoResposta = useCase.buscarPorId(idAvaliacao);

        AvaliacaoValidator.validaAvaliacaoDomain(avaliacaoTeste, avaliacaoResposta);
        Assertions.assertEquals(avaliacaoTeste.getId(), avaliacaoResposta.getId());
    }

    @Test
    void testeExceptionAvaliacaoNaoEncontrada(){
        UUID idAvaliacao = AvaliacaoBuilder.criarAvaliacao().getId();

        Mockito.when(gateway.buscarPorId(Mockito.any())).thenReturn(Optional.empty());

        AvaliacaoNaoEncontradaException exception = Assertions
                .assertThrows(AvaliacaoNaoEncontradaException.class, () -> useCase.buscarPorId(idAvaliacao));
        Assertions.assertEquals(MENSAGEM_AVALIACAO_NAO_ENCONTRADA, exception.getMessage());

        Mockito.verify(gateway, Mockito.times(1)).buscarPorId(idAvaliacao);
    }

    @Test
    void testeListarAvaliacoesPorPsicologo(){
        UUID idPsicologo = psicologoTeste.getId();

        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(psicologoTeste);
        Mockito.when(gateway.listarPorPsicologo(Mockito.any(), Mockito.any())).thenReturn(avaliacaoPageTeste);

        Page<Avaliacao> avaliacaoResposta = useCase.listarPorPsicologo(idPsicologo, PageRequest.of(0,10));

        for (int i = 0; i < avaliacaoResposta.getNumberOfElements(); i++) {
            AvaliacaoValidator.validaAvaliacaoDomain(avaliacaoPageTeste.getContent().get(i), avaliacaoResposta.getContent().get(i));
            Assertions.assertEquals(avaliacaoPageTeste.getContent().get(i).getId(), avaliacaoResposta.getContent().get(i).getId());
        }
    }

    @Test
    void testeDeletarAvaliacao(){
        UUID idAvaliacao = avaliacaoTeste.getId();

        Mockito.when(useCase.buscarPorId(Mockito.any())).thenReturn(avaliacaoTeste);
        Mockito.doNothing().when(gateway).deletar(Mockito.any());

        useCase.deletar(idAvaliacao);

        Mockito.verify(useCase, Mockito.times(1)).buscarPorId(idAvaliacao);
        Mockito.verify(gateway, Mockito.times(1)).deletar(idAvaliacao);
    }

//    @Test
//    void testeConsultarAvaliacaoPorPacientePsicologo(){
//        Avaliacao avaliacaoTeste = AvaliacaoBuilder.criarAvaliacao();
//
//        Mockito.when(gateway.consultarPorPacientePsicologo(Mockito.any(), Mockito.any())).thenReturn(Optional.of(avaliacaoTeste));
//
//        Optional<Avaliacao> avaliacaoResposta = useCase.consultarPorPacientePsicologo(avaliacaoTeste.getPaciente().getId(), avaliacaoTeste.getPsicologo().getId());
//
//        avaliacaoResposta.ifPresent(avaliacao -> {
//                    Assertions.assertEquals(avaliacaoTeste.getId(), avaliacao.getId());
//                    AvaliacaoValidator.validaAvaliacaoDomain(avaliacaoTeste, avaliacao);
//                }
//        );
//    }
}
