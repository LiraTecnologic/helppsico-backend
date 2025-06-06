package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.domain.documento.RelatorioPsicologico;
import com.liratech.helppsico.entrypoint.dto.documento.RelatorioPsicologicoDto;
import com.liratech.helppsico.validators.DocumentoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
public class RelatorioPsicologicoMapperTest {

    private RelatorioPsicologicoMapper mapper;
    private RelatorioPsicologico domainTest;
    private RelatorioPsicologicoDto dtoTest;

    @Test
    void testeRelatorioPsicologicoDomainParaDto(){
        domainTest = DocumentoBuilder.criarRelatorioPsicologico();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaRelatorioPsicologicoMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeRelatorioPsicologicoDtoParaDomain(){
        dtoTest = DocumentoBuilder.criarRelatorioPsicologicoDto();
        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaRelatorioPsicologicoMapperEntry(domainTest, dtoTest);
    }
}
