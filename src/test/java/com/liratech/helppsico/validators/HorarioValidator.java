package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Horario;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioDto;
import com.liratech.helppsico.infrastructure.repositories.entities.HorarioEntity;
import org.junit.jupiter.api.Assertions;

public class HorarioValidator {
    public static void validaHorarioDomain(Horario esperado, Horario resultado){
        Assertions.assertEquals(esperado.getDiaSemana(), resultado.getDiaSemana());
        Assertions.assertEquals(esperado.getInicio(), resultado.getInicio());
        Assertions.assertEquals(esperado.getFim(), resultado.getFim());
        Assertions.assertEquals(esperado.getDisponivel(), resultado.getDisponivel());
        PsicologoValidator.validaPsicologoDomain(esperado.getPsicologo(), resultado.getPsicologo());
    }

    public static void validaHorarioMapperEntry(Horario domain, HorarioDto dto){
        Assertions.assertEquals(domain.getDiaSemana(), dto.getDiaSemana());
        Assertions.assertEquals(domain.getInicio(), dto.getInicio());
        Assertions.assertEquals(domain.getFim(), dto.getFim());
        Assertions.assertEquals(domain.getDisponivel(), dto.getDisponivel());
        PsicologoValidator.validaPsicologoMapperEntry(domain.getPsicologo(), dto.getPsicologo());
    }

    public static void validaHorarioMapperInfra(Horario domain, HorarioEntity entity){
        Assertions.assertEquals(domain.getDiaSemana(), entity.getDiaSemana());
        Assertions.assertEquals(domain.getInicio(), entity.getInicio());
        Assertions.assertEquals(domain.getFim(), entity.getFim());
        Assertions.assertEquals(domain.getDisponivel(), entity.getDisponivel());
        PsicologoValidator.validaPsicologoMapperInfra(domain.getPsicologo(), entity.getPsicologo());
    }
}
