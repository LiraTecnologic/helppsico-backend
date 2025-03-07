package com.liratech.helppsico.domain;

import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HorarioPsicologo {
    private UUID id;
    private Psicologo psicologo;
    private LocalDate data;
    private Time hora;
}
