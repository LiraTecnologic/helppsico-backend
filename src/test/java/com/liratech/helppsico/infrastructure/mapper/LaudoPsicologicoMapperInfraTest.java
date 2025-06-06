package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.domain.documento.LaudoPsicologico;
import com.liratech.helppsico.entrypoint.dto.documento.LaudoPsicologicoDto;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.LaudoPsicologicoEntity;
import com.liratech.helppsico.validators.DocumentoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
public class LaudoPsicologicoMapperInfraTest {

    private LaudoPsicologicoMapperInfra mapper;
    private LaudoPsicologico domainTest;
    private LaudoPsicologicoEntity entityTest;

    @Test
    void testeLaudoPsicologicoDomainParaEntity(){
        domainTest = DocumentoBuilder.criarLaudoPsicologico();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaLaudoPsicologicoMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeLaudoPsicologicoEntityParaDomain(){
        entityTest = DocumentoBuilder.criarLaudoPsicologicoEntiy();
        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaLaudoPsicologicoMapperInfra(domainTest, entityTest);
    }
}
