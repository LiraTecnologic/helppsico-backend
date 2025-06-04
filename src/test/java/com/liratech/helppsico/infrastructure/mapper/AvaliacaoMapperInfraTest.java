package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.AvaliacaoBuilder;
import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.infrastructure.repositories.entities.AvaliacaoEntity;
import com.liratech.helppsico.validators.AvaliacaoValidator;
import com.liratech.helppsico.validators.PacienteValidator;
import com.liratech.helppsico.validators.PsicologoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

@AllArgsConstructor
class AvaliacaoMapperInfraTest {

    private AvaliacaoMapperInfra mapper;
    private Avaliacao domainTest;
    private AvaliacaoEntity entityTest;

    @Test
    void testeAvaliacaoDomainParaEntity() {
        domainTest = AvaliacaoBuilder.criarAvaliacao();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        AvaliacaoValidator.validaAvaliacaoMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeAvaliacaoEntityParaDomain() {
        entityTest = AvaliacaoBuilder.criarAvaliacaoEntity();
        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        AvaliacaoValidator.validaAvaliacaoMapperInfra(domainTest, entityTest);
    }
}