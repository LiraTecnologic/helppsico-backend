package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.domain.documento.*;
import com.liratech.helppsico.entrypoint.dto.documento.*;
import com.liratech.helppsico.validators.DocumentoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DocumentoMapperTest {

    @Mock
    private AtestadoMapper atestadoMapper;

    @Mock
    private DeclaracaoMapper declaracaoMapper;

    @Mock
    private RelatorioPsicologicoMapper relatorioPsicologicoMapper;

    @Mock
    private LaudoPsicologicoMapper laudoPsicologicoMapper;

    @Mock
    private ParecerPsicologicoMapper parecerPsicologicoMapper;

    @InjectMocks
    private DocumentoMapper mapper;

    private Documento domainTest;
    private DocumentoDto dtoTest;

    @Test
    void testeDocumentoInstanciaDeAtestadoParaDto(){
        domainTest = DocumentoBuilder.criarAtestado();

        Mockito.when(atestadoMapper.paraDto(Mockito.any())).thenReturn(DocumentoBuilder.criarAtestadoDto());

        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertInstanceOf(AtestadoDto.class, dtoTest);
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaAtestadoMapperEntry((Atestado) domainTest, (AtestadoDto) dtoTest);
    }

    @Test
    void testeDocumentoInstanciaDeDeclaracaoParaDto(){
        domainTest = DocumentoBuilder.criarDeclaracao();

        Mockito.when(declaracaoMapper.paraDto(Mockito.any())).thenReturn(DocumentoBuilder.criarDeclaracaoDto());

        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertInstanceOf(DeclaracaoDto.class, dtoTest);
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaDeclaracaoMapperEntry((Declaracao) domainTest, (DeclaracaoDto) dtoTest);
    }

    @Test
    void testeDocumentoInstanciaDeRelatorioPsicologicoParaDto(){
        domainTest = DocumentoBuilder.criarRelatorioPsicologico();

        Mockito.when(relatorioPsicologicoMapper.paraDto(Mockito.any())).thenReturn(DocumentoBuilder.criarRelatorioPsicologicoDto());

        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertInstanceOf(RelatorioPsicologicoDto.class, dtoTest);
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaRelatorioPsicologicoMapperEntry((RelatorioPsicologico) domainTest, (RelatorioPsicologicoDto) dtoTest);
    }

    @Test
    void testeDocumentoInstanciaDeLaudoPsicologicoParaDto(){
        domainTest = DocumentoBuilder.criarLaudoPsicologico();

        Mockito.when(laudoPsicologicoMapper.paraDto(Mockito.any())).thenReturn(DocumentoBuilder.criarLaudoPsicologicoDto());

        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertInstanceOf(LaudoPsicologicoDto.class, dtoTest);
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaLaudoPsicologicoMapperEntry((LaudoPsicologico) domainTest, (LaudoPsicologicoDto) dtoTest);
    }

    @Test
    void testeDocumentoInstanciaDeParecerPsicologicoParaDto(){
        domainTest = DocumentoBuilder.criarParecerPsicologico();

        Mockito.when(parecerPsicologicoMapper.paraDto(Mockito.any())).thenReturn(DocumentoBuilder.criarParecerPsicologicoDto());

        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertInstanceOf(ParecerPsicologicoDto.class, dtoTest);
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaParecerPsicologicoMapperEntry((ParecerPsicologico) domainTest, (ParecerPsicologicoDto) dtoTest);
    }
}
