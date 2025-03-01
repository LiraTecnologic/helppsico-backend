package com.liratech.helppsico.domain.documento;

import com.liratech.helppsico.domain.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
public class Atestado {
    private LocalDate dataAtendimento;
    private Endereco local;
    private String descricao;
    private String descricaoEstadoPsicoloco;
    private String periodoAfastamento;
    private String finalidade;
}
