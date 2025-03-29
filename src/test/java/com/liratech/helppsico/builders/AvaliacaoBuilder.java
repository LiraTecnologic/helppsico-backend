package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.entrypoint.dto.psicologo.AvaliacaoDto;

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
}
