package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.PacienteMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.PacienteRepository;
import com.liratech.helppsico.validators.PacienteValidator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class PacienteDataProviderTest {

    @Mock
    private PacienteRepository repository;

    @InjectMocks
    private PacienteDataProvider dataProvider;

    private final PacienteMapperInfra mapper;

    @Test
    void testaSalvarPsicologo() {
        Paciente pacienteTeste = PacienteBuilder.criarPaciente();

        Mockito.when(repository.save(Mockito.any())).thenReturn(mapper.paraEntity(pacienteTeste));
        pacienteTeste.setId(null);
        Paciente pacienteResult = dataProvider.salvar(pacienteTeste);
        PacienteValidator.validaPacienteDomain(pacienteResult, pacienteTeste);
        Assertions.assertNotNull(pacienteResult);
        Assertions.assertNotNull(pacienteResult.getId());
    }

    @Test
    void testaExceptionSalvarPsicologo() {
        Mockito.when(repository.save(Mockito.any())).thenThrow(RuntimeException.class);
        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.salvar(PacienteBuilder.criarPaciente()));
        Assertions.assertEquals(PacienteDataProvider.MENSAGEM_ERRO_SALVAR, exception.getMessage());
    }

    @Test
    void testaConsultarPsicologoPorId() {
        Paciente pacienteTeste = PacienteBuilder.criarPaciente();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(mapper.paraEntity(pacienteTeste)));
        Optional<Paciente> pacienteResult = dataProvider.consultarPorId(pacienteTeste.getId());
        pacienteResult.ifPresent(paciente -> {
            PacienteValidator.validaPacienteDomain(pacienteTeste, paciente);
            Assertions.assertEquals(pacienteTeste.getId(), paciente.getId());
        });
    }

    @Test
    void testaExceptionConsultarPsicologoPorId() {
        Mockito.when(repository.findById(Mockito.any())).thenThrow(RuntimeException.class);
        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.consultarPorId(PacienteBuilder.criarPaciente().getId()));
        Assertions.assertEquals(PacienteDataProvider.MENSAGEM_ERRO_CONSULTAR_POR_ID, exception.getMessage());
    }

    @Test
    void testaConsultaPsicologoPorEmail() {
        Paciente pacienteTeste = PacienteBuilder.criarPaciente();

        Mockito.when(repository.findByEmail(Mockito.any())).thenReturn(Optional.of(mapper.paraEntity(pacienteTeste)));
        Optional<Paciente> pacienteResult = dataProvider.consultarPorEmail(pacienteTeste.getEmail());
        pacienteResult.ifPresent(paciente -> {
            PacienteValidator.validaPacienteDomain(pacienteTeste, paciente);
            Assertions.assertEquals(pacienteTeste.getId(), paciente.getId());
        });
    }

    @Test
    void testaExceptionConsultarPsicologoPorEmail() {
        Mockito.when(repository.findByEmail(Mockito.any())).thenThrow(RuntimeException.class);
        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.consultarPorEmail(PacienteBuilder.criarPaciente().getEmail()));
        Assertions.assertEquals(PacienteDataProvider.MENSAGEM_ERRO_CONSULTAR_POR_EMAIL, exception.getMessage());
    }
}