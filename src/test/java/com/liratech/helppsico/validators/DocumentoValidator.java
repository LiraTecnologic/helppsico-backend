package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.documento.*;
import com.liratech.helppsico.entrypoint.dto.documento.*;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.*;
import org.junit.jupiter.api.Assertions;

public class DocumentoValidator {

    public static void validaDocumentoDomain (Documento esperado, Documento resultado){
        Assertions.assertEquals(esperado.getDataEmissao(), resultado.getDataEmissao());
        Assertions.assertEquals(esperado.getDataValidade(), resultado.getDataValidade());
        Assertions.assertEquals(esperado.getAssinaturaPsicologo(), resultado.getAssinaturaPsicologo());
        PacienteValidator.validaPacienteDomain(esperado.getPaciente(), resultado.getPaciente());
        PsicologoValidator.validaPsicologoDomain(esperado.getPsicologo(), resultado.getPsicologo());
    }
    public static void validaDocumentoEntry (Documento domain, DocumentoDto dto){
        Assertions.assertEquals(domain.getDataEmissao(), dto.getDataEmissao());
        Assertions.assertEquals(domain.getDataValidade(), dto.getDataValidade());
        Assertions.assertEquals(domain.getAssinaturaPsicologo(), dto.getAssinaturaPsicologo());
        PacienteValidator.validaPacienteMapperEntry(domain.getPaciente(), dto.getPaciente());
        PsicologoValidator.validaPsicologoMapperEntry(domain.getPsicologo(), dto.getPsicologo());
    }
    public static void validaDocumentoInfra (Documento domain, DocumentoEntity entity){
        Assertions.assertEquals(domain.getDataEmissao(), entity.getDataEmissao());
        Assertions.assertEquals(domain.getDataValidade(), entity.getDataValidade());
        Assertions.assertEquals(domain.getAssinaturaPsicologo(), entity.getAssinaturaPsicologo());
        PacienteValidator.validaPacienteMapperInfra(domain.getPaciente(), entity.getPaciente());
        PsicologoValidator.validaPsicologoMapperInfra(domain.getPsicologo(), entity.getPsicologo());
    }

    public static void validaAtestado (Atestado esperado, Atestado resultado){
        Assertions.assertEquals(esperado.getDataAtendimento(), resultado.getDataAtendimento());
        EnderecoValidator.validaEnderecoDomain(esperado.getLocal(), resultado.getLocal());
        Assertions.assertEquals(esperado.getDescricao(), resultado.getDescricao());
        Assertions.assertEquals(esperado.getDescricaoEstadoPsicologico(), resultado.getDescricaoEstadoPsicologico());
        Assertions.assertEquals(esperado.getPeriodoAfastamento(), resultado.getPeriodoAfastamento());
        Assertions.assertEquals(esperado.getFinalidade(), resultado.getFinalidade());
    }
    public static void validaAtestadoMapperEntry (Atestado domain, AtestadoDto dto){
        Assertions.assertEquals(domain.getDataAtendimento(), dto.getDataAtendimento());
        EnderecoValidator.validaEnderecoMapperEntry(domain.getLocal(), dto.getLocal());
        Assertions.assertEquals(domain.getDescricao(), dto.getDescricao());
        Assertions.assertEquals(domain.getDescricaoEstadoPsicologico(), dto.getDescricaoEstadoPsicologico());
        Assertions.assertEquals(domain.getPeriodoAfastamento(), dto.getPeriodoAfastamento());
        Assertions.assertEquals(domain.getFinalidade(), dto.getFinalidade());
    }
    public static void validaAtestadoMapperInfra (Atestado domain, AtestadoEntity entity){
        Assertions.assertEquals(domain.getDataAtendimento(), entity.getDataAtendimento());
        EnderecoValidator.validaEnderecoMapperInfra(domain.getLocal(), entity.getLocal());
        Assertions.assertEquals(domain.getDescricao(), entity.getDescricao());
        Assertions.assertEquals(domain.getDescricaoEstadoPsicologico(), entity.getDescricaoEstadoPsicologico());
        Assertions.assertEquals(domain.getPeriodoAfastamento(), entity.getPeriodoAfastamento());
        Assertions.assertEquals(domain.getFinalidade(), entity.getFinalidade());
    }

    public static void validaDeclaracao (Declaracao esperado, Declaracao resultado){
        Assertions.assertEquals(esperado.getMotivo(), resultado.getMotivo());
        Assertions.assertEquals(esperado.getDescricao(), resultado.getDescricao());
        Assertions.assertEquals(esperado.getFinalidade(), resultado.getFinalidade());
    }
    public static void validaDeclaracaoMapperEntry (Declaracao domain, DeclaracaoDto dto){
        Assertions.assertEquals(domain.getMotivo(), dto.getMotivo());
        Assertions.assertEquals(domain.getDescricao(), dto.getDescricao());
        Assertions.assertEquals(domain.getFinalidade(), dto.getFinalidade());
    }
    public static void validaDeclaracaoMapperInfra (Declaracao domain, DeclaracaoEntity entity){
        Assertions.assertEquals(domain.getMotivo(), entity.getMotivo());
        Assertions.assertEquals(domain.getDescricao(), entity.getDescricao());
        Assertions.assertEquals(domain.getFinalidade(), entity.getFinalidade());
    }

    public static void validaRelatorioPsicologico (RelatorioPsicologico esperado, RelatorioPsicologico resultado){
        Assertions.assertEquals(esperado.getSolicitante(), resultado.getSolicitante());
        Assertions.assertEquals(esperado.getObjetivo(), resultado.getObjetivo());
        Assertions.assertEquals(esperado.getHistorico(), resultado.getHistorico());
        Assertions.assertEquals(esperado.getProcedimentosUtilizados(), resultado.getProcedimentosUtilizados());
        Assertions.assertEquals(esperado.getDescricaoResultados(), resultado.getDescricaoResultados());
        Assertions.assertEquals(esperado.getConclusao(), resultado.getConclusao());
        Assertions.assertEquals(esperado.getRecomendacoes(), resultado.getRecomendacoes());
        Assertions.assertEquals(esperado.getSigilo(), resultado.getSigilo());
    }
    public static void validaRelatorioPsicologicoMapperEntry (RelatorioPsicologico domain, RelatorioPsicologicoDto dto){
        Assertions.assertEquals(domain.getSolicitante(), dto.getSolicitante());
        Assertions.assertEquals(domain.getObjetivo(), dto.getObjetivo());
        Assertions.assertEquals(domain.getHistorico(), dto.getHistorico());
        Assertions.assertEquals(domain.getProcedimentosUtilizados(), dto.getProcedimentosUtilizados());
        Assertions.assertEquals(domain.getDescricaoResultados(), dto.getDescricaoResultados());
        Assertions.assertEquals(domain.getConclusao(), dto.getConclusao());
        Assertions.assertEquals(domain.getRecomendacoes(), dto.getRecomendacoes());
        Assertions.assertEquals(domain.getSigilo(), dto.getSigilo());
    }
    public static void validaRelatorioPsicologicoMapperInfra (RelatorioPsicologico domain, RelatorioPsicologicoEntity entity){
        Assertions.assertEquals(domain.getSolicitante(), entity.getSolicitante());
        Assertions.assertEquals(domain.getObjetivo(), entity.getObjetivo());
        Assertions.assertEquals(domain.getHistorico(), entity.getHistorico());
        Assertions.assertEquals(domain.getProcedimentosUtilizados(), entity.getProcedimentosUtilizados());
        Assertions.assertEquals(domain.getDescricaoResultados(), entity.getDescricaoResultados());
        Assertions.assertEquals(domain.getConclusao(), entity.getConclusao());
        Assertions.assertEquals(domain.getRecomendacoes(), entity.getRecomendacoes());
        Assertions.assertEquals(domain.getSigilo(), entity.getSigilo());
    }

    public static void validaLaudoPsicologico (LaudoPsicologico esperado, LaudoPsicologico resultado){
        Assertions.assertEquals(esperado.getSolicitante(), resultado.getSolicitante());
        Assertions.assertEquals(esperado.getObjetivo(), resultado.getObjetivo());
        Assertions.assertEquals(esperado.getHistorico(), resultado.getHistorico());
        Assertions.assertEquals(esperado.getProcedimentosUtilizados(), resultado.getProcedimentosUtilizados());
        Assertions.assertEquals(esperado.getDescricaoResultados(), resultado.getDescricaoResultados());
        Assertions.assertEquals(esperado.getConclusao(), resultado.getConclusao());
        Assertions.assertEquals(esperado.getRespostaDemanda(), resultado.getRespostaDemanda());
        Assertions.assertEquals(esperado.getRecomendacoes(), resultado.getRecomendacoes());
        Assertions.assertEquals(esperado.getSigilo(), resultado.getSigilo());
    }
    public static void validaLaudoPsicologicoMapperEntry (LaudoPsicologico domain, LaudoPsicologicoDto dto){
        Assertions.assertEquals(domain.getSolicitante(), dto.getSolicitante());
        Assertions.assertEquals(domain.getObjetivo(), dto.getObjetivo());
        Assertions.assertEquals(domain.getHistorico(), dto.getHistorico());
        Assertions.assertEquals(domain.getProcedimentosUtilizados(), dto.getProcedimentosUtilizados());
        Assertions.assertEquals(domain.getDescricaoResultados(), dto.getDescricaoResultados());
        Assertions.assertEquals(domain.getConclusao(), dto.getConclusao());
        Assertions.assertEquals(domain.getRespostaDemanda(), dto.getRespostaDemanda());
        Assertions.assertEquals(domain.getRecomendacoes(), dto.getRecomendacoes());
        Assertions.assertEquals(domain.getSigilo(), dto.getSigilo());
    }
    public static void validaLaudoPsicologicoMapperInfra (LaudoPsicologico domain, LaudoPsicologicoEntity entity){
        Assertions.assertEquals(domain.getSolicitante(), entity.getSolicitante());
        Assertions.assertEquals(domain.getObjetivo(), entity.getObjetivo());
        Assertions.assertEquals(domain.getHistorico(), entity.getHistorico());
        Assertions.assertEquals(domain.getProcedimentosUtilizados(), entity.getProcedimentosUtilizados());
        Assertions.assertEquals(domain.getDescricaoResultados(), entity.getDescricaoResultados());
        Assertions.assertEquals(domain.getConclusao(), entity.getConclusao());
        Assertions.assertEquals(domain.getRespostaDemanda(), entity.getRespostaDemanda());
        Assertions.assertEquals(domain.getRecomendacoes(), entity.getRecomendacoes());
        Assertions.assertEquals(domain.getSigilo(), entity.getSigilo());
    }

    public static void validaParecerPsicologico (ParecerPsicologico esperado, ParecerPsicologico resultado){
        Assertions.assertEquals(esperado.getSolicitante(), resultado.getSolicitante());
        Assertions.assertEquals(esperado.getObjetivo(), resultado.getObjetivo());
        Assertions.assertEquals(esperado.getConclusao(), resultado.getConclusao());
        Assertions.assertEquals(esperado.getSigilo(), resultado.getSigilo());
        Assertions.assertEquals(esperado.getContextualizacao(), resultado.getContextualizacao());
        Assertions.assertEquals(esperado.getFundamentacao(), resultado.getFundamentacao());
        Assertions.assertEquals(esperado.getAnaliseDoCaso(), resultado.getAnaliseDoCaso());
    }
    public static void validaParecerPsicologicoMapperEntry (ParecerPsicologico domain, ParecerPsicologicoDto dto){
        Assertions.assertEquals(domain.getSolicitante(), dto.getSolicitante());
        Assertions.assertEquals(domain.getObjetivo(), dto.getObjetivo());
        Assertions.assertEquals(domain.getConclusao(), dto.getConclusao());
        Assertions.assertEquals(domain.getSigilo(), dto.getSigilo());
        Assertions.assertEquals(domain.getContextualizacao(), dto.getContextualizacao());
        Assertions.assertEquals(domain.getFundamentacao(), dto.getFundamentacao());
        Assertions.assertEquals(domain.getAnaliseDoCaso(), dto.getAnaliseDoCaso());
    }
    public static void validaParecerPsicologicoMapperInfra (ParecerPsicologico domain, ParecerPsicologicoEntity entity){
        Assertions.assertEquals(domain.getSolicitante(), entity.getSolicitante());
        Assertions.assertEquals(domain.getObjetivo(), entity.getObjetivo());
        Assertions.assertEquals(domain.getConclusao(), entity.getConclusao());
        Assertions.assertEquals(domain.getSigilo(), entity.getSigilo());
        Assertions.assertEquals(domain.getContextualizacao(), entity.getContextualizacao());
        Assertions.assertEquals(domain.getFundamentacao(), entity.getFundamentacao());
        Assertions.assertEquals(domain.getAnaliseDoCaso(), entity.getAnaliseDoCaso());
    }
}
