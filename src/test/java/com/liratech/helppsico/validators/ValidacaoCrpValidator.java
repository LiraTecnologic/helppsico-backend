package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.ValidacaoCrp;
import org.junit.jupiter.api.Assertions;

public class ValidacaoCrpValidator {
    public static void validaValidacaoCrpDomain(ValidacaoCrp esperado, ValidacaoCrp resultado){
        PsicologoValidator.validaPsicologoDomain(esperado.getPsicologo(), resultado.getPsicologo());
        Assertions.assertEquals(esperado.getPsicologo().getCrp(), resultado.getPsicologo().getCrp());
        Assertions.assertEquals(esperado.getMotivoReprova(), resultado.getMotivoReprova());
    }
}
