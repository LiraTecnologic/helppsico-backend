package com.liratech.helppsico.entrypoint.dto.consulta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ConsultaDto {

    @JsonProperty("id")
    private UUID id;

    @NotNull(message = "O psicologo é obrigatório")
    @JsonProperty("psicologo")
    private PsicologoDto psicologo;

    @NotNull(message = "O paciente é obrigatório")
    @JsonProperty("paciente")
    private PacienteDto paciente;

    @JsonProperty("valor")
    private BigDecimal valor;

    @NotNull(message = "O horario é obrigatório")
    @JsonProperty("horario")
    private HorarioDto horario;

    @NotNull(message = "A data é obrigatória")
    @JsonProperty("data")
    private LocalDate data;

    @JsonProperty("endereco")
    private EnderecoDto endereco;

    @NotNull(message = "O valor de 'finalizada' deve ser verdadeiro ou falso.")
    @JsonProperty("finalizada")
    private Boolean finalizada;
}
