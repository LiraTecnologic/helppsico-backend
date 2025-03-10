package com.liratech.helppsico.entrypoint.dto.psicologo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class HorarioPsicologoDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("psicologo")
    private PsicologoDto psicologo;

    @NotNull(message = "A data é obrigatória")
    @JsonProperty("data")
    private LocalDate data;

    @NotNull(message = "A hora é obrigatória")
    @JsonProperty("hora")
    private Time hora;
}
