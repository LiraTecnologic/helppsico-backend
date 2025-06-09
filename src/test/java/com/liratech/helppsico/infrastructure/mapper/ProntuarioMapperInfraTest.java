package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.ConsultaBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.ProntuarioBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.infrastructure.repositories.entities.ProntuarioEntity;
import com.liratech.helppsico.validators.ProntuarioValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProntuarioMapperInfraTest {

    @Mock
    private PsicologoMapperInfra psicologoMapperInfra;

    @Mock
    private PacienteMapperInfra pacienteMapperInfra;

    @Mock
    private ConsultaMapperInfra consultaMapperInfra;

    @InjectMocks
    private ProntuarioMapperInfraImpl mapper;
    private Prontuario domainTest;
    private ProntuarioEntity entityTest;

    @Test
    void testeProntuarioDomainParaEntity() {
        domainTest = ProntuarioBuilder.criarProntuario();

        Mockito.when(psicologoMapperInfra.paraEntity(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoEntity());
        Mockito.when(pacienteMapperInfra.paraEntity(Mockito.any())).thenReturn(PacienteBuilder.criarPacienteEntity());
        Mockito.when(consultaMapperInfra.paraEntity(Mockito.any())).thenReturn(ConsultaBuilder.criarConsultaEntity());

        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        ProntuarioValidator.validaProntuarioMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeProntuarioEntityParaDomain() {
        entityTest = ProntuarioBuilder.criarProntuarioEntity();

        Mockito.when(psicologoMapperInfra.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());
        Mockito.when(pacienteMapperInfra.paraDomain(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());
        Mockito.when(consultaMapperInfra.paraDomain(Mockito.any())).thenReturn(ConsultaBuilder.criarConsulta());

        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        ProntuarioValidator.validaProntuarioMapperInfra(domainTest, entityTest);
    }
}