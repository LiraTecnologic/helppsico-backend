package com.liratech.helppsico.entrypoint.dto.documento;

import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@NoArgsConstructor
@Getter
@Setter
public class LaudoPsicologicoDto extends DocumentoDto{
    private String solicitante;
    private String objetivo;
    private String historico;
    private String procedimentosUtilizados;
    private String descricaoResultados;
    private String conclusao;
    private String respostaDemanda;
    private String recomendacoes;
    private String sigilo;

    public LaudoPsicologicoDto (UUID id, PacienteDto paciente, PsicologoDto psicologo, LocalDate dataEmissao, LocalDate dataValidade, String assinaturaPsicologo, String solicitante, String objetivo, String historico, String procedimentosUtilizados, String descricaoResultados, String conclusao, String respostaDemanda, String recomendacoes, String sigilo){
        super(id, paciente, psicologo, dataEmissao, dataValidade, assinaturaPsicologo);
        this.solicitante = solicitante;
        this.objetivo = objetivo;
        this.historico = historico;
        this.procedimentosUtilizados = procedimentosUtilizados;
        this.descricaoResultados = descricaoResultados;
        this.conclusao = conclusao;
        this.respostaDemanda = respostaDemanda;
        this.recomendacoes = recomendacoes;
        this.sigilo = sigilo;
    }
}
