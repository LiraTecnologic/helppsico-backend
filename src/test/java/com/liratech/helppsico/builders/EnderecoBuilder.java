package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;

import java.util.UUID;

public class EnderecoBuilder {
    public static Endereco criarEndereco() {
        return Endereco.builder()
                .id(UUID.randomUUID())
                .rua("Rua Teste")
                .numero(123)
                .cep("12345678")
                .cidade("Cidade Teste")
                .estado("Estado Teste")
                .build();
    }

    public static EnderecoDto criarEnderecoDto() {
        return EnderecoDto.builder()
                .id(UUID.randomUUID())
                .rua("Rua Teste")
                .numero(123)
                .cep("12345678")
                .cidade("Cidade Teste")
                .estado("Estado Teste")
                .build();
    }
}
