package com.liratech.helppsico.entrypoint.dto.documento;

import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AtestadoDto extends DocumentoDto{
    private LocalDate dataAtendimento;
    private EnderecoDto local;
    private String descricao;
    private String descricaoEstadoPsicologico;
    private String periodoAfastamento;
    private String finalidade;

    public AtestadoDto (LocalDate dataAtendimento, EnderecoDto local, String descricao, String descricaoEstadoPsicologico, String periodoAfastamento, String finalidade){

    }
}
