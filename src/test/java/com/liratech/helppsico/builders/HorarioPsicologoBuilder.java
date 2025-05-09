package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.HorarioPsicologo;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioPsicologoDto;
import com.liratech.helppsico.infrastructure.repositories.entities.HorarioPsicologoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HorarioPsicologoBuilder {
    public static HorarioPsicologo criarHorarioPsicologo(){
        return HorarioPsicologo.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologo())
                .horarios(HorarioBuilder.criarListaHorarioDomain())
                .build();
    }

    public static HorarioPsicologoDto criarHorarioPsicologoDto (){
        return HorarioPsicologoDto.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologoDto())
                .horarios(HorarioBuilder.criarListaHorarioDto())
                .build();
    }

    public static HorarioPsicologoEntity criarHorarioPsicologoEntity() {
        return HorarioPsicologoEntity.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologoEntity())
                .horarios(HorarioBuilder.criarListaHorarioEntity())
                .build();
    }

    public static Page<HorarioPsicologo> criarPageDeHorarioPsicologos() {
        List<HorarioPsicologo> horarioPsicologoListList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            horarioPsicologoListList.add(criarHorarioPsicologo());
        }

        return transformarListaEmPagina(horarioPsicologoListList, PageRequest.of(0,10));
    }

    private static Page<HorarioPsicologo> transformarListaEmPagina(List<HorarioPsicologo> lista, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), lista.size());

        List<HorarioPsicologo> sublist = lista.subList(start, end);

        return new PageImpl<>(sublist, pageable, lista.size());
    }
}
