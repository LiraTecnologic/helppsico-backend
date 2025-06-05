package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.domain.documento.Atestado;
import com.liratech.helppsico.domain.documento.Declaracao;
import com.liratech.helppsico.entrypoint.dto.documento.AtestadoDto;
import com.liratech.helppsico.entrypoint.dto.documento.DeclaracaoDto;
import com.liratech.helppsico.validators.DocumentoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
public class DeclaracaoMapperTest {

    private DeclaracaoMapper mapper;
    private Declaracao domainTest;
    private DeclaracaoDto dtoTest;

    @Test
    void testeDeclaracaoDomainParaDto(){
        domainTest = DocumentoBuilder.criarDeclaracao();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaDeclaracaoMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeDeclaracaoDtoParaDomain(){
        dtoTest = DocumentoBuilder.criarDeclaracaoDto();
        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaDeclaracaoMapperEntry(domainTest, dtoTest);
    }
}
