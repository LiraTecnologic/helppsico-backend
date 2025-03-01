package com.liratech.helppsico.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Avaliacao {
    private UUID id;
    private Psicologo psicologo;
    private Paciente paciente;
    private BigDecimal nota;
    private String comentario;
}
