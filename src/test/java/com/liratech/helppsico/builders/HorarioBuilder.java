package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.DiaSemana;
import com.liratech.helppsico.domain.Horario;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioDto;
import com.liratech.helppsico.infrastructure.repositories.entities.HorarioEntity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HorarioBuilder {

    public static Horario criarHorarioDomain(){
        return Horario.builder()
                .id(UUID.randomUUID())
                .diaSemana(DiaSemana.SABADO)
                .inicio(LocalTime.now())
                .fim(LocalTime.now().plusMinutes(30))
                .disponivel(true)
                .build();
    }

    public static HorarioEntity criarHorarioEntity(){
        return HorarioEntity.builder()
                .id(UUID.randomUUID())
                .diaSemana(DiaSemana.SABADO)
                .inicio(LocalTime.now())
                .fim(LocalTime.now().plusMinutes(30))
                .disponivel(true)
                .build();
    }

    public static HorarioDto criarHorarioDto(){
        return HorarioDto.builder()
                .id(UUID.randomUUID())
                .diaSemana(DiaSemana.SABADO)
                .inicio(LocalTime.now())
                .fim(LocalTime.now().plusMinutes(30))
                .disponivel(true)
                .build();
    }

    public static List<Horario> criarListaHorarioDomain() {
        List<Horario> horarioList = new ArrayList<>();

        for(int i =0; i<3; i++){
            horarioList.add(criarHorarioDomain());
        }

        return horarioList;
    }
    public static List<HorarioEntity> criarListaHorarioEntity() {
        List<HorarioEntity> horarioList = new ArrayList<>();

        for(int i =0; i<3; i++){
            horarioList.add(criarHorarioEntity());
        }

        return horarioList;
    }

    public static List<HorarioDto> criarListaHorarioDto() {
        List<HorarioDto> horarioList = new ArrayList<>();

        for(int i =0; i<3; i++){
            horarioList.add(criarHorarioDto());
        }

        return horarioList;
    }

    public static Horario criarHorarioDiaQuarta(){
        return Horario.builder()
                .id(UUID.randomUUID())
                .diaSemana(DiaSemana.QUARTA_FEIRA)
                .inicio(LocalTime.now())
                .fim(LocalTime.now().plusMinutes(30))
                .disponivel(true)
                .build();
    }
}
