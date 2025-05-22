package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.vinculo.VinculoInvalidoException;
import com.liratech.helppsico.application.gateways.VinculoGateway;
import com.liratech.helppsico.builders.VinculoBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.StatusVinculo;
import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.validators.VinculoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;
import java.util.UUID;

import static com.liratech.helppsico.application.usecases.VinculoUseCase.ERRO_VINCULO_INVALIDO;

@ExtendWith(MockitoExtension.class)
@AllArgsConstructor
public class VinculoUseCaseTest {

    @Mock
    private PsicologoUseCase psicologoUseCase;

    @Mock
    private PacienteUseCase pacienteUseCase;

    @Mock
    private VinculoGateway gateway;

    @InjectMocks
    private VinculoUseCase useCase;

    @Captor
    private ArgumentCaptor<Vinculo> captor;

    private Vinculo vinculoTeste;
    private Vinculo vinculoAtivoTeste;
    private Page<Vinculo> vinculoPageTeste;
    private Psicologo psicologoTeste;
    private Paciente pacienteTeste;

    @BeforeEach
    void inicializarAtributo(){
        vinculoTeste = VinculoBuilder.criarVinculo();
        pacienteTeste = vinculoTeste.getPaciente();
        psicologoTeste = vinculoTeste.getPsicologo();

        vinculoAtivoTeste = vinculoTeste;
        vinculoAtivoTeste.setStatus(StatusVinculo.ATIVO);

        vinculoPageTeste = VinculoBuilder.criarPageDeVinculos();
    }

    @Test
    void testeCriarSolicitacaoVinculo(){
        vinculoTeste.setId(null);

        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(pacienteTeste);
        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(psicologoTeste);
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(vinculoTeste);

        useCase.criarSolicitacaoVinculo(vinculoTeste);

        Vinculo vinculoResultado = captor.getValue();

        Assertions.assertNotNull(vinculoResultado.getId());
        VinculoValidator.validaVinculoDomain(vinculoTeste, vinculoResultado);
    }

    @Test
    void testeVinculoInvalidoException(){
        vinculoTeste.setStatus(StatusVinculo.ATIVO);

        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(pacienteTeste);
        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(psicologoTeste);
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(vinculoTeste);

        VinculoInvalidoException exception = Assertions.assertThrows(
                VinculoInvalidoException.class,
                () -> useCase.criarSolicitacaoVinculo(vinculoTeste)
        );

        Assertions.assertEquals(ERRO_VINCULO_INVALIDO, exception.getMessage());
    }

    @Test
    void testeAceitarSolicitacao(){
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(vinculoTeste));
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(vinculoAtivoTeste);

        useCase.aceitarSolicitacao(vinculoTeste.getId());
        Vinculo vinculoSalvo = captor.getValue();

        Assertions.assertNotNull(vinculoSalvo.getId());
        VinculoValidator.validaVinculoDomain(vinculoAtivoTeste, vinculoSalvo);
    }

    @Test
    void testeDesvincular(){
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(vinculoTeste));
        Mockito.doNothing().when(gateway).deletar(Mockito.any());

        useCase.desvincular(vinculoTeste.getId());
        Mockito.verify(gateway).deletar(Mockito.any());
    }

    @Test
    void testeListarSolicitacoesPorIdPsicologo(){
        Mockito.when(gateway.listarPorIdPsicologo(Mockito.any(), Mockito.any())).thenReturn(vinculoPageTeste);

        Page<Vinculo> vinculoPageResultado = useCase.listarPorIdPsicologo(psicologoTeste.getId(), PageRequest.of(0, 10));

        vinculoPageResultado.forEach(vinculo -> {
            Assertions.assertNotNull(vinculo.getId());
            VinculoValidator.validaVinculoDomain(vinculoTeste, vinculo);
        });
    }

    @Test
    void testeConsultarVinculoPorId(){
        Mockito.when(gateway.consultarPorIdPaciente(Mockito.any())).thenReturn(Optional.of(vinculoTeste));

        Vinculo vinculoResultado = useCase.consultarPorIdPaciente(pacienteTeste.getId());

        Assertions.assertNotNull(vinculoResultado.getId());
        VinculoValidator.validaVinculoDomain(vinculoTeste, vinculoResultado);
    }
}
