package com.liratech.helppsico.domain.documento;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoDocumento {
    ATESTADO(1, "Atestado"),
    DECLARACAO(2, "Declaração"),
    RELATORIO_PSICOLOGICO(3, "Relatório psicológico"),
    LAUDO_PSICOLOGICO(4, "Laudo psicológico"),
    PARECER_PSICOLOGICO(5, "Parecer psicológico");

    private final Integer codigo;
    private final String descricao;
}
