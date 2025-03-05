package com.liratech.helppsico.entrypoint.dto;

import com.liratech.helppsico.entrypoint.dto.psicologo.Psicologo;
import lombok.Builder;

import java.util.UUID;

@Builder
public class VinculoDto {
    private UUID id;
    private Psicologo psicologo;
    private Paciente paciente;
    private StatusVinculo status;
}
