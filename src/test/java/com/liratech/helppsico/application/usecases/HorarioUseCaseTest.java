package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.horario.HorarioNaoEncontradoException;
import com.liratech.helppsico.application.gateways.HorarioGateway;
import com.liratech.helppsico.builders.HorarioBuilder;
import com.liratech.helppsico.domain.Horario;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.validators.HorarioValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class HorarioUseCaseTest {

    @Mock
    private PsicologoUseCase psicologoUseCase;

    @Mock
    private HorarioGateway gateway;

    @Captor
    private ArgumentCaptor<Horario> captor;

    @InjectMocks
    private HorarioUseCase useCase;

    private Horario horarioTeste;
    private List<Horario> horarioListTeste;
    private Psicologo psicologoTeste;

    @BeforeEach
    void inicializar() {
        horarioTeste = HorarioBuilder.criarHorario();
        psicologoTeste = horarioTeste.getPsicologo();
    }

    @Test
    void testeCadastrarHorario() {
        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(psicologoTeste);
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(horarioTeste);

        useCase.cadastrar(horarioTeste);
        Horario resultado = captor.getValue();

        Assertions.assertEquals(horarioTeste.getId(), resultado.getId());
        HorarioValidator.validaHorarioDomain(horarioTeste, resultado);
        Mockito.verify(gateway).salvar(horarioTeste);
    }

    @Test
    void testeAlterarHorario() {
        Horario horarioNovo = HorarioBuilder.criarHorario();
        horarioNovo.setDisponivel(false);

        Mockito.when(gateway.consultarPorId(horarioTeste.getId())).thenReturn(Optional.of(horarioTeste));
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(horarioNovo);

        useCase.alterar(horarioNovo, horarioTeste.getId());
        Horario resultado = captor.getValue();

        Assertions.assertNotNull(resultado);
        HorarioValidator.validaHorarioDomain(horarioTeste, resultado);
        Mockito.verify(gateway).salvar(Mockito.any());
    }

    @Test
    void testeConsultarHorarioPorId() {
        Mockito.when(gateway.consultarPorId(horarioTeste.getId())).thenReturn(Optional.of(horarioTeste));

        Horario resultado = useCase.consultarPorId(horarioTeste.getId());

        Assertions.assertEquals(horarioTeste.getId(), resultado.getId());
        HorarioValidator.validaHorarioDomain(horarioTeste, resultado);
        Mockito.verify(gateway).consultarPorId(horarioTeste.getId());
    }

    @Test
    void testeExceptionConsultarHorarioPorId() {
        UUID idHorario = UUID.randomUUID();

        Mockito.when(gateway.consultarPorId(idHorario)).thenReturn(Optional.empty());

        HorarioNaoEncontradoException exception = Assertions.assertThrows(
                HorarioNaoEncontradoException.class,
                () -> useCase.consultarPorId(idHorario)
        );

        Assertions.assertEquals(HorarioUseCase.ERRO_HORARIO_NAO_ENCONTRADO, exception.getMessage());
        Mockito.verify(gateway).consultarPorId(idHorario);
    }

    @Test
    void testeListarHorariosPorPsicologo() {
        Mockito.when(psicologoUseCase.consultarPorId(psicologoTeste.getId())).thenReturn(psicologoTeste);
        Mockito.when(gateway.listarPorPsicologo(psicologoTeste.getId())).thenReturn(horarioListTeste);

        List<Horario> resultado = useCase.listarPorPsicologo(psicologoTeste.getId());

        resultado.forEach(horario -> {
            Assertions.assertEquals(horario.getId(), horarioTeste.getId());
            HorarioValidator.validaHorarioDomain(horario, horarioTeste);
        });
        Mockito.verify(gateway).listarPorPsicologo(psicologoTeste.getId());
    }

    @Test
    void testeDeletarHorario(){
        UUID idHorario = horarioTeste.getId();

        Mockito.when(useCase.consultarPorId(Mockito.any())).thenReturn(horarioTeste);
        Mockito.doNothing().when(gateway).deletar(Mockito.any());

        useCase.deletar(idHorario);

        Mockito.verify(useCase, Mockito.times(1)).consultarPorId(idHorario);
        Mockito.verify(gateway, Mockito.times(1)).deletar(idHorario);
    }
}
