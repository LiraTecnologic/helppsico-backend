package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.HorarioBuilder;
import com.liratech.helppsico.domain.Horario;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioDto;
import com.liratech.helppsico.entrypoint.mapper.HorarioMapper;
import com.liratech.helppsico.infrastructure.repositories.entities.HorarioEntity;
import com.liratech.helppsico.validators.HorarioValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
public class HorarioMapperInfraTest {

    private HorarioMapperInfra mapper;
    private Horario domainTest;
    private HorarioEntity entityTest;

    @Test
    void testeHorarioDomainParaEntity(){
        domainTest = HorarioBuilder.criarHorario();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        HorarioValidator.validaHorarioMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeHorarioEntityParaDomain(){
        entityTest = HorarioBuilder.criarHorarioEntity();
        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        HorarioValidator.validaHorarioMapperInfra(domainTest, entityTest);
    }
}
