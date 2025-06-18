package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.builders.ValidacaoCrpBuilder;
import com.liratech.helppsico.domain.ValidacaoCrp;
import com.liratech.helppsico.infrastructure.repositories.entities.ValidacaoCrpEntity;
import com.liratech.helppsico.validators.ValidacaoCrpValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ValidacaoCrpMapperInfraTest {

    @Mock
    private PsicologoMapperInfra psicologoMapperInfra;

    @InjectMocks
    private ValidacaoCrpMapperInfraImpl mapper;
    private ValidacaoCrp domainTest;
    private ValidacaoCrpEntity entityTest;

    @Test
    void testeValidacaoCrpDomainParaEntity(){
        domainTest = ValidacaoCrpBuilder.criarValidacaoCrp();

        Mockito.when(psicologoMapperInfra.paraEntity(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoEntity());

        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        ValidacaoCrpValidator.validaValidacaoCrpMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeValidacaoCrpDtoParaDomain(){
        entityTest = ValidacaoCrpBuilder.criarValidacaoCrpEntity();

        Mockito.when(psicologoMapperInfra.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());

        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        ValidacaoCrpValidator.validaValidacaoCrpMapperInfra(domainTest, entityTest);
    }
}
