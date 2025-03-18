package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Endereco;

import java.util.UUID;

public class EnderecoBuilder {

    public static Endereco gerarEndereco() {
        return Endereco.builder()
                .id(UUID.randomUUID())
                .rua("Rua teste")
                .numero(123)
                .cep("78950123")
                .cidade("Cidade teste")
                .estado("Estado teste")
                .build();
    }
}
