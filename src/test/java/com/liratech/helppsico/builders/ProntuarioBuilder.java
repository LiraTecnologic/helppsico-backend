package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.entrypoint.dto.psicologo.ProntuarioDto;

import java.util.UUID;

public class ProntuarioBuilder {
    public static Prontuario criarProntuario(){
        return Prontuario.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologo())
                .paciente(PacienteBuilder.criarPaciente())
                .titulo("Teste")
                .conteudo("teste")
                .build();
    }

    public static ProntuarioDto criarProntuarioDto(){
        return ProntuarioDto.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologoDto())
                .paciente(PacienteBuilder.criarPacienteDto())
                .titulo("Teste")
                .conteudo("teste")
                .build();
    }
}
