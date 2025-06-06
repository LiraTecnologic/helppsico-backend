package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.domain.documento.ParecerPsicologico;
import com.liratech.helppsico.entrypoint.dto.documento.ParecerPsicologicoDto;
import com.liratech.helppsico.entrypoint.mapper.ParecerPsicologicoMapper;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.ParecerPsicologicoEntity;
import com.liratech.helppsico.validators.DocumentoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
public class ParecerPsicologicoMapperInfraTest {

    private ParecerPsicologicoMapperInfra mapper;
    private ParecerPsicologico domainTest;
    private ParecerPsicologicoEntity entityTest;

    @Test
    void testeParecerPsicologicoDomainParaEntity(){
        domainTest = DocumentoBuilder.criarParecerPsicologico();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaParecerPsicologicoMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeParecerPsicologicoEntityParaDomain(){
        entityTest = DocumentoBuilder.criarParecerPsicologicoEntity();
        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaParecerPsicologicoMapperInfra(domainTest, entityTest);
    }
}
