package com.liratech.helppsico.entrypoint.dto.documento;

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
    private UUID id;
    private PacienteDto paciente;
    private PsicologoDto psicologo;
    private TipoDocumentoDto tipoDocumento;
}
