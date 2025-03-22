package com.liratech.helppsico.validator;

import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import org.junit.jupiter.api.Assertions;

public class EnderecoValidator {
    public static void validaEnderecoDomain (Endereco esperado, Endereco resultado){
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getRua(), resultado.getRua());
        Assertions.assertEquals(esperado.getNumero(), resultado.getNumero());
        Assertions.assertEquals(esperado.getCep(), resultado.getCep());
        Assertions.assertEquals(esperado.getCidade(), resultado.getCidade());
        Assertions.assertEquals(esperado.getEstado(), resultado.getEstado());
    }

    public static void validaEnderecoDto (EnderecoDto esperado, EnderecoDto resultado){
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getRua(), resultado.getRua());
        Assertions.assertEquals(esperado.getNumero(), resultado.getNumero());
        Assertions.assertEquals(esperado.getCep(), resultado.getCep());
        Assertions.assertEquals(esperado.getCidade(), resultado.getCidade());
        Assertions.assertEquals(esperado.getEstado(), resultado.getEstado());
    }

    public static void validaEnderecoDtoParaDomain(EnderecoDto esperado, Endereco resultado){
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getRua(), resultado.getRua());
        Assertions.assertEquals(esperado.getNumero(), resultado.getNumero());
        Assertions.assertEquals(esperado.getCep(), resultado.getCep());
        Assertions.assertEquals(esperado.getCidade(), resultado.getCidade());
        Assertions.assertEquals(esperado.getEstado(), resultado.getEstado());
    }

    public static void validaEnderecoDomainParaDto(Endereco esperado, EnderecoDto resultado){
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getRua(), resultado.getRua());
        Assertions.assertEquals(esperado.getNumero(), resultado.getNumero());
        Assertions.assertEquals(esperado.getCep(), resultado.getCep());
        Assertions.assertEquals(esperado.getCidade(), resultado.getCidade());
        Assertions.assertEquals(esperado.getEstado(), resultado.getEstado());
    }
}
