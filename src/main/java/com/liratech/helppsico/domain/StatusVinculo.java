package com.liratech.helppsico.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusVinculo {
    PENDENTE(1, "Pendente"),
    ATIVO(2, "Ativo"),
    INATIVO(3, "Inativo");

    private final Integer codigo;
    private final String descricao;
}
