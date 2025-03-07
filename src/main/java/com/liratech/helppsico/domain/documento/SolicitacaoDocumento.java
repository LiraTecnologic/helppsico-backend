package com.liratech.helppsico.domain.documento;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class SolicitacaoDocumento {
    private UUID id;
    private Psicologo psicologo;
    private Paciente paciente;
    private TipoDocumento tipoDocumento;
}
