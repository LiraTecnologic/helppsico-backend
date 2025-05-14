package com.liratech.helppsico.domain;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Foto {
    private Psicologo psicologo;
    private Paciente paciente;
    private String fotoUrl;
}
