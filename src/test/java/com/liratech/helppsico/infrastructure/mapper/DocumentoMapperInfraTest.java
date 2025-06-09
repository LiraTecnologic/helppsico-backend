package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.domain.documento.*;
import com.liratech.helppsico.entrypoint.dto.documento.*;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.*;
import com.liratech.helppsico.validators.DocumentoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DocumentoMapperInfraTest {

    @Mock

    @InjectMocks
    private DocumentoMapperInfra mapper;
    private Documento domainTest;
    private DocumentoEntity entityTest;

    @Test
    void testeDocumentoInstanciaDeAtestadoParaEntity(){
        domainTest = DocumentoBuilder.criarAtestado();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertInstanceOf(AtestadoEntity.class, entityTest);
        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaAtestadoMapperInfra((Atestado) domainTest, (AtestadoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeDeclaracaoParaEntity(){
        domainTest = DocumentoBuilder.criarDeclaracao();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertInstanceOf(DeclaracaoEntity.class, entityTest);
        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaDeclaracaoMapperInfra((Declaracao) domainTest, (DeclaracaoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeRelatorioPsicologicoParaEntity(){
        domainTest = DocumentoBuilder.criarRelatorioPsicologico();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertInstanceOf(RelatorioPsicologicoEntity.class, entityTest);
        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaRelatorioPsicologicoMapperInfra((RelatorioPsicologico) domainTest, (RelatorioPsicologicoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeLaudoPsicologicoParaEntity(){
        domainTest = DocumentoBuilder.criarLaudoPsicologico();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertInstanceOf(LaudoPsicologicoEntity.class, entityTest);
        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaLaudoPsicologicoMapperInfra((LaudoPsicologico) domainTest, (LaudoPsicologicoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeParecerPsicologicoParaEntity(){
        domainTest = DocumentoBuilder.criarParecerPsicologico();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertInstanceOf(ParecerPsicologicoEntity.class, entityTest);
        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaParecerPsicologicoMapperInfra((ParecerPsicologico) domainTest, (ParecerPsicologicoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeAtestadoParaDomain(){
        entityTest = DocumentoBuilder.criarAtestadoEntity();
        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertInstanceOf(Atestado.class, domainTest);
        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaAtestadoMapperInfra((Atestado) domainTest, (AtestadoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeDeclaracaoParaDomain(){
        entityTest = DocumentoBuilder.criarDeclaracaoEntity();
        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertInstanceOf(Declaracao.class, domainTest);
        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaDeclaracaoMapperInfra((Declaracao) domainTest, (DeclaracaoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeRelatorioPsicologicoParaDomain(){
        entityTest = DocumentoBuilder.criarRelatorioPsicologicoEntity();
        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertInstanceOf(RelatorioPsicologico.class, domainTest);
        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaRelatorioPsicologicoMapperInfra((RelatorioPsicologico) domainTest, (RelatorioPsicologicoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeLaudoPsicologicoParaDomain(){
        entityTest = DocumentoBuilder.criarLaudoPsicologicoEntiy();
        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertInstanceOf(LaudoPsicologico.class, domainTest);
        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaLaudoPsicologicoMapperInfra((LaudoPsicologico) domainTest, (LaudoPsicologicoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeParecerPsicologicoParaDomain(){
        entityTest = DocumentoBuilder.criarParecerPsicologicoEntity();
        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertInstanceOf(ParecerPsicologico.class, domainTest);
        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaParecerPsicologicoMapperInfra((ParecerPsicologico) domainTest, (ParecerPsicologicoEntity) entityTest);
    }
}
