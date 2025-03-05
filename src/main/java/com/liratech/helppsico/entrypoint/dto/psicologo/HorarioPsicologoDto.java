package com.liratech.helppsico.entrypoint.dto.psicologo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HorarioPsicologoDto {
    private UUID id;
    private PsicologoDto psicologo;
    private LocalDate data;
    private Time hora;
}
