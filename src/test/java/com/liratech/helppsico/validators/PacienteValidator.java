package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.TipoGenero;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.TipoGeneroDto;
import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.TipoGeneroEntity;
import org.junit.jupiter.api.Assertions;

public class PacienteValidator {
    public static void validaPacienteDomain (Paciente esperado, Paciente resultado){

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

    public static void validaPacienteDto (PacienteDto esperado, PacienteDto resultado){
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getNome(), resultado.getNome());
        Assertions.assertEquals(esperado.getCpf(), resultado.getCpf());
        Assertions.assertEquals(esperado.getEmail(),resultado.getEmail());
        Assertions.assertEquals(esperado.getTelefone(), resultado.getTelefone());
        Assertions.assertEquals(esperado.getDataNascimento(), resultado.getDataNascimento());
        Assertions.assertEquals(esperado.getSenha(), resultado.getSenha());
        Assertions.assertEquals(esperado, resultado);
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
        Assertions.assertEquals(TipoGenero.valueOf(esperado.getGenero().name()), resultado.getGenero());
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
        Assertions.assertEquals(TipoGeneroDto.valueOf(esperado.getGenero().name()), resultado.getGenero());
        EnderecoValidator.validaEnderecoDomainParaDto(esperado.getEndereco(), resultado.getEndereco());
        Assertions.assertEquals(esperado.getFotoUrl(), resultado.getFotoUrl());
    }

    public static void validaPacienteDomainParaEntity (Paciente esperado, PacienteEntity resultado){
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getNome(), resultado.getNome());
        Assertions.assertEquals(esperado.getCpf(), resultado.getCpf());
        Assertions.assertEquals(esperado.getEmail(),resultado.getEmail());
        Assertions.assertEquals(esperado.getTelefone(), resultado.getTelefone());
        Assertions.assertEquals(esperado.getDataNascimento(), resultado.getDataNascimento());
        Assertions.assertEquals(esperado.getSenha(), resultado.getSenha());
        Assertions.assertEquals(TipoGeneroEntity.valueOf(esperado.getGenero().name()), resultado.getGenero());
        EnderecoValidator.validaEnderecoDomainParaEntity(esperado.getEndereco(), resultado.getEndereco());
        Assertions.assertEquals(esperado.getFotoUrl(), resultado.getFotoUrl());
    }

    public static void validaPacienteEntityParaDomain (PacienteEntity esperado, Paciente resultado){
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getNome(), resultado.getNome());
        Assertions.assertEquals(esperado.getCpf(), resultado.getCpf());
        Assertions.assertEquals(esperado.getEmail(),resultado.getEmail());
        Assertions.assertEquals(esperado.getTelefone(), resultado.getTelefone());
        Assertions.assertEquals(esperado.getDataNascimento(), resultado.getDataNascimento());
        Assertions.assertEquals(esperado.getSenha(), resultado.getSenha());
        Assertions.assertEquals(TipoGenero.valueOf(esperado.getGenero().name()), resultado.getGenero());
        EnderecoValidator.validaEnderecoEntityParaDomain(esperado.getEndereco(), resultado.getEndereco());
        Assertions.assertEquals(esperado.getFotoUrl(), resultado.getFotoUrl());
    }
}
