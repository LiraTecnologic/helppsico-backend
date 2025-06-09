package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.AvaliacaoBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.infrastructure.repositories.entities.AvaliacaoEntity;
import com.liratech.helppsico.validators.AvaliacaoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AvaliacaoMapperInfraTest {

    @Mock
    private PsicologoMapperInfra psicologoMapperInfra;

    @Mock
    private PacienteMapperInfra pacienteMapperInfra;

    @InjectMocks
    private AvaliacaoMapperInfraImpl mapper;

    private Avaliacao domainTest;
    private AvaliacaoEntity entityTest;

    @Test
    void testeAvaliacaoDomainParaEntity() {
        domainTest = AvaliacaoBuilder.criarAvaliacao();

        Mockito.when(pacienteMapperInfra.paraEntity(Mockito.any())).thenReturn(PacienteBuilder.criarPacienteEntity());
        Mockito.when(psicologoMapperInfra.paraEntity(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoEntity());

        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        AvaliacaoValidator.validaAvaliacaoMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeAvaliacaoEntityParaDomain() {
        entityTest = AvaliacaoBuilder.criarAvaliacaoEntity();

        Mockito.when(pacienteMapperInfra.paraDomain(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());
        Mockito.when(psicologoMapperInfra.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());

        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        AvaliacaoValidator.validaAvaliacaoMapperInfra(domainTest, entityTest);
    }
}