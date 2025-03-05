package com.liratech.helppsico.entrypoint.dto.documento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ParecerPsicologicoDto extends DocumentoDto{
    private String solicitante;
    private String objetivo;
    private String conclusao;
    private String sigilo;
    private String contextualizacao;
    private String fundamentacao;
    private String analiseDoCaso;
}
