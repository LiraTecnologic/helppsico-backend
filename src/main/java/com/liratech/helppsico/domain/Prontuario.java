package com.liratech.helppsico.domain;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Prontuario {
    private UUID id;
    private Psicologo psicologo;
    private Paciente paciente;
    private String titulo;
    private String conteudo;
}
