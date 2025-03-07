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
public class ParecerPsicologicoDto extends DocumentoDto{


    @JsonProperty("solicitante")
    private String solicitante;

    @JsonProperty("objetivo")
    private String objetivo;

    @JsonProperty("conclusao")
    private String conclusao;

    @JsonProperty("sigilo")
    private String sigilo;

    @JsonProperty("contextualização")
    private String contextualizacao;

    @JsonProperty("fundamentacao")
    private String fundamentacao;

    @JsonProperty("analiseDoCaso")
    private String analiseDoCaso;

    public ParecerPsicologicoDto (UUID id, PacienteDto paciente, PsicologoDto psicologo, LocalDate dataEmissao, LocalDate dataValidade, String assinaturaPsicologo, String solicitante, String objetivo, String conclusao, String sigilo, String contextualizacao, String fundamentacao, String analiseDoCaso){
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
