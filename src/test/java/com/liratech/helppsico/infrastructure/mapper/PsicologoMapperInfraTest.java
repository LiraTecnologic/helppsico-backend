package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import com.liratech.helppsico.validators.PsicologoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
class PsicologoMapperInfraTest {

    private PsicologoMapperInfra mapper;
    private Psicologo domainTest;
    private PsicologoEntity entityTest;

    @Test
    void testePsicologoDomainParaEntity() {
        domainTest = PsicologoBuilder.criarPsicologo();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        PsicologoValidator.validaPsicologoMapperInfra(domainTest, entityTest);
    }

    @Test
    void testePsicologoEntityParaDomain() {
        entityTest = PsicologoBuilder.criarPsicologoEntity();
        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        PsicologoValidator.validaPsicologoMapperInfra(domainTest, entityTest);
    }
}