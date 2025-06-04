package com.liratech.helppsico.infrastructure.repositories.entities.documento;

import com.liratech.helppsico.infrastructure.repositories.entities.EnderecoEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "Atestado")
@Table(name = "atestados")
@NoArgsConstructor
@Getter
@ToString
public class AtestadoEntity extends DocumentoEntity {
    @Column(name = "data_atendimento")
    private LocalDate dataAtendimento;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private EnderecoEntity local;

    private String descricao;

    @Column(name = "descricao_estado_psicologico")
    private String descricaoEstadoPsicologico;

    @Column(name = "periodo_afastamento")
    private String periodoAfastamento;

    private String finalidade;

    public AtestadoEntity (UUID id, PacienteEntity paciente, PsicologoEntity psicologo, LocalDate dataEmissao, LocalDate dataValidade, String assinaturaPsicologo, LocalDate dataAtendimento, EnderecoEntity local, String descricao, String descricaoEstadoPsicologico, String periodoAfastamento, String finalidade){
        super(id, paciente, psicologo, dataEmissao, dataValidade, assinaturaPsicologo);
        this.dataAtendimento = dataAtendimento;
        this.local = local;
        this.descricao = descricao;
        this.descricaoEstadoPsicologico = descricaoEstadoPsicologico;
        this.periodoAfastamento = periodoAfastamento;
        this.finalidade = finalidade;
    }
}
