package com.liratech.helppsico.validator;

import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import org.junit.jupiter.api.Assertions;

public class EnderecoValidator {
    public static void validarEnderecoDomain (Endereco comparacao1, Endereco comparacao2){
        Assertions.assertEquals(comparacao1.getId(), comparacao2.getId());
        Assertions.assertEquals(comparacao1.getRua(), comparacao2.getRua());
        Assertions.assertEquals(comparacao1.getNumero(), comparacao2.getNumero());
        Assertions.assertEquals(comparacao1.getCep(), comparacao2.getCep());
        Assertions.assertEquals(comparacao1.getCidade(), comparacao2.getCidade());
        Assertions.assertEquals(comparacao1.getEstado(), comparacao2.getEstado());
    }

    public static void validarEnderecoDto (EnderecoDto comparacao1, EnderecoDto comparacao2){
        Assertions.assertEquals(comparacao1.getId(), comparacao2.getId());
        Assertions.assertEquals(comparacao1.getRua(), comparacao2.getRua());
        Assertions.assertEquals(comparacao1.getNumero(), comparacao2.getNumero());
        Assertions.assertEquals(comparacao1.getCep(), comparacao2.getCep());
        Assertions.assertEquals(comparacao1.getCidade(), comparacao2.getCidade());
        Assertions.assertEquals(comparacao1.getEstado(), comparacao2.getEstado());
    }
}
