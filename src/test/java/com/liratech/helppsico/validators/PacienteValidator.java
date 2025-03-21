package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Paciente;
import org.junit.jupiter.api.Assertions;

public class PacienteValidator {
    public static void validaPaciente(Paciente paciente1, Paciente paciente2) {
        Assertions.assertEquals(paciente1.getNome(), paciente2.getNome());
        Assertions.assertEquals(paciente1.getCpf(), paciente2.getCpf());
        Assertions.assertEquals(paciente1.getEmail(), paciente2.getEmail());
        Assertions.assertEquals(paciente1.getTelefone(), paciente2.getTelefone());
        Assertions.assertEquals(paciente1.getDataNascimento(), paciente2.getDataNascimento());
        Assertions.assertEquals(paciente1.getSenha(), paciente2.getSenha());
        Assertions.assertEquals(paciente1.getGenero(), paciente2.getGenero());
        Assertions.assertEquals(paciente1.getEndereco(), paciente2.getEndereco());
        Assertions.assertEquals(paciente1.getFotoUrl(), paciente2.getFotoUrl());
    }
}
