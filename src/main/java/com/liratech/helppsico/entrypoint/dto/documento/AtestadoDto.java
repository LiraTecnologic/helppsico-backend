package com.liratech.helppsico.entrypoint.dto.documento;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@ToString
@Getter
public class AtestadoDto extends DocumentoDto{
    @NotNull(message = "A data do atendimento é obrigatória")
    @JsonProperty("dataAtendimento")
    private LocalDate dataAtendimento;

    @NotNull(message = "O endereço é obrigatório")
    @JsonProperty("local")
    private EnderecoDto local;

    @NotBlank(message = "A descrição é obrigatória")
    @JsonProperty("descricao")
    private String descricao;

    @NotBlank(message = "A descrição do estado psicologico é obrigatória")
    @JsonProperty("descricaoEstadoPsicologico")
    private String descricaoEstadoPsicologico;

    @NotBlank(message = "O período de afastamento é obrigatório")
    @JsonProperty("periodoAfastamento")
    private String periodoAfastamento;

    @NotBlank(message = "A finalidade é obrigatória")
    @JsonProperty("finalidade")
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
