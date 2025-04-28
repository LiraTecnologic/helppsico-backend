package com.liratech.helppsico.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Consulta {
    private UUID id;
    private Psicologo psicologo;
    private Paciente paciente;
    private LocalDateTime dataHora;
    private BigDecimal valor;
    private Endereco endereco;
    private Boolean finalizada;
}
