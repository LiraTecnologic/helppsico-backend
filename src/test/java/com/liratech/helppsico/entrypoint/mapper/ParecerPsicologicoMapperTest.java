package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.domain.documento.Documento;
import com.liratech.helppsico.domain.documento.ParecerPsicologico;
import com.liratech.helppsico.entrypoint.dto.documento.ParecerPsicologicoDto;
import com.liratech.helppsico.validators.DocumentoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
public class ParecerPsicologicoMapperTest {

    private ParecerPsicologicoMapper mapper;
    private ParecerPsicologico domainTest;
    private ParecerPsicologicoDto dtoTest;

    @Test
    void testeParecerPsicologicoDomainParaDto(){
        domainTest = DocumentoBuilder.criarParecerPsicologico();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaParecerPsicologicoMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeParecerPsicologicoDtoParaDomain(){
        dtoTest = DocumentoBuilder.criarParecerPsicologicoDto();
        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaParecerPsicologicoMapperEntry(domainTest, dtoTest);
    }
}
