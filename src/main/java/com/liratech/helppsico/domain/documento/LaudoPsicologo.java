package com.liratech.helppsico.domain.documento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class LaudoPsicologo {
    private String solicitante;
    private String objetivo;
    private String hisotico;
    private String procedimentosUtilizados;
    private String descricaoResultados;
    private String conclusao;
    private String respostaDemanda;
    private String recomendacoes;
    private String sigilo;
}
