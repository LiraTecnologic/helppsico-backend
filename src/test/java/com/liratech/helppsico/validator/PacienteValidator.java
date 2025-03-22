package com.liratech.helppsico.validator;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import org.junit.jupiter.api.Assertions;

public class PacienteValidator {
    public static void validarPacienteDomain(Paciente comparacao1, Paciente comparacao2){
        Assertions.assertEquals(comparacao1.getId(), comparacao2.getId());
        Assertions.assertEquals(comparacao1.getNome(), comparacao2.getNome());
        Assertions.assertEquals(comparacao1.getCpf(), comparacao2.getCpf());
        Assertions.assertEquals(comparacao1.getEmail(),comparacao2.getEmail());
        Assertions.assertEquals(comparacao1.getTelefone(), comparacao2.getTelefone());
        Assertions.assertEquals(comparacao1.getDataNascimento(), comparacao2.getDataNascimento());
        Assertions.assertEquals(comparacao1.getSenha(), comparacao2.getSenha());
        Assertions.assertEquals(comparacao1.getGenero(), comparacao2.getGenero());
        EnderecoValidator.validarEnderecoDomain(comparacao1.getEndereco(), comparacao2.getEndereco());
        Assertions.assertEquals(comparacao1.getFotoUrl(), comparacao2.getFotoUrl());
    }

    public static void validarPacienteDto(PacienteDto comparacao1, PacienteDto comparacao2){
        Assertions.assertEquals(comparacao1.getId(), comparacao2.getId());
        Assertions.assertEquals(comparacao1.getNome(), comparacao2.getNome());
        Assertions.assertEquals(comparacao1.getCpf(), comparacao2.getCpf());
        Assertions.assertEquals(comparacao1.getEmail(),comparacao2.getEmail());
        Assertions.assertEquals(comparacao1.getTelefone(), comparacao2.getTelefone());
        Assertions.assertEquals(comparacao1.getDataNascimento(), comparacao2.getDataNascimento());
        Assertions.assertEquals(comparacao1.getSenha(), comparacao2.getSenha());
        Assertions.assertEquals(comparacao1.getGenero(), comparacao2.getGenero());
        EnderecoValidator.validarEnderecoDto(comparacao1.getEndereco(), comparacao2.getEndereco());
        Assertions.assertEquals(comparacao1.getFotoUrl(), comparacao2.getFotoUrl());
        Assertions.assertEquals(comparacao1.getFoto(), comparacao2.getFoto());
    }
}
