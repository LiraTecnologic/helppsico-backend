package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Paciente;
import org.junit.jupiter.api.Assertions;

public class PacienteValidator {
    public static void validaPacienteDomain(Paciente esperado, Paciente resultado) {
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getNome(), resultado.getNome());
        Assertions.assertEquals(esperado.getCpf(), resultado.getCpf());
        Assertions.assertEquals(esperado.getEmail(),resultado.getEmail());
        Assertions.assertEquals(esperado.getTelefone(), resultado.getTelefone());
        Assertions.assertEquals(esperado.getDataNascimento(), resultado.getDataNascimento());
        Assertions.assertEquals(esperado.getSenha(), resultado.getSenha());
        Assertions.assertEquals(esperado, resultado);
        EnderecoValidator.validaEnderecoDomain(esperado.getEndereco(), resultado.getEndereco());
        Assertions.assertEquals(esperado.getFotoUrl(), resultado.getFotoUrl());
    }
}
