package com.liratech.helppsico.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class VinculoDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("psicologo")
    private PsicologoDto psicologo;

    @JsonProperty("paciente")
    private PacienteDto paciente;

    @NotBlank(message = "O status é obrigatório")
    @JsonProperty("status")
    @Enumerated(EnumType.STRING)
    private StatusVinculoDto status;
}
