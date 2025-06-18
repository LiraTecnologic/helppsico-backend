package com.liratech.helppsico.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusVinculo {
    PENDENTE(1, "Pendente"),
    ATIVO(2, "Ativo"),
    RECUSADO(3, "Recusado");

    private final Integer codigo;
    private final String descricao;
}
