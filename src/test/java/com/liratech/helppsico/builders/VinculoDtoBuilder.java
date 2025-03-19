package com.liratech.helppsico.builders;

import java.util.UUID;

public class VinculoDtoBuilder {
    public static VinculoDto criarVinculoDto(){
        return VinculoDto.builder()
                .id(UUID.randomUUID())
                .paciente(PacienteDtoBuilder.criarPacienteDto())
                .psicologo(PacienteDtoBuilder.criarPacienteDto())
                .status(StatusVinculoDto.ATIVO)
                .build();
    }
}
