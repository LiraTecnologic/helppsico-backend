package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.PacienteExistenteException;
import com.liratech.helppsico.application.exceptions.PacienteNaoEncontradoException;
import com.liratech.helppsico.application.gateways.PacienteGateway;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.validators.PacienteValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class PacienteUseCaseTest {

    @Mock
    private PacienteGateway gateway;

    @Mock
    private EnderecoUseCase enderecoUseCase;

    @InjectMocks
    private PacienteUseCase useCase;

    @Test
    void testeCadastrarPaciente() {
        Paciente novoPaciente = PacienteBuilder.criarPaciente();

        Mockito.when(gateway.consultarPorEmail(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(enderecoUseCase.cadastrar(Mockito.any())).thenReturn(novoPaciente.getEndereco());
        Mockito.when(gateway.salvar(Mockito.any())).thenReturn(novoPaciente);

        Paciente pacienteCadastrado = useCase.cadastrar(novoPaciente);
        PacienteValidator.validaPacienteDomain(novoPaciente, pacienteCadastrado);
    }

    @Test
    void testeErroCadastrarPaciente() {
        Paciente novoPaciente = PacienteBuilder.criarPaciente();

        Mockito.when(gateway.consultarPorEmail(Mockito.any())).thenReturn(Optional.of(PacienteBuilder.criarPaciente()));
        PacienteExistenteException exception = Assertions.assertThrows(PacienteExistenteException.class, () -> useCase.cadastrar(novoPaciente));
        Assertions.assertEquals(MENSAGEM_PACIENTE_JA_EXISTE, exception.getMessage());
        Mockito.verify(gateway, Mockito.times(1)).consultarPorEmail(novoPaciente.getEmail());
    }

    @Test
    void testeConsultarPacientePorId() {
        Paciente pacienteCriado = PacienteBuilder.criarPaciente();

        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(pacienteCriado));
        Paciente paciente = useCase.consultarPorId(pacienteCriado.getId());
        PacienteValidator.validaPacienteDomain(pacienteCriado, paciente);
    }

    @Test
    void testeErroConsultaPacientePorId() {
        UUID id = PacienteBuilder.criarPaciente().getId();

        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.empty());
        PacienteNaoEncontradoException exception = Assertions.assertThrows(PacienteNaoEncontradoException.class, () -> useCase.consultarPorId(id));
        Assertions.assertEquals(MENSAGEM_PACIENTE_NAO_ENCONTRADO, exception.getMessage());
        Mockito.verify(gateway, Mockito.times(1)).consultarPorId(id);
    }
}