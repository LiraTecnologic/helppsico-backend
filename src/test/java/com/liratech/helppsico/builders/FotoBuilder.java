package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Foto;
import com.liratech.helppsico.entrypoint.dto.FotoDto;

import java.util.UUID;

public class FotoBuilder {
    public static Foto criarFotoDomainPaciente(){
        return Foto.builder()
                .psicologo(null)
                .paciente(PacienteBuilder.criarPaciente())
                .fotoUrl("url-salvo")
                .build();
    }

    public static Foto criarFotoDomainPsicologo(){
        return Foto.builder()
                .psicologo(PsicologoBuilder.criarPsicologo())
                .paciente(null)
                .fotoUrl("url-salvo")
                .build();
    }

    public static FotoDto criarFotoDto() {
        return FotoDto.builder()
                .psicologo(PsicologoBuilder.criarPsicologoDto())
                .paciente(PacienteBuilder.criarPacienteDto())
                .fotoUrl("url-salvo")
                .build();
    }
}
