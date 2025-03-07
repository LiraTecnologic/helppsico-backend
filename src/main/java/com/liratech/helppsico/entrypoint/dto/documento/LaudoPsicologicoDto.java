package com.liratech.helppsico.entrypoint.dto.documento;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;


@NoArgsConstructor
@Getter
public class LaudoPsicologicoDto extends DocumentoDto{

    @JsonProperty("solicitante")
    private String solicitante;

    @JsonProperty("objetivo")
    private String objetivo;

    @JsonProperty("historico")
    private String historico;

    @JsonProperty("procedimentosUtilizados")
    private String procedimentosUtilizados;

    @JsonProperty("descricaoResultados")
    private String descricaoResultados;

    @JsonProperty("conclusao")
    private String conclusao;

    @JsonProperty("respostaDemanda")
    private String respostaDemanda;

    @JsonProperty("recomendacoes")
    private String recomendacoes;

    @JsonProperty("sigilo")
    private String sigilo;

    public LaudoPsicologicoDto (UUID id, PacienteDto paciente, PsicologoDto psicologo, LocalDate dataEmissao, LocalDate dataValidade, String assinaturaPsicologo, String solicitante, String objetivo, String historico, String procedimentosUtilizados, String descricaoResultados, String conclusao, String respostaDemanda, String recomendacoes, String sigilo){
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
