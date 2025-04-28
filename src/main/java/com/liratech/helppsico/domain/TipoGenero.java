package com.liratech.helppsico.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoGenero {
    MASCULINO(1, "Masculino"),
    FEMININO(2, "Feminino"),
    OUTRO(3, "Masculino");

    private final Integer codigo;
    private final String descricao;
}
