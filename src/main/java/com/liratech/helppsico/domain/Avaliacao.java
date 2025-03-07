package com.liratech.helppsico.domain;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Avaliacao {
    private UUID id;
    private Psicologo psicologo;
    private Paciente paciente;
    private Double nota;
    private String comentario;
}
