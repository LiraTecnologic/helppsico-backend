package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.AvaliacaoDto;
import com.liratech.helppsico.infrastructure.repositories.entities.AvaliacaoEntity;

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
}
