package com.liratech.helppsico.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class FotoDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("psicologo")
    private PsicologoDto psicologo;

    @JsonProperty("paciente")
    private PacienteDto paciente;

    @NotBlank(message = "O url da foto é obrigatório.")
    @JsonProperty("fotoUrl")
    private String fotoUrl;
}
