package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.documento.*;
import org.junit.jupiter.api.Assertions;

public class DocumentoValidator {
    public static void validaDocumentoDomain (Documento esperado, Documento resultado){
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        Assertions.assertEquals(esperado.getDataEmissao(), resultado.getDataEmissao());
        Assertions.assertEquals(esperado.getDataValidade(), resultado.getDataValidade());
        Assertions.assertEquals(esperado.getAssinaturaPsicologo(), resultado.getAssinaturaPsicologo());
        Assertions.assertEquals(esperado.getPaciente(), resultado.getPaciente());
        Assertions.assertEquals(esperado.getPsicologo(), resultado.getPsicologo());
    }

    public static void validaAtestado (Atestado esperado, Atestado resultado){
        Assertions.assertEquals(esperado.getDataAtendimento(), resultado.getDataAtendimento());
        Assertions.assertEquals(esperado.getLocal(), resultado.getLocal());
        Assertions.assertEquals(esperado.getDescricao(), resultado.getDescricao());
        Assertions.assertEquals(esperado.getDescricaoEstadoPsicologico(), resultado.getDescricaoEstadoPsicologico());
        Assertions.assertEquals(esperado.getPeriodoAfastamento(), resultado.getPeriodoAfastamento());
        Assertions.assertEquals(esperado.getFinalidade(), resultado.getFinalidade());
    }

    public static void validaDeclaracao (Declaracao esperado, Declaracao resultado){
        Assertions.assertEquals(esperado.getMotivo(), resultado.getMotivo());
        Assertions.assertEquals(esperado.getDescricao(), resultado.getDescricao());
        Assertions.assertEquals(esperado.getFinalidade(), resultado.getFinalidade());
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

    public static void validaParecerPsicologico (ParecerPsicologico esperado, ParecerPsicologico resultado){
        Assertions.assertEquals(esperado.getSolicitante(), resultado.getSolicitante());
        Assertions.assertEquals(esperado.getObjetivo(), resultado.getObjetivo());
        Assertions.assertEquals(esperado.getConclusao(), resultado.getConclusao());
        Assertions.assertEquals(esperado.getSigilo(), resultado.getSigilo());
        Assertions.assertEquals(esperado.getContextualizacao(), resultado.getContextualizacao());
        Assertions.assertEquals(esperado.getFundamentacao(), resultado.getFundamentacao());
        Assertions.assertEquals(esperado.getAnaliseDoCaso(), resultado.getAnaliseDoCaso());
    }
}
