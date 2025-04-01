package com.liratech.helppsico.validator;

import com.liratech.helppsico.domain.Psicologo;
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
}
