package com.liratech.helppsico.entrypoint.dto;

import lombok.Builder;

@Builder
public class LoginDto {
    private String email;
    private String senha;
    private String token;
}