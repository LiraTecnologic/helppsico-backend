package com.liratech.helppsico.entrypoint.dto;

import lombok.Builder;

@Builder
public record LoginDto(
        String email,
        String senha,
        String token
) {
}