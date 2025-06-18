package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import org.junit.jupiter.api.Assertions;

public class PacienteValidator {
    public static void validaPacienteDomain (Paciente esperado, Paciente resultado){
        Assertions.assertEquals(esperado.getNome(), resultado.getNome());
        Assertions.assertEquals(esperado.getCpf(), resultado.getCpf());
        Assertions.assertEquals(esperado.getEmail(),resultado.getEmail());
        Assertions.assertEquals(esperado.getTelefone(), resultado.getTelefone());
        Assertions.assertEquals(esperado.getDataNascimento(), resultado.getDataNascimento());
        Assertions.assertEquals(esperado.getSenha(), resultado.getSenha());
        Assertions.assertEquals(esperado.getGenero(), resultado.getGenero());
        EnderecoValidator.validaEnderecoDomain(esperado.getEndereco(), resultado.getEndereco());
        Assertions.assertEquals(esperado.getFotoUrl(), resultado.getFotoUrl());
    }

    public static void validaPacienteMapperEntry (Paciente domain, PacienteDto dto){
        Assertions.assertEquals(domain.getNome(), dto.getNome());
        Assertions.assertEquals(domain.getCpf(), dto.getCpf());
        Assertions.assertEquals(domain.getEmail(),dto.getEmail());
        Assertions.assertEquals(domain.getTelefone(), dto.getTelefone());
        Assertions.assertEquals(domain.getDataNascimento(), dto.getDataNascimento());
        Assertions.assertEquals(domain.getSenha(), dto.getSenha());
        Assertions.assertEquals(domain.getGenero(), dto.getGenero());
        EnderecoValidator.validaEnderecoMapperEntry(domain.getEndereco(), dto.getEndereco());
        Assertions.assertEquals(domain.getFotoUrl(), dto.getFotoUrl());
    }

    public static void validaPacienteMapperInfra (Paciente domain, PacienteEntity entity){
        Assertions.assertEquals(domain.getNome(), entity.getNome());
        Assertions.assertEquals(domain.getCpf(), entity.getCpf());
        Assertions.assertEquals(domain.getEmail(),entity.getEmail());
        Assertions.assertEquals(domain.getTelefone(), entity.getTelefone());
        Assertions.assertEquals(domain.getDataNascimento(), entity.getDataNascimento());
        Assertions.assertEquals(domain.getSenha(), entity.getSenha());
        Assertions.assertEquals(domain.getGenero(), entity.getGenero());
        EnderecoValidator.validaEnderecoMapperInfra(domain.getEndereco(), entity.getEndereco());
        Assertions.assertEquals(domain.getFotoUrl(), entity.getFotoUrl());
    }
}
