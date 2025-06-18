package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.StatusVinculo;
import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.entrypoint.dto.VinculoDto;
import com.liratech.helppsico.infrastructure.repositories.entities.VinculoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VinculoBuilder {
    public static Vinculo criarVinculo(){
        return Vinculo.builder()
                .id(UUID.randomUUID())
                .paciente(PacienteBuilder.criarPaciente())
                .psicologo(PsicologoBuilder.criarPsicologo())
                .status(StatusVinculo.PENDENTE)
                .build();
    }

    public static VinculoDto criarVinculoDto(){
        return VinculoDto.builder()
                .id(UUID.randomUUID())
                .paciente(PacienteBuilder.criarPacienteDto())
                .psicologo(PsicologoBuilder.criarPsicologoDto())
                .status(StatusVinculo.PENDENTE)
                .build();
    }

    public static VinculoEntity criarVinculoEntity() {
        return VinculoEntity.builder()
                .id(UUID.randomUUID())
                .paciente(PacienteBuilder.criarPacienteEntity())
                .psicologo(PsicologoBuilder.criarPsicologoEntity())
                .status(StatusVinculo.PENDENTE)
                .build();
    }

    public static Page<Vinculo> criarPageDeVinculos() {
        List<Vinculo> vinculoList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            vinculoList.add(criarVinculo());
        }

        return transformarListaEmPagina(vinculoList, PageRequest.of(0,10));
    }

    public static Page<VinculoEntity> criarPageDeVinculosEntity () {
        List<VinculoEntity> vinculoList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            vinculoList.add(criarVinculoEntity());
        }

        return transformarListaEmPaginaEntity(vinculoList, PageRequest.of(0,10));
    }

    private static Page<Vinculo> transformarListaEmPagina(List<Vinculo> lista, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), lista.size());

        List<Vinculo> sublist = lista.subList(start, end);

        return new PageImpl<>(sublist, pageable, lista.size());
    }

    private static Page<VinculoEntity> transformarListaEmPaginaEntity(List<VinculoEntity> lista, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), lista.size());

        List<VinculoEntity> sublist = lista.subList(start, end);

        return new PageImpl<>(sublist, pageable, lista.size());
    }
}
