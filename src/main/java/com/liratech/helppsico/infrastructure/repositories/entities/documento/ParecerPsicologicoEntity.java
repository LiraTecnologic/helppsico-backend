package com.liratech.helppsico.infrastructure.repositories.entities.documento;

import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "ParecerPsicologico")
@Table(name = "pareceres_psicologicos")
@NoArgsConstructor
@Getter
@ToString
public class ParecerPsicologicoEntity extends DocumentoEntity {

    private String solicitante;
    private String objetivo;
    private String conclusao;
    private String sigilo;
    private String contextualizacao;
    private String fundamentacao;

    @Column(name = "analise_do_caso")
    private String analiseDoCaso;

    public ParecerPsicologicoEntity (UUID id, PacienteEntity paciente, PsicologoEntity psicologo, LocalDate dataEmissao, LocalDate dataValidade, String assinaturaPsicologo, String solicitante, String objetivo, String conclusao, String sigilo, String contextualizacao, String fundamentacao, String analiseDoCaso){
        super(id, paciente, psicologo, dataEmissao, dataValidade, assinaturaPsicologo);
        this.solicitante = solicitante;
        this.objetivo = objetivo;
        this.conclusao = conclusao;
        this.sigilo = sigilo;
        this.contextualizacao = contextualizacao;
        this.fundamentacao = fundamentacao;
        this.analiseDoCaso = analiseDoCaso;
    }

}
