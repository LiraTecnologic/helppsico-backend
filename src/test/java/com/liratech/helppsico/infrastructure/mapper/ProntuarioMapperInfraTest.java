package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.ProntuarioBuilder;
import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.entrypoint.dto.psicologo.ProntuarioDto;
import com.liratech.helppsico.infrastructure.repositories.entities.ProntuarioEntity;
import com.liratech.helppsico.validators.ProntuarioValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
class ProntuarioMapperInfraTest {

    private ProntuarioMapperInfra mapper;
    private Prontuario domainTest;
    private ProntuarioEntity entityTest;

    @Test
    void testeProntuarioDomainParaEntity() {
        domainTest = ProntuarioBuilder.criarProntuario();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        ProntuarioValidator.validaProntuarioMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeProntuarioEntityParaDomain() {
        entityTest = ProntuarioBuilder.criarProntuarioEntity();
        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        ProntuarioValidator.validaProntuarioMapperInfra(domainTest, entityTest);
    }
}