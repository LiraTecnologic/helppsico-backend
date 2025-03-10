package com.liratech.helppsico.entrypoint.dto.documento;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import java.time.LocalDate;
import java.util.UUID;


@Getter
public class LaudoPsicologicoDto extends DocumentoDto{

    @NotBlank(message = "O solicitante é obrigatório")
    @JsonProperty("solicitante")
    private String solicitante;

    @NotBlank(message = "O objetivo é obrigatório")
    @JsonProperty("objetivo")
    private String objetivo;

    @NotBlank(message = "O histórico é obrigatório")
    @JsonProperty("historico")
    private String historico;

    @NotBlank(message = "Os procedimentos utilizados são obrigatórios")
    @JsonProperty("procedimentosUtilizados")
    private String procedimentosUtilizados;

    @NotBlank(message = "A descrição de resultados é obrigatória")
    @JsonProperty("descricaoResultados")
    private String descricaoResultados;

    @NotBlank(message = "A conclusão é obrigatória")
    @JsonProperty("conclusao")
    private String conclusao;

    @NotBlank(message = "A resposta e demanda é obrigatória")
    @JsonProperty("respostaDemanda")
    private String respostaDemanda;

    @NotBlank(message = "As recomendações são obrigatórias")
    @JsonProperty("recomendacoes")
    private String recomendacoes;

    @NotBlank(message = "O sigílo é obrigatório")
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
