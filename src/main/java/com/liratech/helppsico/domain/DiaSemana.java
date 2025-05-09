package com.liratech.helppsico.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DiaSemana {
    DOMINGO(1, "Domingo"),
    SEGUNDA_FEIRA(2, "Segunda-feira"),
    TERCA_FEIRA(3, "Terca-feira"),
    QUARTA_FEIRA(4, "Quarta-feira"),
    QUINTA_FEIRA(5, "Quinta-feira"),
    SEXTA_FEIRA(6, "Sexta-feira"),
    SABADO(7, "Sabado");

    private final Integer codigo;
    private final String descricao;
}
