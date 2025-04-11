package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.StatusVinculo;
import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.entrypoint.dto.StatusVinculoDto;
import com.liratech.helppsico.entrypoint.dto.VinculoDto;
import com.liratech.helppsico.infrastructure.repositories.entities.StatusVinculoEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.VinculoEntity;

import java.util.UUID;

public class VinculoBuilder {
    public static Vinculo criarVinculo(){
        return Vinculo.builder()
                .id(UUID.randomUUID())
                .paciente(PacienteBuilder.criarPaciente())
                .psicologo(PsicologoBuilder.criarPsicologo())
                .status(StatusVinculo.ATIVO)
                .build();
    }

    public static VinculoDto criarVinculoDto(){
        return VinculoDto.builder()
                .id(UUID.randomUUID())
                .paciente(PacienteBuilder.criarPacienteDto())
                .psicologo(PsicologoBuilder.criarPsicologoDto())
                .status(StatusVinculoDto.ATIVO)
                .build();
    }

    public static VinculoEntity criarVinculoEntity() {
        return VinculoEntity.builder()
                .id(UUID.randomUUID())
                .paciente(PacienteBuilder.criarPacienteEntity())
                .psicologo(PsicologoBuilder.criarPsicologoEntity())
                .status(StatusVinculoEntity.ATIVO)
                .build();
    }
}
