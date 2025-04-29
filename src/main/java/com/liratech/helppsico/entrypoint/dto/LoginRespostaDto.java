package com.liratech.helppsico.entrypoint.dto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class LoginRespostaDto {
    private UUID idUsuario;
    private String crp;
    private String email;
    private String token;
}
