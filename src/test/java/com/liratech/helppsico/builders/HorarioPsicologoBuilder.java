package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.HorarioPsicologo;

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
}
