package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.HorarioPsicologo;
import org.junit.jupiter.api.Assertions;

public class HorarioPsicologoValidator {
    public static void validaHorarioPsicologoDomain(HorarioPsicologo esperado, HorarioPsicologo resultado){
        Assertions.assertEquals(esperado.getPsicologo(), resultado.getPsicologo());
        Assertions.assertEquals(esperado.getData(), resultado.getData());
        Assertions.assertEquals(esperado.getHora(), resultado.getHora());
    }
}
