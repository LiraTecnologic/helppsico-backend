package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.entrypoint.dto.psicologo.ProntuarioDto;
import com.liratech.helppsico.infrastructure.repositories.entities.ConsultaEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.ProntuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProntuarioBuilder {
    public static Prontuario criarProntuario(){
        return Prontuario.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologo())
                .paciente(PacienteBuilder.criarPaciente())
                .titulo("Teste")
                .conteudo("teste")
                .build();
    }

    public static ProntuarioDto criarProntuarioDto(){
        return ProntuarioDto.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologoDto())
                .paciente(PacienteBuilder.criarPacienteDto())
                .titulo("Teste")
                .conteudo("teste")
                .build();
    }

    public static ProntuarioEntity criarProntuarioEntity() {
        return ProntuarioEntity.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologoEntity())
                .paciente(PacienteBuilder.criarPacienteEntity())
                .titulo("Teste")
                .conteudo("teste")
                .build();
    }

    public static List<ProntuarioEntity> criarListaProntuarioEntity() {
        List<ProntuarioEntity> prontuarioEntities = new ArrayList<>();

        for(int i =0; i<3; i++){
            prontuarioEntities.add(criarProntuarioEntity());
        }

        return prontuarioEntities;
    }

    public static Page<ProntuarioEntity> criarPageProntuarioEntity() {
        Pageable pageable = PageRequest.of(0, 10);
        return transformarListaEmPaginaEntity(criarListaProntuarioEntity(), pageable);
    }

    private static Page<ProntuarioEntity> transformarListaEmPaginaEntity(List<ProntuarioEntity> lista, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), lista.size());

        List<ProntuarioEntity> sublist = lista.subList(start, end);

        return new PageImpl<>(sublist, pageable, lista.size());
    }
}
