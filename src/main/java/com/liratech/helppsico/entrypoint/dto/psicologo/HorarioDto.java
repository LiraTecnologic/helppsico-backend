package com.liratech.helppsico.entrypoint.dto.psicologo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liratech.helppsico.domain.DiaSemana;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class HorarioDto {

    @JsonProperty("id")
    private UUID id;

    @NotNull(message = "O dia da semana é obrigatório")
    @JsonProperty("diaSemana")
    private DiaSemana diaSemana;

    @NotNull(message = "A hora de inicio é obrigatório")
    @JsonProperty("inicio")
    private LocalTime inicio;

    @NotNull(message = "A hora do fim é obrigatória")
    @JsonProperty("fim")
    private LocalTime fim;

    @NotNull(message = "O valor de 'disponivel' deve ser verdadeiro ou falso.")
    @JsonProperty("disponivel")
    private Boolean disponivel;

    @JsonProperty("horarioPsicologo")
    private HorarioPsicologoDto horarioPsicologo;
}
