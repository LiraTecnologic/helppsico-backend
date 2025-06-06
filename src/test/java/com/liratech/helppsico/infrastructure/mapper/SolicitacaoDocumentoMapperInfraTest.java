package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.SolicitacaoDocumentoBuilder;
import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import com.liratech.helppsico.entrypoint.dto.documento.SolicitacaoDocumentoDto;
import com.liratech.helppsico.entrypoint.mapper.SolicitacaoDocumentoMapper;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.SolicitacaoDocumentoEntity;
import com.liratech.helppsico.validators.SolicitacaoDocumentoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
public class SolicitacaoDocumentoMapperInfraTest {

    private SolicitacaoDocumentoMapperInfra mapper;
    private SolicitacaoDocumento domainTest;
    private SolicitacaoDocumentoEntity entityTest;

    @Test
    void testeSolicitacaoDocumentoDomainParaEntity(){
        domainTest = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumento();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        SolicitacaoDocumentoValidator.validaSolicitacaoDocumentoMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeSolicitacaoDocumentoEntityParaDomain(){
        entityTest = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumentoEntity();
        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        SolicitacaoDocumentoValidator.validaSolicitacaoDocumentoMapperInfra(domainTest, entityTest);
    }
}
