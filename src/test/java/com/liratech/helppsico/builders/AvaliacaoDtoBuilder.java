package com.liratech.helppsico.builders;

import java.util.UUID;

public class AvaliacaoDtoBuilder {
    public static AvaliacaoDto criarAvaliacaoDto(){
        return AvaliacaoDto.builder()
                .id(UUID.randomUUID())
                .psicologoDto(PsicologoDtoBuilder.criarPsicologoDto())
                .nota(4.5)
                .comentario("Mau psicologo")
                .build();
    }
}
