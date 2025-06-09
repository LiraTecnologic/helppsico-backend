package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.domain.documento.*;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.*;
import com.liratech.helppsico.validators.DocumentoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DocumentoMapperInfraTest {

    @Mock
    private AtestadoMapperInfra atestadoMapper;

    @Mock
    private DeclaracaoMapperInfra declaracaoMapper;

    @Mock
    private RelatorioPsicologicoMapperInfra relatorioPsicologicoMapper;

    @Mock
    private LaudoPsicologicoMapperInfra laudoPsicologicoMapper;

    @Mock
    private ParecerPsicologicoMapperInfra parecerPsicologicoMapper;

    @InjectMocks
    private DocumentoMapperInfra mapper;

    private Documento domainTest;
    private DocumentoEntity entityTest;

    @Test
    void testeDocumentoInstanciaDeAtestadoParaEntity(){
        domainTest = DocumentoBuilder.criarAtestado();

        Mockito.when(atestadoMapper.paraEntity(Mockito.any())).thenReturn(DocumentoBuilder.criarAtestadoEntity());

        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertInstanceOf(AtestadoEntity.class, entityTest);
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaAtestadoMapperInfra((Atestado) domainTest, (AtestadoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeDeclaracaoParaEntity(){
        domainTest = DocumentoBuilder.criarDeclaracao();

        Mockito.when(declaracaoMapper.paraEntity(Mockito.any())).thenReturn(DocumentoBuilder.criarDeclaracaoEntity());

        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertInstanceOf(DeclaracaoEntity.class, entityTest);
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaDeclaracaoMapperInfra((Declaracao) domainTest, (DeclaracaoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeRelatorioPsicologicoParaEntity(){
        domainTest = DocumentoBuilder.criarRelatorioPsicologico();

        Mockito.when(relatorioPsicologicoMapper.paraEntity(Mockito.any())).thenReturn(DocumentoBuilder.criarRelatorioPsicologicoEntity());

        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertInstanceOf(RelatorioPsicologicoEntity.class, entityTest);
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaRelatorioPsicologicoMapperInfra((RelatorioPsicologico) domainTest, (RelatorioPsicologicoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeLaudoPsicologicoParaEntity(){
        domainTest = DocumentoBuilder.criarLaudoPsicologico();

        Mockito.when(laudoPsicologicoMapper.paraEntity(Mockito.any())).thenReturn(DocumentoBuilder.criarLaudoPsicologicoEntity());

        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertInstanceOf(LaudoPsicologicoEntity.class, entityTest);
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaLaudoPsicologicoMapperInfra((LaudoPsicologico) domainTest, (LaudoPsicologicoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeParecerPsicologicoParaEntity(){
        domainTest = DocumentoBuilder.criarParecerPsicologico();

        Mockito.when(parecerPsicologicoMapper.paraEntity(Mockito.any())).thenReturn(DocumentoBuilder.criarParecerPsicologicoEntity());

        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertInstanceOf(ParecerPsicologicoEntity.class, entityTest);
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaParecerPsicologicoMapperInfra((ParecerPsicologico) domainTest, (ParecerPsicologicoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeAtestadoParaDomain(){
        entityTest = DocumentoBuilder.criarAtestadoEntity();

        Mockito.when(atestadoMapper.paraDomain(Mockito.any())).thenReturn(DocumentoBuilder.criarAtestado());

        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertInstanceOf(Atestado.class, domainTest);
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaAtestadoMapperInfra((Atestado) domainTest, (AtestadoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeDeclaracaoParaDomain(){
        entityTest = DocumentoBuilder.criarDeclaracaoEntity();

        Mockito.when(declaracaoMapper.paraDomain(Mockito.any())).thenReturn(DocumentoBuilder.criarDeclaracao());

        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertInstanceOf(Declaracao.class, domainTest);
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaDeclaracaoMapperInfra((Declaracao) domainTest, (DeclaracaoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeRelatorioPsicologicoParaDomain(){
        entityTest = DocumentoBuilder.criarRelatorioPsicologicoEntity();

        Mockito.when(relatorioPsicologicoMapper.paraDomain(Mockito.any())).thenReturn(DocumentoBuilder.criarRelatorioPsicologico());

        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertInstanceOf(RelatorioPsicologico.class, domainTest);
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaRelatorioPsicologicoMapperInfra((RelatorioPsicologico) domainTest, (RelatorioPsicologicoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeLaudoPsicologicoParaDomain(){
        entityTest = DocumentoBuilder.criarLaudoPsicologicoEntity();

        Mockito.when(laudoPsicologicoMapper.paraDomain(Mockito.any())).thenReturn(DocumentoBuilder.criarLaudoPsicologico());

        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertInstanceOf(LaudoPsicologico.class, domainTest);
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaLaudoPsicologicoMapperInfra((LaudoPsicologico) domainTest, (LaudoPsicologicoEntity) entityTest);
    }

    @Test
    void testeDocumentoInstanciaDeParecerPsicologicoParaDomain(){
        entityTest = DocumentoBuilder.criarParecerPsicologicoEntity();

        Mockito.when(parecerPsicologicoMapper.paraDomain(Mockito.any())).thenReturn(DocumentoBuilder.criarParecerPsicologico());

        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertInstanceOf(ParecerPsicologico.class, domainTest);
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaParecerPsicologicoMapperInfra((ParecerPsicologico) domainTest, (ParecerPsicologicoEntity) entityTest);
    }
}
