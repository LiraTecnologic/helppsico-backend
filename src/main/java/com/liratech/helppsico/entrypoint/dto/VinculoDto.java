package com.liratech.helppsico.entrypoint.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record VinculoDto(
        UUID id,
        Psicologo psicologo,
        Paciente paciente,
        StatusVinculo status
) {
}
