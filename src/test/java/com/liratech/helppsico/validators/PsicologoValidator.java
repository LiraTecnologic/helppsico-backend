package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import org.junit.jupiter.api.Assertions;

public class PsicologoValidator {
    public static void validaPsicologoDomain(Psicologo esperado, Psicologo resultado){
        Assertions.assertEquals(esperado.getNome(), resultado.getNome());
        Assertions.assertEquals(esperado.getCrp(), resultado.getCrp());
        Assertions.assertEquals(esperado.getCpf(), resultado.getCpf());
        Assertions.assertEquals(esperado.getEmail(), resultado.getEmail());
        Assertions.assertEquals(esperado.getTelefone(), resultado.getTelefone());
        Assertions.assertEquals(esperado.getDataNascimento(), resultado.getDataNascimento());
        Assertions.assertEquals(esperado.getSenha(), resultado.getSenha());
        Assertions.assertEquals(esperado.getGenero(), resultado.getGenero());
        EnderecoValidator.validaEnderecoDomain(esperado.getEnderecoAtendimento(), resultado.getEnderecoAtendimento());
        Assertions.assertEquals(esperado.getFotoUrl(), resultado.getFotoUrl());
        Assertions.assertEquals(esperado.getBiografia(), resultado.getBiografia());
        Assertions.assertEquals(esperado.getStatusPsicologo(), resultado.getStatusPsicologo());
        Assertions.assertEquals(esperado.getValorSessao(), resultado.getValorSessao());
        Assertions.assertEquals(esperado.getTempoSessao(), resultado.getTempoSessao());
    }

    public static void validaPsicologoMapperEntry(Psicologo domain, PsicologoDto dto) {
        Assertions.assertEquals(domain.getNome(), dto.getNome());
        Assertions.assertEquals(domain.getCrp(), dto.getCrp());
        Assertions.assertEquals(domain.getCpf(), dto.getCpf());
        Assertions.assertEquals(domain.getEmail(), dto.getEmail());
        Assertions.assertEquals(domain.getTelefone(), dto.getTelefone());
        Assertions.assertEquals(domain.getDataNascimento(), dto.getDataNascimento());
        Assertions.assertEquals(domain.getSenha(), dto.getSenha());
        Assertions.assertEquals(domain.getGenero(), dto.getGenero());
        EnderecoValidator.validaEnderecoMapperEntry(domain.getEnderecoAtendimento(), dto.getEnderecoAtendimento());
        Assertions.assertEquals(domain.getFotoUrl(), dto.getFotoUrl());
        Assertions.assertEquals(domain.getBiografia(), dto.getBiografia());
        Assertions.assertEquals(domain.getStatusPsicologo(), dto.getStatusPsicologo());
        Assertions.assertEquals(domain.getValorSessao(), dto.getValorSessao());
        Assertions.assertEquals(domain.getTempoSessao(), dto.getTempoSessao());
    }

    public static void validaPsicologoMapperInfra(Psicologo domain, PsicologoEntity entity) {
        Assertions.assertEquals(domain.getNome(), entity.getNome());
        Assertions.assertEquals(domain.getCrp(), entity.getCrp());
        Assertions.assertEquals(domain.getCpf(), entity.getCpf());
        Assertions.assertEquals(domain.getEmail(), entity.getEmail());
        Assertions.assertEquals(domain.getTelefone(), entity.getTelefone());
        Assertions.assertEquals(domain.getDataNascimento(), entity.getDataNascimento());
        Assertions.assertEquals(domain.getSenha(), entity.getSenha());
        Assertions.assertEquals(domain.getGenero(), entity.getGenero());
        EnderecoValidator.validaEnderecoMapperInfra(domain.getEnderecoAtendimento(), entity.getEnderecoAtendimento());
        Assertions.assertEquals(domain.getFotoUrl(), entity.getFotoUrl());
        Assertions.assertEquals(domain.getBiografia(), entity.getBiografia());
        Assertions.assertEquals(domain.getStatusPsicologo(), entity.getStatusPsicologo());
        Assertions.assertEquals(domain.getValorSessao(), entity.getValorSessao());
        Assertions.assertEquals(domain.getTempoSessao(), entity.getTempoSessao());
    }
}
