package com.liratech.helppsico.entrypoint.dto.documento;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import java.time.LocalDate;
import java.util.UUID;

@Getter
public class DeclaracaoDto extends DocumentoDto{

    @NotBlank(message = "O motivo é obrigatório")
    @JsonProperty("motivo")
    private String motivo;

    @NotBlank(message = "A descricao é obrigatória")
    @JsonProperty("descricao")
    private String descricao;

    @NotBlank(message = "A finalidade é obrigatória")
    @JsonProperty("finalidade")
    private String finalidade;

    public DeclaracaoDto (UUID id, PacienteDto paciente, PsicologoDto psicologo, LocalDate dataEmissao, LocalDate dataValidade, String assinaturaPsicologo, String motivo, String descricao, String finalidade){
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
