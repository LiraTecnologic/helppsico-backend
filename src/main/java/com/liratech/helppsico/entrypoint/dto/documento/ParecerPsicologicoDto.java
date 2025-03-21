package com.liratech.helppsico.entrypoint.dto.documento;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import java.time.LocalDate;
import java.util.UUID;

@Getter
public class  ParecerPsicologicoDto extends DocumentoDto{


    @NotBlank(message = "O solicitante é obrigatório")
    @JsonProperty("solicitante")
    private String solicitante;

    @NotBlank(message = "O objetivo é obrigatório")
    @JsonProperty("objetivo")
    private String objetivo;

    @NotBlank(message = "A conclusão é obrigatória")
    @JsonProperty("conclusao")
    private String conclusao;

    @NotBlank(message = "O sigílo é obrigatório")
    @JsonProperty("sigilo")
    private String sigilo;

    @NotBlank(message = "A contextualização é obrigatória")
    @JsonProperty("contextualização")
    private String contextualizacao;

    @NotBlank(message = "A fundamentação é obrigatória")
    @JsonProperty("fundamentacao")
    private String fundamentacao;

    @NotBlank(message = "A análise do caso é obrigatória")
    @JsonProperty("analiseDoCaso")
    private String analiseDoCaso;

    public ParecerPsicologicoDto (UUID id, PacienteDto paciente, PsicologoDto psicologo, LocalDate dataEmissao, LocalDate dataValidade, String assinaturaPsicologo, String solicitante, String objetivo, String conclusao, String sigilo, String contextualizacao, String fundamentacao, String analiseDoCaso){
        this.id = id;
        this.paciente = paciente;
        this.psicologo = psicologo;
        this.dataEmissao = dataEmissao;
        this.dataValidade = dataValidade;
        this.assinaturaPsicologo = assinaturaPsicologo;
        this.solicitante = solicitante;
        this.objetivo = objetivo;
        this.conclusao = conclusao;
        this.sigilo = sigilo;
        this.contextualizacao = contextualizacao;
        this.fundamentacao = fundamentacao;
        this.analiseDoCaso = analiseDoCaso;
    }
}
