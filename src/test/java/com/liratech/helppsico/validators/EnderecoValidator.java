package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Endereco;
import org.junit.jupiter.api.Assertions;

public class EnderecoValidator {
    public static void validaEnderecoDomain(Endereco esperado, Endereco resultado) {
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getRua(), resultado.getRua());
        Assertions.assertEquals(esperado.getNumero(), resultado.getNumero());
        Assertions.assertEquals(esperado.getCep(), resultado.getCep());
        Assertions.assertEquals(esperado.getCidade(), resultado.getCidade());
        Assertions.assertEquals(esperado.getEstado(), resultado.getEstado());
    }
}
