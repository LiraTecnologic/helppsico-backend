package com.liratech.helppsico.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusPsicologo {
    PENDENTE (1, "Pendente"),
    APROVADO (2, "Aprovado"),
    NAO_APROVADO (3, "Não aprovado");

    private final Integer codigo;
    private final String descricao;
}
