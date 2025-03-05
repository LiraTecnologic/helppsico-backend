package com.liratech.helppsico.entrypoint.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginDto {
    private String email;
    private String senha;
    private String token;
}