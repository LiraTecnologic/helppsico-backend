package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.validators.PacienteValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
class PacienteMapperInfraTest {

    private PacienteMapperInfra mapper;
    private Paciente domainTest;
    private PacienteEntity entityTest;

    @Test
    void testePacienteDomainParaEntity() {
        domainTest = PacienteBuilder.criarPaciente();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        PacienteValidator.validaPacienteMapperInfra(domainTest, entityTest);
    }

    @Test
    void testePacienteDtoParaDomain() {
        entityTest = PacienteBuilder.criarPacienteEntity();
        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        PacienteValidator.validaPacienteMapperInfra(domainTest, entityTest);
    }
}