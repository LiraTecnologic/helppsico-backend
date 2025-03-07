package com.liratech.helppsico.entrypoint.dto.consulta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import lombok.*;

import java.math.BigDecimal;
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

    @JsonProperty("psicologo")
    private PsicologoDto psicologo;

    @JsonProperty("paciente")
    private PacienteDto paciente;

    @JsonProperty("valor")
    private BigDecimal valor;

    @JsonProperty("dataHora")
    private LocalDateTime dataHora;

    @JsonProperty("endereco")
    private EnderecoDto endereco;

    @JsonProperty("finalizada")
    private Boolean finalizada;
}
