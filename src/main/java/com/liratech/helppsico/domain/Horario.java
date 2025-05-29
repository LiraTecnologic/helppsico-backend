package com.liratech.helppsico.domain;

import lombok.*;

import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Horario {
    private UUID id;
    private DiaSemana diaSemana;
    private LocalTime inicio;
    private LocalTime fim;
    private Boolean disponivel;
    private HorarioPsicologo horarioPsicologo;
}
