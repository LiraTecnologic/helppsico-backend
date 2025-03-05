package com.liratech.helppsico.entrypoint.dto.consulta;

import lombok.Builder;

import java.util.UUID;

@Builder
public class PsicologoPacienteDto{
    private UUID idPsicologo;
    private UUID idPaciente;
}
