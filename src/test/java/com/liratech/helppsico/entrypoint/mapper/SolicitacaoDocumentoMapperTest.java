package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.SolicitacaoDocumentoBuilder;
import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import com.liratech.helppsico.entrypoint.dto.documento.SolicitacaoDocumentoDto;
import com.liratech.helppsico.validators.SolicitacaoDocumentoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
public class SolicitacaoDocumentoMapperTest {

    private SolicitacaoDocumentoMapper mapper;
    private SolicitacaoDocumento domainTest;
    private SolicitacaoDocumentoDto dtoTest;

    @Test
    void testeSolicitacaoDocumentoDomainParaDto(){
        domainTest = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumento();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        SolicitacaoDocumentoValidator.validaSolicitacaoDocumentoMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeSolicitacaoDocumentoDtoParaDomain(){
        dtoTest = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumentoDto();
        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        SolicitacaoDocumentoValidator.validaSolicitacaoDocumentoMapperEntry(domainTest, dtoTest);
    }
}
