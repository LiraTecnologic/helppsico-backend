package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.StatusVinculo;
import com.liratech.helppsico.domain.Vinculo;

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
}
