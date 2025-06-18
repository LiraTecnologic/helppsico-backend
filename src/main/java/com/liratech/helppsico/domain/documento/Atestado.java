package com.liratech.helppsico.domain.documento;

import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class Atestado extends Documento{
    private LocalDate dataAtendimento;
    private Endereco local;
    private String descricao;
    private String descricaoEstadoPsicologico;
    private String periodoAfastamento;
    private String finalidade;

    public Atestado (UUID id, Paciente paciente, Psicologo psicologo,
                     LocalDate dataEmissao, LocalDate dataValidade,
                     String assinaturaPsicologo, LocalDate dataAtendimento,
                     Endereco local, String descricao,
                     String descricaoEstadoPsicologico, String periodoAfastamento,
                     String finalidade){
        super(id, paciente, psicologo, dataEmissao, dataValidade, assinaturaPsicologo);
        this.dataAtendimento = dataAtendimento;
        this.local = local;
        this.descricao = descricao;
        this.descricaoEstadoPsicologico = descricaoEstadoPsicologico;
        this.periodoAfastamento = periodoAfastamento;
        this.finalidade = finalidade;
    }
}
