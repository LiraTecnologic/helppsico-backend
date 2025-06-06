package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.ValidacaoCrpBuilder;
import com.liratech.helppsico.domain.ValidacaoCrp;
import com.liratech.helppsico.infrastructure.repositories.entities.ValidacaoCrpEntity;
import com.liratech.helppsico.validators.ValidacaoCrpValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
public class ValidacaoCrpMapperInfraTest {

    private ValidacaoCrpMapperInfra mapper;
    private ValidacaoCrp domainTest;
    private ValidacaoCrpEntity entityTest;

    @Test
    void testeValidacaoCrpDomainParaEntity(){
        domainTest = ValidacaoCrpBuilder.criarValidacaoCrp();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        ValidacaoCrpValidator.validaValidacaoCrpMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeValidacaoCrpDtoParaDomain(){
        entityTest = ValidacaoCrpBuilder.criarValidacaoCrpEntity();
        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        ValidacaoCrpValidator.validaValidacaoCrpMapperInfra(domainTest, entityTest);
    }
}
