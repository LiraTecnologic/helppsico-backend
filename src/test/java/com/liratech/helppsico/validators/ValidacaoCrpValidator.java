package com.liratech.helppsico.validators;

import org.junit.jupiter.api.Assertions;

public class ValidacaoCrpValidator {
    public static void validaValidacaoCrpDomain(ValidacaoCrp esperado, ValidacaoCrp resultado){
        PsicologoValidator.validaPsicologoDomain(esperado.getPsicologo(), resultado.getPsicologo());
        PsicologoValidator.validaPsicologoDomain(esperado.getPsicologo().getCrp(), resultado.getPsicologo().getCrp());
        Assertions.assertEquals(esperado.getMotivoReprova(), resultado.getMotivoReprova());
    }
}
