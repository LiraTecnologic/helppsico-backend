package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.domain.documento.*;
import com.liratech.helppsico.entrypoint.dto.documento.*;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.AtestadoEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.DeclaracaoEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.DocumentoEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.RelatorioPsicologicoEntity;
import com.liratech.helppsico.validators.DocumentoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DocumentoMapperInfraTest {
    private DocumentoMapperInfra mapper;
    private Documento domainTest;
    private DocumentoEntity entityTest;

    @Test
    void testeDocumentoInstanciaDeAtestadoParaEntity(){
        domainTest = DocumentoBuilder.criarAtestado();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertTrue(entityTest instanceof AtestadoEntity);
        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaAtestadoMapperInfra((Atestado) domainTest, (AtestadoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeDeclaracaoParaEntity(){
        domainTest = DocumentoBuilder.criarDeclaracao();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertTrue(entityTest instanceof DeclaracaoEntity);
        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaDeclaracaoMapperInfra((Declaracao) domainTest, (DeclaracaoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeRelatorioPsicologicoParaEntity(){
        domainTest = DocumentoBuilder.criarRelatorioPsicologico();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertTrue(entityTest instanceof RelatorioPsicologicoEntity);
        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaRelatorioPsicologicoMapperInfra((RelatorioPsicologico) domainTest, (RelatorioPsicologicoDto) dtoTest);
    }

    @Test
    void testeDocumentoInstanciaDeLaudoPsicologicoParaEntity(){
        domainTest = DocumentoBuilder.criarLaudoPsicologico();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertTrue(dtoTest instanceof LaudoPsicologicoDto);
        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaLaudoPsicologicoMapperEntry((LaudoPsicologico) domainTest, (LaudoPsicologicoDto) dtoTest);
    }

    @Test
    void testeDocumentoInstanciaDeParecerPsicologicoParaEntity(){
        domainTest = DocumentoBuilder.criarParecerPsicologico();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertTrue(dtoTest instanceof ParecerPsicologicoDto);
        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaParecerPsicologicoMapperEntry((ParecerPsicologico) domainTest, (ParecerPsicologicoDto) dtoTest);
    }

    @Test
    void testeDocumentoInstanciaDeAtestadoParaDomain(){
        domainTest = DocumentoBuilder.criarAtestado();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertTrue(dtoTest instanceof AtestadoDto);
        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaAtestadoMapperEntry((Atestado) domainTest, (AtestadoDto) dtoTest);
    }

    @Test
    void testeDocumentoInstanciaDeDeclaracaoParaDomain(){
        domainTest = DocumentoBuilder.criarDeclaracao();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertTrue(dtoTest instanceof DeclaracaoDto);
        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaDeclaracaoMapperEntry((Declaracao) domainTest, (DeclaracaoDto) dtoTest);
    }

    @Test
    void testeDocumentoInstanciaDeRelatorioPsicologicoParaDomain(){
        domainTest = DocumentoBuilder.criarRelatorioPsicologico();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertTrue(dtoTest instanceof RelatorioPsicologicoDto);
        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaRelatorioPsicologicoMapperEntry((RelatorioPsicologico) domainTest, (RelatorioPsicologicoDto) dtoTest);
    }

    @Test
    void testeDocumentoInstanciaDeLaudoPsicologicoParaDomain(){
        domainTest = DocumentoBuilder.criarLaudoPsicologico();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertTrue(dtoTest instanceof LaudoPsicologicoDto);
        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaLaudoPsicologicoMapperEntry((LaudoPsicologico) domainTest, (LaudoPsicologicoDto) dtoTest);
    }

    @Test
    void testeDocumentoInstanciaDeParecerPsicologicoParaDomain(){
        domainTest = DocumentoBuilder.criarParecerPsicologico();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertTrue(dtoTest instanceof ParecerPsicologicoDto);
        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaParecerPsicologicoMapperEntry((ParecerPsicologico) domainTest, (ParecerPsicologicoDto) dtoTest);
    }
}
