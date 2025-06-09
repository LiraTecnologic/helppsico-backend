package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.builders.VinculoBuilder;
import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.infrastructure.repositories.entities.VinculoEntity;
import com.liratech.helppsico.validators.VinculoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VinculoMapperInfraTest {

    @Mock
    private PacienteMapperInfra pacienteMapperInfra;

    @Mock
    private PsicologoMapperInfra psicologoMapperInfra;

    @InjectMocks
    private VinculoMapperInfraImpl mapper;
    private Vinculo domainTest;
    private VinculoEntity entityTest;

    @Test
    void testeVinculoDomainParaEntity() {
        domainTest = VinculoBuilder.criarVinculo();

        Mockito.when(pacienteMapperInfra.paraEntity(Mockito.any())).thenReturn(PacienteBuilder.criarPacienteEntity());
        Mockito.when(psicologoMapperInfra.paraEntity(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoEntity());

        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        VinculoValidator.validaVinculoMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeVinculoEntityParaDomain() {
        entityTest = VinculoBuilder.criarVinculoEntity();

        Mockito.when(pacienteMapperInfra.paraDomain(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());
        Mockito.when(psicologoMapperInfra.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());

        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        VinculoValidator.validaVinculoMapperInfra(domainTest, entityTest);
    }
}