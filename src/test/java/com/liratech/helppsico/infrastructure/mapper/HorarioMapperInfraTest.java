package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.HorarioBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Horario;
import com.liratech.helppsico.infrastructure.repositories.entities.HorarioEntity;
import com.liratech.helppsico.validators.HorarioValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HorarioMapperInfraTest {

    @Mock
    private PsicologoMapperInfra psicologoMapperInfra;

    @InjectMocks
    private HorarioMapperInfraImpl mapper;

    private Horario domainTest;
    private HorarioEntity entityTest;

    @Test
    void testeHorarioDomainParaEntity(){
        domainTest = HorarioBuilder.criarHorario();

        Mockito.when(psicologoMapperInfra.paraEntity(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoEntity());

        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        HorarioValidator.validaHorarioMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeHorarioEntityParaDomain(){
        entityTest = HorarioBuilder.criarHorarioEntity();

        Mockito.when(psicologoMapperInfra.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());

        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        HorarioValidator.validaHorarioMapperInfra(domainTest, entityTest);
    }
}
