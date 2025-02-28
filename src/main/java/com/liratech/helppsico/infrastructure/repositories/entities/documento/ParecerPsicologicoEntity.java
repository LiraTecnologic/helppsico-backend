package com.liratech.helppsico.infrastructure.repositories.entities.documento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "ParecerPsicologico")
@Table(name = "pareceres_psicologicos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ParecerPsicologicoEntity {

    private String solicitante;
    private String objetivo;
    private String conclusao;
    private String sigilo;
    private String contextualizacao;
    private String fundamentacao;

    @Column(name = "analise_do_caso")
    private String analiseDoCaso;
}
