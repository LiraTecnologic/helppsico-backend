package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Avaliacao;

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
}
