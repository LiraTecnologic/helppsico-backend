package com.liratech.helppsico.entrypoint.dto.documento;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DocumentoDto {

    @JsonProperty("id")
    protected UUID id;

    @JsonProperty("paciente")
    protected PacienteDto paciente;

    @JsonProperty("psicologo")
    protected PsicologoDto psicologo;

    @JsonProperty("dataEmissao")
    protected LocalDate dataEmissao;

    @JsonProperty("dataValidade")
    protected LocalDate dataValidade;

    @JsonProperty("assinaturaPsicologo")
    protected String assinaturaPsicologo;


}
