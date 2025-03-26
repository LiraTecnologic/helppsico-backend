package com.liratech.helppsico.domain.documento;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoDocumento {
    ATESTADO(1),
    DECLARACAO(2),
    RELATORIO_PSICOLOGICO(3),
    RELATORIO_MULTIPROFISSIONAL(4),
    LAUDO_PSICOLOGICO(5),
    PARECER_PSICOLOGICO(6);

    private final Integer codigo;
}
