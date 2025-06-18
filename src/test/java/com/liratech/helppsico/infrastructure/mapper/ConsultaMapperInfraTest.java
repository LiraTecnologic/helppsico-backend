package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.*;
import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.infrastructure.repositories.entities.ConsultaEntity;
import com.liratech.helppsico.validators.ConsultaValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConsultaMapperInfraTest {

    @Mock
    private PsicologoMapperInfra psicologoMapperInfra;

    @Mock
    private PacienteMapperInfra pacienteMapperInfra;

    @Mock
    private EnderecoMapperInfra enderecoMapperInfra;

    @Mock
    private HorarioMapperInfra horarioMapperInfra;

    @InjectMocks
    private ConsultaMapperInfraImpl mapper;

    private ConsultaEntity entityTest;
    private Consulta domainTest;

    @Test
    void testeConsultaEntityParaDomain() {
        entityTest = ConsultaBuilder.criarConsultaEntity();

        Mockito.when(psicologoMapperInfra.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());
        Mockito.when(pacienteMapperInfra.paraDomain(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());
        Mockito.when(enderecoMapperInfra.paraDomain(Mockito.any())).thenReturn(EnderecoBuilder.criarEndereco());
        Mockito.when(horarioMapperInfra.paraDomain(Mockito.any())).thenReturn(HorarioBuilder.criarHorario());

        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        ConsultaValidator.validaConsultaMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeConsultaDomainParaEntity() {
        domainTest = ConsultaBuilder.criarConsulta();

        Mockito.when(psicologoMapperInfra.paraEntity(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoEntity());
        Mockito.when(pacienteMapperInfra.paraEntity(Mockito.any())).thenReturn(PacienteBuilder.criarPacienteEntity());
        Mockito.when(enderecoMapperInfra.paraEntity(Mockito.any())).thenReturn(EnderecoBuilder.criarEnderecoEntity());
        Mockito.when(horarioMapperInfra.paraEntity(Mockito.any())).thenReturn(HorarioBuilder.criarHorarioEntity());

        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        ConsultaValidator.validaConsultaMapperInfra(domainTest, entityTest);
    }
}