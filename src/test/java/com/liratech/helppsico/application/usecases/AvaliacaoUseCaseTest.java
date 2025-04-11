package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.avaliacao.AvaliacaoNaoEncontradaException;
import com.liratech.helppsico.application.gateways.AvaliacaoGateway;
import com.liratech.helppsico.application.gateways.PsicologoGateway;
import com.liratech.helppsico.builders.AvaliacaoBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.validators.AvaliacaoValidator;
import jdk.incubator.vector.VectorOperators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

import static com.liratech.helppsico.application.usecases.AvaliacaoUseCase.MENSAGEM_AVALIACAO_NAO_ENCONTRADA;

@ExtendWith(MockitoExtension.class)
public class AvaliacaoUseCaseTest {

    @Mock
    private PacienteUseCase pacienteUseCase;

    @Mock
    private PsicologoUseCase psicologoUseCase;

    @Mock
    private AvaliacaoGateway gateway;

    @InjectMocks
    private AvaliacaoUseCase useCase;

    @Test
    void testeCadastroDaAvaliacao(){
        Avaliacao avaliacaoTeste = AvaliacaoBuilder.criarAvaliacao();

        Psicologo psicologoTeste = PsicologoBuilder.criarPsicologo();
        psicologoTeste.setId(avaliacaoTeste.getPsicologo().getId());

        Paciente pacienteTeste = PacienteBuilder.criarPaciente();
        pacienteTeste.setId(avaliacaoTeste.getPaciente().getId());

        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(psicologoTeste);
        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(pacienteTeste);
        //Logica de repetição
        Mockito.when(gateway.salvar(Mockito.any())).thenReturn(avaliacaoTeste);

        Avaliacao avaliacao = useCase.avaliar(avaliacaoTeste);
        AvaliacaoValidator.validaAvaliacaoDomain(avaliacaoTeste, avaliacao);
    }

    @Test
    void testeExceptionAvaliacaoJaCadastrada(){}

    @Test
    void testeListarAvaliacoesPorPsicologo(){
        Psicologo psicologoTeste = PsicologoBuilder.criarPsicologo();
        UUID idPsicologo = psicologoTeste.getId();
        Page<Avaliacao> avaliacaoPageTeste = AvaliacaoBuilder.criarPageDeAvaliacoes();
        avaliacaoPageTeste.forEach(avaliacao -> avaliacao.setPsicologo(psicologoTeste));

        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(psicologoTeste);
        Mockito.when(gateway.listarPorPsicologo(Mockito.any())).thenReturn(avaliacaoPageTeste);

        Page<Avaliacao> avaliacaoResposta = gateway.listarPorPsicologo(idPsicologo);
        for (int i = 0; i < avaliacaoResposta.getNumberOfElements(); i++) {
            AvaliacaoValidator.validaAvaliacaoDomain(avaliacaoPageTeste.getContent().get(i), avaliacaoResposta.getContent().get(i));
        }
    }

    @Test
    void testeBuscarAvaliacaoPorId(){
        Avaliacao avaliacao = AvaliacaoBuilder.criarAvaliacao();
        UUID idAvaliacao = avaliacao.getId();

        Mockito.when(gateway.buscarPorId(Mockito.any())).thenReturn(Optional.of(avaliacao));

        Avaliacao avaliacaoResposta = useCase.buscarPorId(idAvaliacao);
        AvaliacaoValidator.validaAvaliacaoDomain(avaliacao, avaliacaoResposta);
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
    void testeDeletarAvaliacao(){
        Avaliacao avaliacao = AvaliacaoBuilder.criarAvaliacao();
        UUID idAvaliacao = avaliacao.getId();

        Mockito.when(useCase.buscarPorId(Mockito.any())).thenReturn(avaliacao);
        Mockito.doNothing().when(gateway).deletar(Mockito.any());

        useCase.deletar(idAvaliacao);

        Mockito.verify(useCase, Mockito.times(1)).buscarPorId(idAvaliacao);
        Mockito.verify(gateway, Mockito.times(1)).deletar(idAvaliacao);
    }
}
