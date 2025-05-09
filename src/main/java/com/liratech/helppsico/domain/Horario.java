package com.liratech.helppsico.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Horario {
    private UUID id;
    private DiaSemana diaSemana;
    private LocalTime inicio;
    private LocalTime fim;
    private Boolean disponivel;
}
