package com.liratech.helppsico.application.usecases.dto;

import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class DadosGeraisDocumentoDto {
    private PacienteDto paciente;
    private PsicologoDto psicologo;
    private LocalDate dataEmissao;
    private LocalDate dataValidade;
    private String assinaturaPsicologo;
    private String motivo;
    private String descricao;
    private String finalidade;
    private String solicitante;
    private String objetivo;
    private String historico;
    private String procedimentosUtilizados;
    private String descricaoResultados;
    private String conclusao;
    private String recomendacoes;
    private String sigilo;
    private String contextualizacao;
    private String fundamentacao;
    private String analiseDoCaso;
    private String respostaDemanda;
    private LocalDate dataAtendimento;
    private EnderecoDto local;
    private String descricaoEstadoPsicologico;
    private String periodoAfastamento;
}
