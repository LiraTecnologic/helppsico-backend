package com.liratech.helppsico.domain;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Prontuario {
    private UUID id;
    private Psicologo psicologo;
    private Paciente paciente;
    private String titulo;
    private String conteudo;

    public void alterarDado(Prontuario prontuarioAlterado) {
        this.titulo = prontuarioAlterado.getTitulo();
        this.conteudo = prontuarioAlterado.getConteudo();
    }
}
