package com.liratech.helppsico.entrypoint.dto.consulta;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PsicologoPacienteDto{

    @JsonProperty("idPsicologo")
    private UUID idPsicologo;

    @JsonProperty("idPaciente")
    private UUID idPaciente;
}
