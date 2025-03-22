package com.liratech.helppsico.validator;

import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import org.junit.jupiter.api.Assertions;

public class PsicologoValidator {
    public static void validaPsicologoDomain(Psicologo comparacao1, Psicologo comparacao2){
        Assertions.assertEquals(comparacao1.getId(), comparacao2.getId());
        Assertions.assertEquals(comparacao1.getNome(), comparacao2.getNome());
        Assertions.assertEquals(comparacao1.getCrp(), comparacao2.getCrp());
        Assertions.assertEquals(comparacao1.getCpf(), comparacao2.getCpf());
        Assertions.assertEquals(comparacao1.getEmail(), comparacao2.getEmail());
        Assertions.assertEquals(comparacao1.getTelefone(), comparacao2.getTelefone());
        Assertions.assertEquals(comparacao1.getDataNascimento(), comparacao2.getDataNascimento());
        Assertions.assertEquals(comparacao1.getSenha(), comparacao2.getSenha());
        Assertions.assertEquals(comparacao1.getGenero(), comparacao2.getGenero());
        Assertions.assertEquals(comparacao1.getEnderecoAtendimento(), comparacao2.getEnderecoAtendimento());
        Assertions.assertEquals(comparacao1.getFotoUrl(), comparacao2.getFotoUrl());
        Assertions.assertEquals(comparacao1.getBiografia(), comparacao2.getBiografia());
    }

    public static void validaPsicologoDto(PsicologoDto comparacao1, PsicologoDto comparacao2){
        Assertions.assertEquals(comparacao1.getId(), comparacao2.getId());
        Assertions.assertEquals(comparacao1.getNome(), comparacao2.getNome());
        Assertions.assertEquals(comparacao1.getCrp(), comparacao2.getCrp());
        Assertions.assertEquals(comparacao1.getCpf(), comparacao2.getCpf());
        Assertions.assertEquals(comparacao1.getEmail(), comparacao2.getEmail());
        Assertions.assertEquals(comparacao1.getTelefone(), comparacao2.getTelefone());
        Assertions.assertEquals(comparacao1.getDataNascimento(), comparacao2.getDataNascimento());
        Assertions.assertEquals(comparacao1.getSenha(), comparacao2.getSenha());
        Assertions.assertEquals(comparacao1.getGenero(), comparacao2.getGenero());
        Assertions.assertEquals(comparacao1.getEnderecoAtendimento(), comparacao2.getEnderecoAtendimento());
        Assertions.assertEquals(comparacao1.getFotoUrl(), comparacao2.getFotoUrl());
        Assertions.assertEquals(comparacao1.getFoto(), comparacao2.getFoto());
        Assertions.assertEquals(comparacao1.getBiografia(), comparacao2.getBiografia());
    }

}
