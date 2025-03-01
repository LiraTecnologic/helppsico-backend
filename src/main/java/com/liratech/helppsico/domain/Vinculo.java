package com.liratech.helppsico.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Vinculo {
    private UUID id;
    private Paciente paciente;
    private Psicologo psicologo;
    private StatusVinculo status;
}
