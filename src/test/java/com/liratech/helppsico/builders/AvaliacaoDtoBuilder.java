package com.liratech.helppsico.builders;

import com.liratech.helppsico.entrypoint.dto.psicologo.AvaliacaoDto;

import java.util.UUID;

public class AvaliacaoDtoBuilder {
    public static AvaliacaoDto criarAvaliacaoDto(){
        return AvaliacaoDto.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoDtoBuilder.criarPsicologoDto())
                .nota(4.5)
                .comentario("Mau psicologo")
                .build();
    }
}
