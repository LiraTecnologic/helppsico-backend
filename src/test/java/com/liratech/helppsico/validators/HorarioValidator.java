package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Horario;
import org.junit.jupiter.api.Assertions;

public class HorarioValidator {
    public static void validaHorarioDomain(Horario esperado, Horario resultado){
        Assertions.assertEquals(esperado.getDiaSemana(), resultado.getDiaSemana());
        Assertions.assertEquals(esperado.getInicio(), resultado.getInicio());
        Assertions.assertEquals(esperado.getFim(), resultado.getInicio());
        Assertions.assertEquals(esperado.getDisponivel(), resultado.getDisponivel());
    }
}
