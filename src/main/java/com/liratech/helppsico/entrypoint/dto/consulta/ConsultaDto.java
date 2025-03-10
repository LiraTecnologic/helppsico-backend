package com.liratech.helppsico.entrypoint.dto.consulta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ConsultaDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("psicologo")
    private PsicologoDto psicologo;

    @JsonProperty("paciente")
    private PacienteDto paciente;

    @NotNull(message = "O valor é obrigatório")
    @PositiveOrZero(message = "O valor tem que ser maior ou igual a zero")
    @JsonProperty("valor")
    private BigDecimal valor;

    @NotNull(message = "A data e hora são obrigatórios")
    @JsonProperty("dataHora")
    private LocalDateTime dataHora;

    @JsonProperty("endereco")
    private EnderecoDto endereco;

    @NotNull(message = "O valor de 'finalizada' deve ser verdadeiro ou falso.")
    @JsonProperty("finalizada")
    private Boolean finalizada;
}
