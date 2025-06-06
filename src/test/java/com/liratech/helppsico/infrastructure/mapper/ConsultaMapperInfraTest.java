package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.ConsultaBuilder;
import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.entrypoint.dto.consulta.ConsultaDto;
import com.liratech.helppsico.entrypoint.mapper.ConsultaMapper;
import com.liratech.helppsico.infrastructure.repositories.entities.ConsultaEntity;
import com.liratech.helppsico.validators.ConsultaValidator;
import com.liratech.helppsico.validators.EnderecoValidator;
import com.liratech.helppsico.validators.PacienteValidator;
import com.liratech.helppsico.validators.PsicologoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

@AllArgsConstructor
class ConsultaMapperInfraTest {

    private ConsultaMapperInfra mapper;
    private ConsultaEntity entityTest;
    private Consulta domainTest;

    @Test
    void testeConsultaEntityParaDomain() {
        entityTest = ConsultaBuilder.criarConsultaEntity();
        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        ConsultaValidator.validaConsultaMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeConsultaDomainParaEntity() {
        domainTest = ConsultaBuilder.criarConsulta();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        ConsultaValidator.validaConsultaMapperInfra(domainTest, entityTest);
    }
}