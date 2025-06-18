package com.liratech.helppsico.domain.documento;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class LaudoPsicologico extends Documento{
    private String solicitante;
    private String objetivo;
    private String historico;
    private String procedimentosUtilizados;
    private String descricaoResultados;
    private String conclusao;
    private String respostaDemanda;
    private String recomendacoes;
    private String sigilo;

    public LaudoPsicologico(UUID id, Paciente paciente, Psicologo psicologo,
                            LocalDate dataEmissao, LocalDate dataValidade,
                            String assinaturaPsicologo, String solicitante,
                            String objetivo, String historico, String procedimentosUtilizados,
                            String descricaoResultados, String conclusao, String respostaDemanda,
                            String recomendacoes, String sigilo){
        super(id, paciente, psicologo, dataEmissao, dataValidade, assinaturaPsicologo);
        this.solicitante = solicitante;
        this.objetivo = objetivo;
        this.historico = historico;
        this.procedimentosUtilizados = procedimentosUtilizados;
        this.descricaoResultados = descricaoResultados;
        this.conclusao = conclusao;
        this.respostaDemanda = respostaDemanda;
        this.recomendacoes = recomendacoes;
        this.sigilo = sigilo;

    }
}
