package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.domain.documento.Atestado;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.AtestadoEntity;
import com.liratech.helppsico.validators.DocumentoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
public class AtestadoMapperInfraTest {

    private AtestadoMapperInfra mapper;
    private Atestado domainTest;
    private AtestadoEntity entityTest;

    @Test
    void testeAtestadoEntityParaDomain(){
        domainTest = DocumentoBuilder.criarAtestado();
        entityTest = mapper.paraEntity(domainTest);

        DocumentoValidator.validaAtestadoMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeAtestadoDomainParaEntity(){
        entityTest = DocumentoBuilder.criarAtestadoEntity();
        domainTest = mapper.paraDomain(entityTest);

        DocumentoValidator.validaAtestadoMapperInfra(domainTest, entityTest);
    }
}
