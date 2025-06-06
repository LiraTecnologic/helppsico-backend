package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.domain.documento.LaudoPsicologico;
import com.liratech.helppsico.entrypoint.dto.documento.LaudoPsicologicoDto;
import com.liratech.helppsico.validators.DocumentoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
public class LaudoPsicologicoMapperTest {

    private LaudoPsicologicoMapper mapper;
    private LaudoPsicologico domainTest;
    private LaudoPsicologicoDto dtoTest;

    @Test
    void testeLaudoPsicologicoDomainParaDto(){
        domainTest = DocumentoBuilder.criarLaudoPsicologico();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaLaudoPsicologicoMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeLaudoPsicologicoDtoParaDomain(){
        dtoTest = DocumentoBuilder.criarLaudoPsicologicoDto();
        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaLaudoPsicologicoMapperEntry(domainTest, dtoTest);
    }
}
