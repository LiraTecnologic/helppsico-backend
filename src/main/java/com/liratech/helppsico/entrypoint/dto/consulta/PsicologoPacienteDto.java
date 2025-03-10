package com.liratech.helppsico.entrypoint.dto.consulta;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class PsicologoPacienteDto{

    @NotNull(message = "O id do psicologo é obrigatório")
    @JsonProperty("idPsicologo")
    private UUID idPsicologo;

    @NotNull(message = "O id do paciente é obrigatório")
    @JsonProperty("idPaciente")
    private UUID idPaciente;
}
