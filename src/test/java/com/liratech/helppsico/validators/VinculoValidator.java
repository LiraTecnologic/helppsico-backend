package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Vinculo;
import org.junit.jupiter.api.Assertions;

public class VinculoValidator {
    public static void validaVinculoDomain(Vinculo esperado, Vinculo resultado){
        PsicologoValidator.validaPsicologoDomain(esperado.getPsicologo(), resultado.getPsicologo());
        PacienteValidator.validaPacienteDomain(esperado.getPaciente(), resultado.getPaciente());
        Assertions.assertEquals(esperado.getStatus(), resultado.getStatus());
    }
}
