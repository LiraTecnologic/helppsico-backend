package com.liratech.helppsico.entrypoint.dto;

import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class DataConsultaDto {
    private HorarioDto horario;
    private LocalDate data;
}
