package com.liratech.helppsico.infrastructure.repositories.entities.documento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "LaudoPsicologico")
@Table(name = "laudos_psicologicos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LaudoPsicologicoEntity {

    private String solicitante;
    private String objetivo;
    private String historico;

    @Column(name = "procedimentos_utilizados")
    private String procedimentosUtilizados;

    @Column(name = "descricao_resultados")
    private String descricaoResultados;

    private String conclusao;

    @Column(name = "resposta_demanda")
    private String respostaDemanda;

    private String recomendacoes;
    private String sigilo;



}
