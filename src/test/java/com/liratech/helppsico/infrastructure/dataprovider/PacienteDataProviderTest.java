package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.PacienteMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.PacienteRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.validators.PacienteValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PacienteDataProviderTest {

    @Mock
    private PacienteMapperInfra mapper;

    @Mock
    private PacienteRepository repository;

    @InjectMocks
    private PacienteDataProvider dataProvider;

    private Paciente pacienteTeste;
    private PacienteEntity pacienteEntityTeste;

    @BeforeEach
    void inicializaar() {
        pacienteTeste = PacienteBuilder.criarPaciente();
        pacienteEntityTeste = PacienteBuilder.criarPacienteEntity();
    }

    @Test
    void testeSalvarPaciente() {
        Mockito.when(mapper.paraEntity(Mockito.any())).thenReturn(pacienteEntityTeste);
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(pacienteTeste);
        Mockito.when(repository.save(Mockito.any())).thenReturn(pacienteEntityTeste);

        Paciente pacienteResult = dataProvider.salvar(pacienteTeste);

        PacienteValidator.validaPacienteDomain(pacienteResult, pacienteTeste);
        Assertions.assertNotNull(pacienteResult.getId());
    }

    @Test
    void testeExceptionSalvarPaciente() {
        Mockito.when(mapper.paraEntity(Mockito.any())).thenReturn(pacienteEntityTeste);
        Mockito.when(repository.save(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.salvar(PacienteBuilder.criarPaciente()));

        Assertions.assertEquals(PacienteDataProvider.MENSAGEM_ERRO_SALVAR, exception.getMessage());
    }

    @Test
    void testeConsultarPacientePorId() {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(pacienteEntityTeste));
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(pacienteTeste);

        Optional<Paciente> pacienteResult = dataProvider.consultarPorId(pacienteTeste.getId());

        pacienteResult.ifPresent(paciente -> {
            PacienteValidator.validaPacienteDomain(pacienteTeste, paciente);
            Assertions.assertEquals(pacienteTeste.getId(), paciente.getId());
        });
    }

    @Test
    void testeExceptionConsultarPacientePorId() {
        Mockito.when(repository.findById(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.consultarPorId(PacienteBuilder.criarPaciente().getId()));

        Assertions.assertEquals(PacienteDataProvider.MENSAGEM_ERRO_CONSULTAR_POR_ID, exception.getMessage());
    }

    @Test
    void testeConsultaPacientePorEmail() {
        Mockito.when(repository.findByEmail(Mockito.any())).thenReturn(Optional.of(pacienteEntityTeste));
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(pacienteTeste);

        Optional<Paciente> pacienteResult = dataProvider.consultarPorEmail(pacienteTeste.getEmail());

        pacienteResult.ifPresent(paciente -> {
            PacienteValidator.validaPacienteDomain(pacienteTeste, paciente);
            Assertions.assertEquals(pacienteTeste.getId(), paciente.getId());
        });
    }

    @Test
    void testeExceptionConsultarPacientePorEmail() {
        Mockito.when(repository.findByEmail(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.consultarPorEmail(PacienteBuilder.criarPaciente().getEmail()));

        Assertions.assertEquals(PacienteDataProvider.MENSAGEM_ERRO_CONSULTAR_POR_EMAIL, exception.getMessage());
    }
}