package com.liratech.helppsico.domain.documento;


import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter
public class ParecerPsicologo extends Documento{
    private String solicitante;
    private String objetivo;
    private String conclusao;
    private String sigilo;
    private String contextualizacao;
    private String fundamentacao;
    private String analiseDoCaso;

    public ParecerPsicologo (UUID id, Paciente paciente, Psicologo psicologo, LocalDate dataEmissao,
                             LocalDate dataValidade, String assinaturaPsicologo, String solicitante, String objetivo,
                             String conclusao, String sigilo, String contextualizacao, String fundamentacao, String analiseDoCaso){
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
