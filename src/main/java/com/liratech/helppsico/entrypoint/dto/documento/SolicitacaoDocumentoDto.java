package com.liratech.helppsico.entrypoint.dto.documento;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SolicitacaoDocumentoDto {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("paciente")
    private PacienteDto paciente;

    @JsonProperty("psicologo")
    private PsicologoDto psicologo;

    @JsonProperty("tipoDocumento")
    private TipoDocumentoDto tipoDocumento;
}
