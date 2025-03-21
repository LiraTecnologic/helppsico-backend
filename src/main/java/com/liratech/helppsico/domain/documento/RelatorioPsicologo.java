package com.liratech.helppsico.domain.documento;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class RelatorioPsicologo extends Documento{
    private String solicitante;
    private String objetivo;
    private String historico;
    private String procedimentosUtilizados;
    private String descricaoResultados;
    private String conclusao;
    private String recomendacoes;
    private String sigilo;

    public RelatorioPsicologo (UUID id, Paciente paciente, Psicologo psicologo,
                               LocalDate dataEmissao, LocalDate dataValidade,
                               String assinaturaPsicologo, String solicitante,
                               String objetivo, String historico, String procedimentosUtilizados,
                               String descricaoResultados, String conclusao,
                               String recomendacoes, String sigilo){
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
