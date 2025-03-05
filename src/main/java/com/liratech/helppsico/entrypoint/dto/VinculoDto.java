package com.liratech.helppsico.entrypoint.dto;

import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VinculoDto {
    private UUID id;
    private PsicologoDto psicologo;
    private PacienteDto paciente;
    private StatusVinculoDto status;
}
