package com.liratech.helppsico.domain;

import lombok.*;

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
