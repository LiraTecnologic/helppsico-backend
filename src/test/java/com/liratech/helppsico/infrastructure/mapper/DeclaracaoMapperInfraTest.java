package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.domain.documento.Declaracao;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.DeclaracaoEntity;
import com.liratech.helppsico.validators.DocumentoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
public class DeclaracaoMapperInfraTest {

    private DeclaracaoMapperInfra mapper;
    private Declaracao domainTest;
    private DeclaracaoEntity entityTest;

    @Test
    void testeDeclaracaoDomainParaEntity(){
        domainTest = DocumentoBuilder.criarDeclaracao();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaDeclaracaoMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeDeclaracaoEntityParaDomain(){
        entityTest = DocumentoBuilder.criarDeclaracaoEntity();
        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaDeclaracaoMapperInfra(domainTest, entityTest);
    }
}
