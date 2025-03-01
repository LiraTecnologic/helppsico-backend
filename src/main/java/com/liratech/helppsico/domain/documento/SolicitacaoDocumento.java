package com.liratech.helppsico.domain.documento;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
