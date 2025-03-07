package com.liratech.helppsico.domain;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Vinculo {
    private UUID id;
    private Paciente paciente;
    private Psicologo psicologo;
    private StatusVinculo status;
}
