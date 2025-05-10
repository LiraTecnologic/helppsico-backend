package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.HorarioPsicologo;
import com.liratech.helppsico.infrastructure.repositories.entities.HorarioEntity;
import org.junit.jupiter.api.Assertions;

public class HorarioPsicologoValidator {
    public static void validaHorarioPsicologoDomain(HorarioPsicologo esperado, HorarioPsicologo resultado){
        PsicologoValidator.validaPsicologoDomain(esperado.getPsicologo(), resultado.getPsicologo());
        Assertions.assertEquals(esperado.getHorarios(), resultado.getHorarios());
    }
}
