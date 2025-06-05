package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.domain.documento.*;
import com.liratech.helppsico.entrypoint.dto.documento.*;
import com.liratech.helppsico.validators.DocumentoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
public class DocumentoMapperTest {

    private DocumentoMapper mapper;
    private Documento domainTest;
    private DocumentoDto dtoTest;

    @Test
    void testeDocumentoInstanciaDeAtestadoParaDto(){
        domainTest = DocumentoBuilder.criarAtestado();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertInstanceOf(AtestadoDto.class, dtoTest);
        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaAtestadoMapperEntry((Atestado) domainTest, (AtestadoDto) dtoTest);
    }

    @Test
    void testeDocumentoInstanciaDeDeclaracaoParaDto(){
        domainTest = DocumentoBuilder.criarDeclaracao();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertInstanceOf(DeclaracaoDto.class, dtoTest);
        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaDeclaracaoMapperEntry((Declaracao) domainTest, (DeclaracaoDto) dtoTest);
    }

    @Test
    void testeDocumentoInstanciaDeRelatorioPsicologicoParaDto(){
        domainTest = DocumentoBuilder.criarRelatorioPsicologico();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertInstanceOf(RelatorioPsicologicoDto.class, dtoTest);
        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaRelatorioPsicologicoMapperEntry((RelatorioPsicologico) domainTest, (RelatorioPsicologicoDto) dtoTest);
    }

    @Test
    void testeDocumentoInstanciaDeLaudoPsicologicoParaDto(){
        domainTest = DocumentoBuilder.criarLaudoPsicologico();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertInstanceOf(LaudoPsicologicoDto.class, dtoTest);
        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaLaudoPsicologicoMapperEntry((LaudoPsicologico) domainTest, (LaudoPsicologicoDto) dtoTest);
    }

    @Test
    void testeDocumentoInstanciaDeParecerPsicologicoParaDto(){
        domainTest = DocumentoBuilder.criarParecerPsicologico();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertInstanceOf(ParecerPsicologicoDto.class, dtoTest);
        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaParecerPsicologicoMapperEntry((ParecerPsicologico) domainTest, (ParecerPsicologicoDto) dtoTest);
    }
}
