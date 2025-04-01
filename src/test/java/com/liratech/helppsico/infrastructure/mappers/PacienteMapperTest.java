package com.liratech.helppsico.infrastructure.mappers;

import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.validators.PacienteValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class PacienteMapperTest {

    private final PacienteMapper pacienteMapper = Mappers.getMapper(PacienteMapper.class);

    @Test
    @DisplayName("Caso de sucesso na transformação de Paciente Domain para Entity")
    void testeTransformacaoPacienteDomainParaEntity() {
        Paciente paciente = PacienteBuilder.criarPaciente();
        PacienteEntity pacienteEntity = pacienteMapper.paraEntity(paciente);

        Assertions.assertNotNull(paciente);
        PacienteValidator.validaPacienteDomainParaEntity(paciente, pacienteEntity);
    }

    @Test
    @DisplayName("Caso de sucesso na transformação de Paciente Entity para Domain")
    void testeTransformacaoPacienteEntityParaDomain() {
        PacienteEntity pacienteEntity = PacienteBuilder.criarPacienteEntity();
        Paciente paciente = pacienteMapper.paraDomain(pacienteEntity);

        Assertions.assertNotNull(paciente);
        PacienteValidator.validaPacienteEntityParaDomain(pacienteEntity, paciente);
    }
}