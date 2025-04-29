package com.liratech.helppsico.entrypoint.dto.psicologo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProntuarioDto {
    @JsonProperty("id")
    private UUID id;

    @NotNull(message = "O psicologo é obrigatório")
    @JsonProperty("psicologo")
    private PsicologoDto psicologo;

    @NotNull(message = "O paciente é obrigatório")
    @JsonProperty("paciente")
    private PacienteDto paciente;

    @NotBlank(message = "O titulo é obrigatório")
    @JsonProperty("titulo")
    private String titulo;

    @NotBlank(message = "O conteudo é obrigatório")
    @JsonProperty("conteudo")
    private String conteudo;
}
