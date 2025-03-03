package com.liratech.helppsico.entrypoint.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record EnderecoDto(
        UUID id,
        String rua,
        Integer numero,
        String cep,
        String cidade,
        String estado
) {
}
