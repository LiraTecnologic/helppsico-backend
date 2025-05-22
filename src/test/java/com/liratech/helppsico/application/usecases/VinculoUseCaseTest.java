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
    private Psicologo psicologoTeste;
    private Paciente pacienteTeste;

    @BeforeEach
    void inicializarAtributo(){
        vinculoTeste = VinculoBuilder.criarVinculo();
        pacienteTeste = vinculoTeste.getPaciente();
        psicologoTeste = vinculoTeste.getPsicologo();
    }

    @Test
    void testeCriarSolicitacaoVinculo(){
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

    void testeAceitarSolicitacao(){

    }

}
