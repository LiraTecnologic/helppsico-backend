package com.liratech.helppsico.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusVinculo {
    PENDENTE(1),
    ATIVO(2),
    INATIVO(3);

    private final Integer codigo;
}
