package com.liratech.helppsico.validators.json;

import com.liratech.helppsico.domain.documento.*;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class DocumentoValidatorJson {
    public static void validaDocumentoJson(ResultActions resultado, Documento esperado) throws Exception {
        resultado.andExpect(jsonPath("$.dado.id").value(esperado.getId().toString()))
                .andExpect(jsonPath("$.dado.paciente.id").value(esperado.getPaciente().getId().toString()))
                .andExpect(jsonPath("$.dado.paciente.nome").value(esperado.getPaciente().getNome()))
                .andExpect(jsonPath("$.dado.paciente.cpf").value(esperado.getPaciente().getCpf()))
                .andExpect(jsonPath("$.dado.paciente.email").value(esperado.getPaciente().getEmail()))
                .andExpect(jsonPath("$.dado.paciente.telefone").value(esperado.getPaciente().getTelefone()))
                .andExpect(jsonPath("$.dado.paciente.dataNascimento").value(esperado.getPaciente().getDataNascimento().toString()))
                .andExpect(jsonPath("$.dado.paciente.senha").value(esperado.getPaciente().getSenha()))
                .andExpect(jsonPath("$.dado.paciente.genero").value(esperado.getPaciente().getGenero().toString()))
                .andExpect(jsonPath("$.dado.paciente.enderecoAtendimento.id").value(esperado.getPaciente().getEndereco().getId().toString()))
                .andExpect(jsonPath("$.dado.paciente.enderecoAtendimento.rua").value(esperado.getPaciente().getEndereco().getRua()))
                .andExpect(jsonPath("$.dado.paciente.enderecoAtendimento.numero").value(esperado.getPaciente().getEndereco().getNumero()))
                .andExpect(jsonPath("$.dado.paciente.enderecoAtendimento.cep").value(esperado.getPaciente().getEndereco().getCep()))
                .andExpect(jsonPath("$.dado.paciente.enderecoAtendimento.cidade").value(esperado.getPaciente().getEndereco().getCidade()))
                .andExpect(jsonPath("$.dado.paciente.enderecoAtendimento.estado").value(esperado.getPaciente().getEndereco().getEstado()))
                .andExpect(jsonPath("$.dado.paciente.fotoUrl").value(esperado.getPaciente().getFotoUrl()))
                .andExpect(jsonPath("$.dado.psicologo.id").value(esperado.getPsicologo().getId().toString()))
                .andExpect(jsonPath("$.dado.psicologo.nome").value(esperado.getPsicologo().getNome()))
                .andExpect(jsonPath("$.dado.psicologo.crp").value(esperado.getPsicologo().getCrp()))
                .andExpect(jsonPath("$.dado.psicologo.cpf").value(esperado.getPsicologo().getCpf()))
                .andExpect(jsonPath("$.dado.psicologo.email").value(esperado.getPsicologo().getEmail()))
                .andExpect(jsonPath("$.dado.psicologo.telefone").value(esperado.getPsicologo().getTelefone()))
                .andExpect(jsonPath("$.dado.psicologo.dataNascimento").value(esperado.getPsicologo().getDataNascimento().toString()))
                .andExpect(jsonPath("$.dado.psicologo.senha").value(esperado.getPsicologo().getSenha()))
                .andExpect(jsonPath("$.dado.psicologo.genero").value(esperado.getPsicologo().getGenero().toString()))
                .andExpect(jsonPath("$.dado.psicologo.enderecoAtendimento.id").value(esperado.getPsicologo().getEnderecoAtendimento().getId().toString()))
                .andExpect(jsonPath("$.dado.psicologo.enderecoAtendimento.rua").value(esperado.getPsicologo().getEnderecoAtendimento().getRua()))
                .andExpect(jsonPath("$.dado.psicologo.enderecoAtendimento.numero").value(esperado.getPsicologo().getEnderecoAtendimento().getNumero()))
                .andExpect(jsonPath("$.dado.psicologo.enderecoAtendimento.cep").value(esperado.getPsicologo().getEnderecoAtendimento().getCep()))
                .andExpect(jsonPath("$.dado.psicologo.enderecoAtendimento.cidade").value(esperado.getPsicologo().getEnderecoAtendimento().getCidade()))
                .andExpect(jsonPath("$.dado.psicologo.enderecoAtendimento.estado").value(esperado.getPsicologo().getEnderecoAtendimento().getEstado()))
                .andExpect(jsonPath("$.dado.psicologo.fotoUrl").value(esperado.getPsicologo().getFotoUrl()))
                .andExpect(jsonPath("$.dado.psicologo.biografia").value(esperado.getPsicologo().getBiografia()))
                .andExpect(jsonPath("$.dado.dataEmissao").value(esperado.getDataEmissao().toString()))
                .andExpect(jsonPath("$.dado.dataValidade").value(esperado.getDataValidade().toString()))
                .andExpect(jsonPath("$.dado.assinaturaPsicologo").value(esperado.getAssinaturaPsicologo()));
    }

    public static void validaAtestadoJson(ResultActions resultado, Atestado esperado) throws Exception{
        resultado.andExpect(jsonPath("$.dado.dataAtendimento").value(esperado.getDataAtendimento().toString()))
                .andExpect(jsonPath("$.dado.local.id").value(esperado.getPsicologo().getEnderecoAtendimento().getId().toString()))
                .andExpect(jsonPath("$.dado.local.rua").value(esperado.getLocal().getRua()))
                .andExpect(jsonPath("$.dado.local.numero").value(esperado.getLocal().getNumero()))
                .andExpect(jsonPath("$.dado.local.cep").value(esperado.getLocal().getCep()))
                .andExpect(jsonPath("$.dado.local.cidade").value(esperado.getLocal().getCidade()))
                .andExpect(jsonPath("$.dado.local.estado").value(esperado.getLocal().getEstado()))
                .andExpect(jsonPath("$.dado.descricao").value(esperado.getDescricao()))
                .andExpect(jsonPath("$.dado.descricaoEstadoPsicologico").value(esperado.getDescricaoEstadoPsicologico()))
                .andExpect(jsonPath("$.dado.periodoAfastamento").value(esperado.getPeriodoAfastamento()))
                .andExpect(jsonPath("$.dado.finalidade").value(esperado.getFinalidade()));
    }

    public static void validaDeclaracaoJson(ResultActions resultado, Declaracao esperado) throws Exception{
        resultado.andExpect(jsonPath("$.dado.motivo").value(esperado.getMotivo()))
                .andExpect(jsonPath("$.dado.descricao").value(esperado.getDescricao()))
                .andExpect(jsonPath("$.dado.finalidade").value(esperado.getFinalidade()));
    }

    public static void validaLaudoPsicologicoJson(ResultActions resultado, LaudoPsicologico esperado) throws Exception{
        resultado.andExpect(jsonPath("$.dado.solicitante").value(esperado.getSolicitante()))
                .andExpect(jsonPath("$.dado.objetivo").value(esperado.getObjetivo()))
                .andExpect(jsonPath("$.dado.historico").value(esperado.getHistorico()))
                .andExpect(jsonPath("$.dado.procedimentosUtilizados").value(esperado.getProcedimentosUtilizados()))
                .andExpect(jsonPath("$.dado.descricaoResultados").value(esperado.getDescricaoResultados()))
                .andExpect(jsonPath("$.dado.conclusao").value(esperado.getConclusao()))
                .andExpect(jsonPath("$.dado.respostaDemanda").value(esperado.getRespostaDemanda()))
                .andExpect(jsonPath("$.dado.recomendacoes").value(esperado.getRecomendacoes()))
                .andExpect(jsonPath("$.dado.sigilo").value(esperado.getSigilo()));
    }

    public static void validaParecerPsicologicoJson(ResultActions resultado, ParecerPsicologico esperado) throws Exception{
        resultado.andExpect(jsonPath("$.dado.solicitante").value(esperado.getSolicitante()))
                .andExpect(jsonPath("$.dado.objetivo").value(esperado.getObjetivo()))
                .andExpect(jsonPath("$.dado.conclusao").value(esperado.getConclusao()))
                .andExpect(jsonPath("$.dado.sigilo").value(esperado.getSigilo()))
                .andExpect(jsonPath("$.dado.contextualizacao").value(esperado.getContextualizacao()))
                .andExpect(jsonPath("$.dado.fundamentacao").value(esperado.getFundamentacao()))
                .andExpect(jsonPath("$.dado.analiseDoCaso").value(esperado.getAnaliseDoCaso()));
    }

    public static void validaRelatorioPsicologicoJson(ResultActions resultado, RelatorioPsicologico esperado) throws Exception{
        resultado.andExpect(jsonPath("$.dado.solicitante").value(esperado.getSolicitante()))
                .andExpect(jsonPath("$.dado.objetivo").value(esperado.getObjetivo()))
                .andExpect(jsonPath("$.dado.historico").value(esperado.getHistorico()))
                .andExpect(jsonPath("$.dado.procedimentosUtilizados").value(esperado.getProcedimentosUtilizados()))
                .andExpect(jsonPath("$.dado.descricaoResultados").value(esperado.getDescricaoResultados()))
                .andExpect(jsonPath("$.dado.conclusao").value(esperado.getConclusao()))
                .andExpect(jsonPath("$.dado.recomendacoes").value(esperado.getRecomendacoes()))
                .andExpect(jsonPath("$.dado.sigilo").value(esperado.getRecomendacoes()));
    }
}
