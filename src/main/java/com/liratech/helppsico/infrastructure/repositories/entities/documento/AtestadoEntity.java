package com.liratech.helppsico.infrastructure.repositories.entities.documento;

import com.liratech.helppsico.infrastructure.repositories.entities.EnderecoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity(name = "Atestado")
@Table(name = "atestados")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AtestadoEntity {

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
}
