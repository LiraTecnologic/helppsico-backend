package com.liratech.helppsico.validator;

import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Psicologo;
import org.junit.jupiter.api.Assertions;

public class PsicologoValidator {
    public static void validaPsicologo(Psicologo psicologoCadastrado) {
        Psicologo psicologo = PsicologoBuilder.gerarPsicologo();

        Assertions.assertEquals(psicologo.getId(), psicologoCadastrado.getId());
        Assertions.assertEquals(psicologo.getNome(), psicologoCadastrado.getNome());
        Assertions.assertEquals(psicologo.getCrp(), psicologoCadastrado.getCrp());
        Assertions.assertEquals(psicologo.getCpf(), psicologoCadastrado.getCpf());
        Assertions.assertEquals(psicologo.getEmail(), psicologoCadastrado.getEmail());
        Assertions.assertEquals(psicologo.getTelefone(), psicologoCadastrado.getTelefone());
        Assertions.assertEquals(psicologo.getDataNascimento(), psicologoCadastrado.getDataNascimento());
        Assertions.assertEquals(psicologo.getSenha(), psicologoCadastrado.getSenha());
        Assertions.assertEquals(psicologo.getGenero(), psicologoCadastrado.getGenero());
        Assertions.assertEquals(psicologo.getEnderecoAtendimento(), psicologoCadastrado.getEnderecoAtendimento());
        Assertions.assertEquals(psicologo.getFotoUrl(), psicologoCadastrado.getFotoUrl());
        Assertions.assertEquals(psicologo.getBiografia(), psicologoCadastrado.getBiografia());
    }
}
