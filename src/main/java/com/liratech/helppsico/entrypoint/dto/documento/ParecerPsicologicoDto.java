package com.liratech.helppsico.entrypoint.dto.documento;

import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class ParecerPsicologicoDto extends DocumentoDto{
    private String solicitante;
    private String objetivo;
    private String conclusao;
    private String sigilo;
    private String contextualizacao;
    private String fundamentacao;
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
