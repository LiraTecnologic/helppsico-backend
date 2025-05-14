package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Prontuario;
import org.junit.jupiter.api.Assertions;

public class ProntuarioValidator {

    public static void validaProntuarioDoamain(Prontuario resultado, Prontuario prontuarioTeste) {
        PsicologoValidator.validaPsicologoDomain(resultado.getPsicologo(), prontuarioTeste.getPsicologo());
        PacienteValidator.validaPacienteDomain(resultado.getPaciente(), prontuarioTeste.getPaciente());
        Assertions.assertEquals(resultado.getTitulo(), prontuarioTeste.getTitulo());
        Assertions.assertEquals(resultado.getConteudo(), prontuarioTeste.getConteudo());
    }
}
