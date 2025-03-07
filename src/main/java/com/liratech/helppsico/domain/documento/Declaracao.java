package com.liratech.helppsico.domain.documento;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter
public class Declaracao extends Documento{
    private String motivo;
    private String descricao;
    private String finalidade;

    public Declaracao (UUID id, Paciente paciente, Psicologo psicologo, LocalDate dataEmissao,
                       LocalDate dataValidade, String assinaturaPsicologo, String motivo,
                       String descricao, String finalidade){
        super(id, paciente, psicologo, dataEmissao, dataValidade, assinaturaPsicologo);
        this.motivo = motivo;
        this.descricao = descricao;
        this.finalidade = finalidade;
    }
}
