package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.VinculoBuilder;
import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.infrastructure.repositories.entities.VinculoEntity;
import com.liratech.helppsico.validators.VinculoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
class VinculoMapperInfraTest {

    private VinculoMapperInfra mapper;
    private Vinculo domainTest;
    private VinculoEntity entityTest;

    @Test
    void testeVinculoDomainParaEntity() {
        domainTest = VinculoBuilder.criarVinculo();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        VinculoValidator.validaVinculoMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeVinculoEntityParaDomain() {
        entityTest = VinculoBuilder.criarVinculoEntity();
        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        VinculoValidator.validaVinculoMapperInfra(domainTest, entityTest);
    }
}