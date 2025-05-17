package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.HorarioPsicologo;
import com.liratech.helppsico.infrastructure.repositories.entities.HorarioEntity;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class HorarioPsicologoValidator {
    public static void validaHorarioPsicologoDomain(HorarioPsicologo esperado, HorarioPsicologo resultado){
        PsicologoValidator.validaPsicologoDomain(esperado.getPsicologo(), resultado.getPsicologo());
        for (int i = 0; i < resultado.getHorarios().size(); i++){
            HorarioValidator.validaHorarioDomain(esperado.getHorarios().get(i), resultado.getHorarios().get(i));
        }
    }
}
