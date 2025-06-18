package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import com.liratech.helppsico.validators.PsicologoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PsicologoMapperInfraTest {

    @Mock
    private EnderecoMapperInfra enderecoMapperInfra;

    @InjectMocks
    private PsicologoMapperInfraImp mapper;
    private Psicologo domainTest;
    private PsicologoEntity entityTest;

    @Test
    void testePsicologoDomainParaEntity() {
        domainTest = PsicologoBuilder.criarPsicologo();

        Mockito.when(enderecoMapperInfra.paraEntity(Mockito.any())).thenReturn(EnderecoBuilder.criarEnderecoEntity());

        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        PsicologoValidator.validaPsicologoMapperInfra(domainTest, entityTest);
    }

    @Test
    void testePsicologoEntityParaDomain() {
        entityTest = PsicologoBuilder.criarPsicologoEntity();

        Mockito.when(enderecoMapperInfra.paraDomain(Mockito.any())).thenReturn(EnderecoBuilder.criarEndereco());

        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        PsicologoValidator.validaPsicologoMapperInfra(domainTest, entityTest);
    }
}