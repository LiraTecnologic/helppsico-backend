package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Avaliacao;
import org.junit.jupiter.api.Assertions;

public class AvaliacaoValidator {

    public static void validaAvaliacaoDomain (Avaliacao esperado, Avaliacao resultado) {
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        PsicologoValidator.validaPsicologoDomain(esperado.getPsicologo(), resultado.getPsicologo());
        PacienteValidator.validaPacienteDomain(esperado.getPaciente(), resultado.getPaciente());
        Assertions.assertEquals(esperado.getNota(), resultado.getNota());
        Assertions.assertEquals(esperado.getComentario(), resultado.getComentario());
    }
}
