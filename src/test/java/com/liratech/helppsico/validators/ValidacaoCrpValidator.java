package com.liratech.helppsico.validators;

import org.junit.jupiter.api.Assertions;

public class ValidacaoCrpValidator {
    public static void validaValidacaoCrpDomain(ValidacaoCrp esperado, ValidacaoCrp resultado){
        //mesma coisa referente ao psicologo aqui.
        PsicologoValidator.validaPsicologoDomain(esperado.getPsicologo(), esperado.getPsicologo());
        Assertions.assertEquals(esperado.getCrp(), resultado.getCrp());
    }
}
