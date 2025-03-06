package com.liratech.helppsico.entrypoint.dto.documento;

import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@NoArgsConstructor
@Getter
@Setter
public class AtestadoDto extends DocumentoDto{
    private LocalDate dataAtendimento;
    private EnderecoDto local;
    private String descricao;
    private String descricaoEstadoPsicologico;
    private String periodoAfastamento;
    private String finalidade;

    public AtestadoDto (UUID id, PacienteDto paciente, PsicologoDto psicologo, LocalDate dataEmissao, LocalDate dataValidade, String assinaturaPsicologo, LocalDate dataAtendimento, EnderecoDto local, String descricao, String descricaoEstadoPsicologico, String periodoAfastamento, String finalidade){
        super(id, paciente, psicologo, dataEmissao, dataValidade, assinaturaPsicologo);
        this.dataAtendimento = dataAtendimento;
        this.local = local;
        this.descricao = descricao;
        this.descricaoEstadoPsicologico = descricaoEstadoPsicologico;
        this.periodoAfastamento = periodoAfastamento;
        this.finalidade = finalidade;
    }
}
