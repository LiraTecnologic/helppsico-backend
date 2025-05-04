package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Foto;
import com.liratech.helppsico.entrypoint.dto.FotoDto;
import com.liratech.helppsico.infrastructure.repositories.entities.FotoEntity;

import java.util.UUID;

public class FotoBuilder {
    public static Foto criarFotoDomain(){
        return Foto.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologo())
                .paciente(PacienteBuilder.criarPaciente())
                .fotoUrl("url-salvo")
                .build();
    }

    public static FotoDto criarFotoDto() {
        return FotoDto.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologoDto())
                .paciente(PacienteBuilder.criarPacienteDto())
                .fotoUrl("url-salvo")
                .build();
    }

    public static FotoEntity criarFotoEntity() {
        return FotoEntity.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologoEntity())
                .paciente(PacienteBuilder.criarPacienteEntity())
                .fotoUrl("url-salvo")
                .build();
    }
}
