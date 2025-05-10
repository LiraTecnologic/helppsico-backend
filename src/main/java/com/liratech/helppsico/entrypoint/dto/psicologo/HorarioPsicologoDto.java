package com.liratech.helppsico.entrypoint.dto.psicologo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class HorarioPsicologoDto {

    @JsonProperty("id")
    private UUID id;

    @NotNull(message = "O psicologo é obrigatório")
    @JsonProperty("psicologo")
    private PsicologoDto psicologo;

    @NotEmpty(message = "A lista de horários nao pode ser vazia")
    @Valid
    private List<HorarioDto> horarios;
}
