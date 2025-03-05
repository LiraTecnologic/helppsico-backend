package com.liratech.helppsico.entrypoint.dto.documento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RelatorioPsicologicoDto extends DocumentoDto{
    private String solicitante;
    private String objetivo;
    private String historico;
    private String procedimentosUtilizados;
    private String descricaoResultados;
    private String conclusao;
    private String recomendacoes;
    private String sigilo;
}