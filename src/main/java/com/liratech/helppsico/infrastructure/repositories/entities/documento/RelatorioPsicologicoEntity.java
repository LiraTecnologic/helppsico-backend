package com.liratech.helppsico.infrastructure.repositories.entities.documento;

import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "RelatorioPsicologico")
@Table(name = "relatorios_psicologicos")
@NoArgsConstructor
@Getter
public class RelatorioPsicologicoEntity extends DocumentoEntity {

    private String solicitante;
    private String objetivo;
    private String historico;

    @Column(name = "procedimentos_utilizados")
    private String procedimentosUtilizados;

    @Column(name = "descricao_resultados")
    private String descricaoResultados;

    private String conclusao;
    private String recomendacoes;
    private String sigilo;

    public RelatorioPsicologicoEntity (UUID id, PacienteEntity paciente, PsicologoEntity psicologo, LocalDate dataEmissao, LocalDate dataValidade, String assinaturaPsicologo, String solicitante, String objetivo, String historico, String procedimentosUtilizados, String descricaoResultados, String conclusao, String recomendacoes, String sigilo){
        this.id = id;
        this.paciente = paciente;
        this.psicologo = psicologo;
        this.dataEmissao = dataEmissao;
        this.dataValidade = dataValidade;
        this.assinaturaPsicologo = assinaturaPsicologo;
        this.solicitante = solicitante;
        this.objetivo = objetivo;
        this.historico = historico;
        this.procedimentosUtilizados = procedimentosUtilizados;
        this.descricaoResultados = descricaoResultados;
        this.conclusao = conclusao;
        this.recomendacoes = recomendacoes;
        this.sigilo = sigilo;
    }

}
