package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.TipoDocumentoInvalidoException;
import com.liratech.helppsico.application.usecases.dto.DadosGeraisDocumentoDto;
import com.liratech.helppsico.domain.documento.*;
import com.liratech.helppsico.entrypoint.mapper.EnderecoMapper;
import com.liratech.helppsico.entrypoint.mapper.PacienteMapper;
import com.liratech.helppsico.entrypoint.mapper.PsicologoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentoFactory {

    private final PacienteMapper pacienteMapper;
    private final PsicologoMapper psicologoMapper;
    private final EnderecoMapper enderecoMapper;
    public final String MENSAGEM_TIPO_DOCUMENTO_INVALIDO = "Tipo do documento inválido";

    public Documento criar(DadosGeraisDocumentoDto dadosGeraisDocumentoDto, TipoDocumento tipoDocumento) {
        switch (tipoDocumento.getCodigo()){
            case 1:
                return new Atestado(
                        null,
                        pacienteMapper.paraDomain(dadosGeraisDocumentoDto.getPaciente()),
                        psicologoMapper.paraDomain(dadosGeraisDocumentoDto.getPsicologo()),
                        dadosGeraisDocumentoDto.getDataEmissao(),
                        dadosGeraisDocumentoDto.getDataValidade(),
                        dadosGeraisDocumentoDto.getAssinaturaPsicologo(),
                        dadosGeraisDocumentoDto.getDataAtendimento(),
                        enderecoMapper.paraDomain(dadosGeraisDocumentoDto.getLocal()),
                        dadosGeraisDocumentoDto.getDescricao(),
                        dadosGeraisDocumentoDto.getDescricaoEstadoPsicologico(),
                        dadosGeraisDocumentoDto.getPeriodoAfastamento(),
                        dadosGeraisDocumentoDto.getFinalidade()
                );
            case 2:
                return new Declaracao(
                        null,
                        pacienteMapper.paraDomain(dadosGeraisDocumentoDto.getPaciente()),
                        psicologoMapper.paraDomain(dadosGeraisDocumentoDto.getPsicologo()),
                        dadosGeraisDocumentoDto.getDataEmissao(),
                        dadosGeraisDocumentoDto.getDataValidade(),
                        dadosGeraisDocumentoDto.getAssinaturaPsicologo(),
                        dadosGeraisDocumentoDto.getMotivo(),
                        dadosGeraisDocumentoDto.getDescricao(),
                        dadosGeraisDocumentoDto.getFinalidade()
                );
            case 3:
                return new RelatorioPsicologico(
                        null,
                        pacienteMapper.paraDomain(dadosGeraisDocumentoDto.getPaciente()),
                        psicologoMapper.paraDomain(dadosGeraisDocumentoDto.getPsicologo()),
                        dadosGeraisDocumentoDto.getDataEmissao(),
                        dadosGeraisDocumentoDto.getDataValidade(),
                        dadosGeraisDocumentoDto.getAssinaturaPsicologo(),
                        dadosGeraisDocumentoDto.getSolicitante(),
                        dadosGeraisDocumentoDto.getObjetivo(),
                        dadosGeraisDocumentoDto.getHistorico(),
                        dadosGeraisDocumentoDto.getProcedimentosUtilizados(),
                        dadosGeraisDocumentoDto.getDescricaoResultados(),
                        dadosGeraisDocumentoDto.getConclusao(),
                        dadosGeraisDocumentoDto.getRecomendacoes(),
                        dadosGeraisDocumentoDto.getSigilo()
                );
            case 4:
                return new LaudoPsicologico(
                        null,
                        pacienteMapper.paraDomain(dadosGeraisDocumentoDto.getPaciente()),
                        psicologoMapper.paraDomain(dadosGeraisDocumentoDto.getPsicologo()),
                        dadosGeraisDocumentoDto.getDataEmissao(),
                        dadosGeraisDocumentoDto.getDataValidade(),
                        dadosGeraisDocumentoDto.getAssinaturaPsicologo(),
                        dadosGeraisDocumentoDto.getSolicitante(),
                        dadosGeraisDocumentoDto.getObjetivo(),
                        dadosGeraisDocumentoDto.getHistorico(),
                        dadosGeraisDocumentoDto.getProcedimentosUtilizados(),
                        dadosGeraisDocumentoDto.getDescricaoResultados(),
                        dadosGeraisDocumentoDto.getConclusao(),
                        dadosGeraisDocumentoDto.getRespostaDemanda(),
                        dadosGeraisDocumentoDto.getRecomendacoes(),
                        dadosGeraisDocumentoDto.getSigilo()
                );
            case 5:
                return new ParecerPsicologico(
                        null,
                        pacienteMapper.paraDomain(dadosGeraisDocumentoDto.getPaciente()),
                        psicologoMapper.paraDomain(dadosGeraisDocumentoDto.getPsicologo()),
                        dadosGeraisDocumentoDto.getDataEmissao(),
                        dadosGeraisDocumentoDto.getDataValidade(),
                        dadosGeraisDocumentoDto.getAssinaturaPsicologo(),
                        dadosGeraisDocumentoDto.getSolicitante(),
                        dadosGeraisDocumentoDto.getObjetivo(),
                        dadosGeraisDocumentoDto.getConclusao(),
                        dadosGeraisDocumentoDto.getSigilo(),
                        dadosGeraisDocumentoDto.getContextualizacao(),
                        dadosGeraisDocumentoDto.getFundamentacao(),
                        dadosGeraisDocumentoDto.getAnaliseDoCaso()
                );
            default:
                throw new TipoDocumentoInvalidoException(MENSAGEM_TIPO_DOCUMENTO_INVALIDO);
        }

    }
}
