package com.liratech.helppsico.entrypoint.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PacienteControllerTest {

    private final PacienteMapper mapper;

    @Mock
    private PacienteUseCase useCase;

    @InjectMocks
    private PacienteController controller;

    @Test
    @DisplayName("Teste de sucesso para cadastro de um novo Paciente.")
    void testeCadastrarPaciente() {

    }

    @Test
    @DisplayName("Teste de sucesso para consulta de Paciente por ID.")
    void testeConsultaPacientePorId() {

    }
}