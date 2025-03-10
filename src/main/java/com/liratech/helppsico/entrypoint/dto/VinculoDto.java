package com.liratech.helppsico.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class VinculoDto {

    @JsonProperty("id")
    private UUID id;

    @NotNull(message = "O psicologo é obrigatório")
    @JsonProperty("psicologo")
    private PsicologoDto psicologo;

    @NotNull(message = "O paciente é obrigatório")
    @JsonProperty("paciente")
    private PacienteDto paciente;

    @NotNull(message = "O status é obrigatório")
    @JsonProperty("status")
    @Enumerated(EnumType.STRING)
    private StatusVinculoDto status;
}
