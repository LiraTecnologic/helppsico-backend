package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Foto;
import org.junit.jupiter.api.Assertions;

public class FotoValidator {
    public static void validaFotoDomain(Foto esperado, Foto resultado){
        Assertions.assertEquals(esperado.getPsicologo(), resultado.getPsicologo());
        Assertions.assertEquals(esperado.getPaciente(), resultado.getPaciente());
        Assertions.assertEquals(esperado.getFotoUrl(), resultado.getFotoUrl());
    }
}
