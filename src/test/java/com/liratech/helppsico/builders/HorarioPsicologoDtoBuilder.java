package com.liratech.helppsico.builders;

import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioPsicologoDto;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class HorarioPsicologoDtoBuilder {
    public static HorarioPsicologoDto criarHorarioPsicologoDto (){
        return HorarioPsicologoDto.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoDtoBuilder.criarPsicologoDto())
                .data(LocalDate.now())
                .hora(Time.valueOf(LocalTime.now()))
                .build();
    }
}
