package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.AvaliacaoDto;
import com.liratech.helppsico.infrastructure.repositories.entities.AvaliacaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AvaliacaoBuilder {
    public static Avaliacao criarAvaliacao() {
        return Avaliacao.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologo())
                .paciente(PacienteBuilder.criarPaciente())
                .nota(4.5)
                .comentario("Bom psicologo")
                .build();
    }

    public static AvaliacaoDto criarAvaliacaoDto(){
        return AvaliacaoDto.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologoDto())
                .paciente(PacienteBuilder.criarPacienteDto())
                .nota(4.5)
                .comentario("Bom psicologo")
                .build();
    }

    public static AvaliacaoEntity criarAvaliacaoEntity(){
        return AvaliacaoEntity.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologoEntity())
                .paciente(PacienteBuilder.criarPacienteEntity())
                .nota(4.5)
                .comentario("Bom psicologo")
                .build();
    }

    public static List<AvaliacaoEntity> criarListaDeAvaliacaoEntity() {
        List<AvaliacaoEntity> avaliacaoList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            avaliacaoList.add(criarAvaliacaoEntity());
        }

        return avaliacaoList;
    }

    public static Page<AvaliacaoEntity> criarPageDeAvaliacoesEntity() {
        List<AvaliacaoEntity> avaliacaoList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            avaliacaoList.add(criarAvaliacaoEntity());
        }

        return transformarListaEmPaginaEntity(avaliacaoList, PageRequest.of(0,10));
    }

    private static Page<AvaliacaoEntity> transformarListaEmPaginaEntity(List<AvaliacaoEntity> lista, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), lista.size());

        List<AvaliacaoEntity> sublist = lista.subList(start, end);

        return new PageImpl<>(sublist, pageable, lista.size());
    }

    public static Page<Avaliacao> criarPageDeAvaliacoes() {
        List<Avaliacao> avaliacaoList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            avaliacaoList.add(criarAvaliacao());
        }

        return transformarListaEmPagina(avaliacaoList, PageRequest.of(0,10));
    }

    private static Page<Avaliacao> transformarListaEmPagina(List<Avaliacao> lista, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), lista.size());

        List<Avaliacao> sublist = lista.subList(start, end);

        return new PageImpl<>(sublist, pageable, lista.size());
    }
}
