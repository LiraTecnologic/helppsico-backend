package com.liratech.helppsico.entrypoint.dto.documento;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import lombok.Getter;
import java.time.LocalDate;
import java.util.UUID;

@Getter
public class DeclaracaoDto extends DocumentoDto{

    @JsonProperty("motivo")
    private String motivo;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("finalidade")
    private String finalidade;

    public DeclaracaoDto (UUID id, PacienteDto paciente, PsicologoDto psicologo, LocalDate dataEmissao, LocalDate dataValidade, String assinaturaPsicologo, String motivo, String descricao, String finalidade){
        super(id, paciente, psicologo, dataEmissao, dataValidade, assinaturaPsicologo);
        this.motivo = motivo;
        this.descricao = descricao;
        this.finalidade = finalidade;
    }
}
