package com.liratech.helppsico.domain.documento;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class Declaracao extends Documento{
    private String motivo;
    private String descricao;
    private String finalidade;

    public Declaracao (UUID id, Paciente paciente, Psicologo psicologo, LocalDate dataEmissao,
                       LocalDate dataValidade, String assinaturaPsicologo, String motivo,
                       String descricao, String finalidade){
        this.id = id;
        this.paciente = paciente;
        this.psicologo = psicologo;
        this.dataEmissao = dataEmissao;
        this.dataValidade = dataValidade;
        this.assinaturaPsicologo = assinaturaPsicologo;
        this.motivo = motivo;
        this.descricao = descricao;
        this.finalidade = finalidade;
    }


}
