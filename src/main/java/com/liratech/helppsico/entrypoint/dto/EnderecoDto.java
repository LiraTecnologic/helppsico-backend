package com.liratech.helppsico.entrypoint.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public class EnderecoDto{
    private UUID id;
    private String rua;
    private Integer numero;
    private String cep;
    private String cidade;
    private String estado;
}
