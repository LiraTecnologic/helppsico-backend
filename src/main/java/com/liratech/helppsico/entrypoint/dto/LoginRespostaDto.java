package com.liratech.helppsico.entrypoint.dto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class LoginRespostaDto {
    private UUID idPaciente;
    private String email;
    private String senha;
    private String token;
}
