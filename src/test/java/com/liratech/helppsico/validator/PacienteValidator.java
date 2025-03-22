package com.liratech.helppsico.validator;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import org.junit.jupiter.api.Assertions;

public class PacienteValidator {
    public static void validarPacienteDomain (Paciente esperado, Paciente resultado){
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getNome(), resultado.getNome());
        Assertions.assertEquals(esperado.getCpf(), resultado.getCpf());
        Assertions.assertEquals(esperado.getEmail(),resultado.getEmail());
        Assertions.assertEquals(esperado.getTelefone(), resultado.getTelefone());
        Assertions.assertEquals(esperado.getDataNascimento(), resultado.getDataNascimento());
        Assertions.assertEquals(esperado.getSenha(), resultado.getSenha());
        TipoGeneroValidator.validaTipoGenero(esperado.getGenero(), resultado.getGenero());
        EnderecoValidator.validaEnderecoDomain(esperado.getEndereco(), resultado.getEndereco());
        Assertions.assertEquals(esperado.getFotoUrl(), resultado.getFotoUrl());
    }

    public static void validarPacienteDto (PacienteDto esperado, PacienteDto resultado){
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getNome(), resultado.getNome());
        Assertions.assertEquals(esperado.getCpf(), resultado.getCpf());
        Assertions.assertEquals(esperado.getEmail(),resultado.getEmail());
        Assertions.assertEquals(esperado.getTelefone(), resultado.getTelefone());
        Assertions.assertEquals(esperado.getDataNascimento(), resultado.getDataNascimento());
        Assertions.assertEquals(esperado.getSenha(), resultado.getSenha());
        TipoGeneroValidator.validaTipoGeneroDto(esperado.getGenero(), resultado.getGenero());
        EnderecoValidator.validaEnderecoDto(esperado.getEndereco(), resultado.getEndereco());
        Assertions.assertEquals(esperado.getFotoUrl(), resultado.getFotoUrl());
        Assertions.assertEquals(esperado.getFoto(), resultado.getFoto());
    }

    public static void validaPacienteDtoParaDomain (PacienteDto esperado, Paciente resultado){
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getNome(), resultado.getNome());
        Assertions.assertEquals(esperado.getCpf(), resultado.getCpf());
        Assertions.assertEquals(esperado.getEmail(),resultado.getEmail());
        Assertions.assertEquals(esperado.getTelefone(), resultado.getTelefone());
        Assertions.assertEquals(esperado.getDataNascimento(), resultado.getDataNascimento());
        Assertions.assertEquals(esperado.getSenha(), resultado.getSenha());
        TipoGeneroValidator.validaTipoGeneroDtoParaDomain(esperado.getGenero(), resultado.getGenero());
        EnderecoValidator.validaEnderecoDtoParaDomain(esperado.getEndereco(), resultado.getEndereco());
        Assertions.assertEquals(esperado.getFotoUrl(), resultado.getFotoUrl());
    }

    public static void validaPacienteDomainParaDto (Paciente esperado, PacienteDto resultado){
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getNome(), resultado.getNome());
        Assertions.assertEquals(esperado.getCpf(), resultado.getCpf());
        Assertions.assertEquals(esperado.getEmail(),resultado.getEmail());
        Assertions.assertEquals(esperado.getTelefone(), resultado.getTelefone());
        Assertions.assertEquals(esperado.getDataNascimento(), resultado.getDataNascimento());
        Assertions.assertEquals(esperado.getSenha(), resultado.getSenha());
        TipoGeneroValidator.validaTipoGeneroDomainParaDto(esperado.getGenero(), resultado.getGenero());
        EnderecoValidator.validaEnderecoDomainParaDto(esperado.getEndereco(), resultado.getEndereco());
        Assertions.assertEquals(esperado.getFotoUrl(), resultado.getFotoUrl());
    }
}
