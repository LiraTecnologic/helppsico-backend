package com.liratech.helppsico.builders;

import com.liratech.helppsico.entrypoint.dto.StatusVinculoDto;
import com.liratech.helppsico.entrypoint.dto.VinculoDto;

import java.util.UUID;

public class VinculoDtoBuilder {
    public static VinculoDto criarVinculoDto(){
        return VinculoDto.builder()
                .id(UUID.randomUUID())
                .paciente(PacienteDtoBuilder.criarPacienteDto())
                .psicologo(PsicologoDtoBuilder.criarPsicologoDto())
                .status(StatusVinculoDto.ATIVO)
                .build();
    }
}
