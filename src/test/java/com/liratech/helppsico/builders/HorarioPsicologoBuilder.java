package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.HorarioPsicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioPsicologoDto;
import com.liratech.helppsico.infrastructure.repositories.entities.HorarioPsicologoEntity;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class HorarioPsicologoBuilder {
    public static HorarioPsicologo criarHorarioPsicologo(){
        return HorarioPsicologo.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologo())
                .data(LocalDate.now())
                .hora(Time.valueOf(LocalTime.now()))
                .build();
    }

    public static HorarioPsicologoDto criarHorarioPsicologoDto (){
        return HorarioPsicologoDto.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologoDto())
                .data(LocalDate.now())
                .hora(Time.valueOf(LocalTime.now()))
                .build();
    }

    public static HorarioPsicologoEntity criarHorarioPsicologoEntity() {
        return HorarioPsicologoEntity.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologoEntity())
                .data(LocalDate.now())
                .hora(Time.valueOf(LocalTime.now()))
                .build();
    }
}
