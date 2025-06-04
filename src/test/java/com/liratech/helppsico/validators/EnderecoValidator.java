package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.infrastructure.repositories.entities.EnderecoEntity;
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

    public static void validaEnderecoMapperEntry(Endereco domain, EnderecoDto dto){
        Assertions.assertEquals(domain.getId(), dto.getId());
        Assertions.assertEquals(domain.getRua(), dto.getRua());
        Assertions.assertEquals(domain.getNumero(), dto.getNumero());
        Assertions.assertEquals(domain.getCep(), dto.getCep());
        Assertions.assertEquals(domain.getCidade(), dto.getCidade());
        Assertions.assertEquals(domain.getEstado(), dto.getEstado());
    }

    public static void validaEnderecoMapperInfra(Endereco domain, EnderecoEntity entity){
        Assertions.assertEquals(domain.getId(), entity.getId());
        Assertions.assertEquals(domain.getRua(), entity.getRua());
        Assertions.assertEquals(domain.getNumero(), entity.getNumero());
        Assertions.assertEquals(domain.getCep(), entity.getCep());
        Assertions.assertEquals(domain.getCidade(), entity.getCidade());
        Assertions.assertEquals(domain.getEstado(), entity.getEstado());
    }
}
