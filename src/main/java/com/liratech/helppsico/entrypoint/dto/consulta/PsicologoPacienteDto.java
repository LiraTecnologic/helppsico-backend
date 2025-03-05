package com.liratech.helppsico.entrypoint.dto.consulta;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PsicologoPacienteDto{
    private UUID idPsicologo;
    private UUID idPaciente;
}
