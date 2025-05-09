package com.liratech.helppsico.domain;

import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class HorarioPsicologo {
    private UUID id;
    private Psicologo psicologo;
    private List<Horario> horarios;
}
