package com.liratech.helppsico.builders;

import java.util.UUID;

public class EnderecoDtoBuilder {
    public static EnderecoDto criarEnderecoDto(){
        return EndercoDto.builder()
                .id(UUID.randomUUID())
                .rua("Rua Teste")
                .numero(123)
                .cep("12345678")
                .cidade("Cidade Teste")
                .estado("Estado Teste")
                .build();
    }
}
