package com.liratech.helppsico.builders;

import com.liratech.helppsico.application.usecases.dto.DadosGeraisDocumentoDto;

import java.time.LocalDate;

public class DadosGeraisDocumentoBuilder {
    public static DadosGeraisDocumentoDto criarDadosGeraisDocumentos(){
        return DadosGeraisDocumentoDto.builder()
                .paciente(PacienteBuilder.criarPacienteDto())
                .psicologo(PsicologoBuilder.criarPsicologoDto())
                .dataEmissao(LocalDate.now())
                .dataValidade(LocalDate.now().plusDays(10))
                .assinaturaPsicologo("Dra. Ana")
                .motivo("Motivo do documento")
                .descricao("Descrição completa do documento")
                .finalidade("Finalidade terapêutica")
                .solicitante("Solicitante Externo")
                .objetivo("Objetivo da avaliação psicológica")
                .historico("Histórico clínico do paciente")
                .procedimentosUtilizados("Entrevistas, testes")
                .descricaoResultados("Resultados apontam melhora")
                .conclusao("Paciente apresenta evolução")
                .recomendacoes("Acompanhamento mensal")
                .sigilo("Sigiloso conforme ética")
                .contextualizacao("Contexto familiar e social")
                .fundamentacao("Baseado no código de ética")
                .analiseDoCaso("Análise detalhada do caso")
                .respostaDemanda("Resposta clara e fundamentada")
                .dataAtendimento(LocalDate.now().minusDays(1))
                .local(EnderecoBuilder.criarEnderecoDto())
                .descrcaoEstadoPsicologico("Estado emocional estável")
                .peridoAfastamento("5 dias")
                .build();
    }
}
