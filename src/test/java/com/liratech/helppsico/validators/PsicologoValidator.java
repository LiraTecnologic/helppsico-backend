package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.TipoGenero;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import org.junit.jupiter.api.Assertions;

public class PsicologoValidator {
    public static void validaPsicologoDomain(Psicologo esperado, Psicologo resultado){
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getNome(), resultado.getNome());
        Assertions.assertEquals(esperado.getCrp(), resultado.getCrp());
        Assertions.assertEquals(esperado.getCpf(), resultado.getCpf());
        Assertions.assertEquals(esperado.getEmail(), resultado.getEmail());
        Assertions.assertEquals(esperado.getTelefone(), resultado.getTelefone());
        Assertions.assertEquals(esperado.getDataNascimento(), resultado.getDataNascimento());
        Assertions.assertEquals(esperado.getSenha(), resultado.getSenha());
        Assertions.assertEquals(esperado, resultado);
        Assertions.assertEquals(esperado.getEnderecoAtendimento(), resultado.getEnderecoAtendimento());
        Assertions.assertEquals(esperado.getFotoUrl(), resultado.getFotoUrl());
        Assertions.assertEquals(esperado.getBiografia(), resultado.getBiografia());
    }

    public static void validaPsicologoDto(PsicologoDto esperado, PsicologoDto resultado){
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getNome(), resultado.getNome());
        Assertions.assertEquals(esperado.getCrp(), resultado.getCrp());
        Assertions.assertEquals(esperado.getCpf(), resultado.getCpf());
        Assertions.assertEquals(esperado.getEmail(), resultado.getEmail());
        Assertions.assertEquals(esperado.getTelefone(), resultado.getTelefone());
        Assertions.assertEquals(esperado.getDataNascimento(), resultado.getDataNascimento());
        Assertions.assertEquals(esperado.getSenha(), resultado.getSenha());
        Assertions.assertEquals(esperado, resultado);
        Assertions.assertEquals(esperado.getEnderecoAtendimento(), resultado.getEnderecoAtendimento());
        Assertions.assertEquals(esperado.getFotoUrl(), resultado.getFotoUrl());
        Assertions.assertEquals(esperado.getFoto(), resultado.getFoto());
        Assertions.assertEquals(esperado.getBiografia(), resultado.getBiografia());
    }

    public static void validaPsicologoDtoParaDomain(PsicologoDto esperado, Psicologo resultado) {
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getNome(), resultado.getNome());
        Assertions.assertEquals(esperado.getCrp(), resultado.getCrp());
        Assertions.assertEquals(esperado.getCpf(), resultado.getCpf());
        Assertions.assertEquals(esperado.getEmail(), resultado.getEmail());
        Assertions.assertEquals(esperado.getTelefone(), resultado.getTelefone());
        Assertions.assertEquals(esperado.getDataNascimento(), resultado.getDataNascimento());
        Assertions.assertEquals(esperado.getSenha(), resultado.getSenha());
        Assertions.assertEquals(TipoGenero.valueOf(esperado.getGenero().name()), resultado.getGenero());
        EnderecoValidator.validaEnderecoDtoParaDomain(esperado.getEnderecoAtendimento(), resultado.getEnderecoAtendimento());
        Assertions.assertEquals(esperado.getFotoUrl(), resultado.getFotoUrl());
        Assertions.assertEquals(esperado.getBiografia(), resultado.getBiografia());
    }

    public static void validaPsicologoDomainParaDto(Psicologo esperado, PsicologoDto resultado) {
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getNome(), resultado.getNome());
        Assertions.assertEquals(esperado.getCrp(), resultado.getCrp());
        Assertions.assertEquals(esperado.getCpf(), resultado.getCpf());
        Assertions.assertEquals(esperado.getEmail(), resultado.getEmail());
        Assertions.assertEquals(esperado.getTelefone(), resultado.getTelefone());
        Assertions.assertEquals(esperado.getDataNascimento(), resultado.getDataNascimento());
        Assertions.assertEquals(esperado.getSenha(), resultado.getSenha());
        Assertions.assertEquals(TipoGenero.valueOf(esperado.getGenero().name()), resultado.getGenero());
        EnderecoValidator.validaEnderecoDomainParaDto(esperado.getEnderecoAtendimento(), resultado.getEnderecoAtendimento());
        Assertions.assertEquals(esperado.getFotoUrl(), resultado.getFotoUrl());
        Assertions.assertEquals(esperado.getBiografia(), resultado.getBiografia());
    }

    public static void validaPsicologoDomainParaEntity(Psicologo esperado, PsicologoEntity resultado) {
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getNome(), resultado.getNome());
        Assertions.assertEquals(esperado.getCrp(), resultado.getCrp());
        Assertions.assertEquals(esperado.getCpf(), resultado.getCpf());
        Assertions.assertEquals(esperado.getEmail(), resultado.getEmail());
        Assertions.assertEquals(esperado.getTelefone(), resultado.getTelefone());
        Assertions.assertEquals(esperado.getDataNascimento(), resultado.getDataNascimento());
        Assertions.assertEquals(esperado.getSenha(), resultado.getSenha());
        Assertions.assertEquals(TipoGenero.valueOf(esperado.getGenero().name()), resultado.getGenero());
        EnderecoValidator.validaEnderecoDomainParaEntity(esperado.getEnderecoAtendimento(), resultado.getEnderecoAtendimento());
        Assertions.assertEquals(esperado.getFotoUrl(), resultado.getFotoUrl());
        Assertions.assertEquals(esperado.getBiografia(), resultado.getBiografia());
    }

    public static void validaPsicologoEntityParaDomain(PsicologoEntity esperado, Psicologo resultado) {
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getNome(), resultado.getNome());
        Assertions.assertEquals(esperado.getCrp(), resultado.getCrp());
        Assertions.assertEquals(esperado.getCpf(), resultado.getCpf());
        Assertions.assertEquals(esperado.getEmail(), resultado.getEmail());
        Assertions.assertEquals(esperado.getTelefone(), resultado.getTelefone());
        Assertions.assertEquals(esperado.getDataNascimento(), resultado.getDataNascimento());
        Assertions.assertEquals(esperado.getSenha(), resultado.getSenha());
        Assertions.assertEquals(TipoGenero.valueOf(esperado.getGenero().name()), resultado.getGenero());
        EnderecoValidator.validaEnderecoEntityParaDomain(esperado.getEnderecoAtendimento(), resultado.getEnderecoAtendimento());
        Assertions.assertEquals(esperado.getFotoUrl(), resultado.getFotoUrl());
        Assertions.assertEquals(esperado.getBiografia(), resultado.getBiografia());
    }
}
