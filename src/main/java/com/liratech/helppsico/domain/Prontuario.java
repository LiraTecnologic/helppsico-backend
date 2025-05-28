package com.liratech.helppsico.domain;

import lombok.*;

import java.time.LocalDate;
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
    private Consulta consulta;
    private String titulo;
    private String conteudo;
    private LocalDate dataCriacao;
    private LocalDate dataEdicao;

    public void alterarDado(Prontuario prontuarioAlterado) {
        this.titulo = prontuarioAlterado.getTitulo();
        this.conteudo = prontuarioAlterado.getConteudo();
        this.dataEdicao = prontuarioAlterado.getDataEdicao();
    }
}
