package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.domain.documento.RelatorioPsicologico;
import com.liratech.helppsico.entrypoint.dto.documento.RelatorioPsicologicoDto;
import com.liratech.helppsico.entrypoint.mapper.RelatorioPsicologicoMapper;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.RelatorioPsicologicoEntity;
import com.liratech.helppsico.validators.DocumentoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
public class RelatorioPsicologicoMapperInfraTest {

    private RelatorioPsicologicoMapperInfra mapper;
    private RelatorioPsicologico domainTest;
    private RelatorioPsicologicoEntity entityTest;

    @Test
    void testeRelatorioPsicologicoDomainParaEntity(){
        domainTest = DocumentoBuilder.criarRelatorioPsicologico();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaRelatorioPsicologicoMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeRelatorioPsicologicoEntityParaDomain(){
        entityTest = DocumentoBuilder.criarRelatorioPsicologicoEntity();
        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaRelatorioPsicologicoMapperInfra(domainTest, entityTest);
    }
}
