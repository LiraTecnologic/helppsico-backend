package com.liratech.helppsico.entrypoint.dto.documento;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DocumentoDto {

    @JsonProperty("id")
    protected UUID id;

    @NotNull(message = "O paciente é obrigatório")
    @JsonProperty("paciente")
    protected PacienteDto paciente;

    @NotNull(message = "O psicologo é obrigatório")
    @JsonProperty("psicologo")
    protected PsicologoDto psicologo;

    @NotNull(message = "A data de emissão é obrigatória")
    @JsonProperty("dataEmissao")
    protected LocalDate dataEmissao;

    @NotNull(message = "A data de validade é obrigatória")
    @JsonProperty("dataValidade")
    protected LocalDate dataValidade;

    @NotBlank(message = "A assinatura do psicólogo é obrigatória")
    @JsonProperty("assinaturaPsicologo")
    protected String assinaturaPsicologo;


}
