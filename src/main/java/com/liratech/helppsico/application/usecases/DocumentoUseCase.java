package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.gateways.DocumentoGateway;
import com.liratech.helppsico.application.usecases.dto.DadosGeraisDocumentoDto;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.documento.*;
import com.liratech.helppsico.entrypoint.mapper.EnderecoMapper;
import com.liratech.helppsico.entrypoint.mapper.PacienteMapper;
import com.liratech.helppsico.entrypoint.mapper.PsicologoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentoUseCase {
    private final SolicitacaoDocumentoUseCase solicitacaoDocumentoUseCase;
    private final DocumentoGateway gateway;
    private final PacienteMapper pacienteMapper;
    private final PsicologoMapper psicologoMapper;
    private final EnderecoMapper enderecoMapper;

    public Documento salvar(UUID idSolicitacao, DadosGeraisDocumentoDto dadosGeraisDocumentoDto){
        log.info("Iniciando salvamento do documento. ID da solicitação: {}, Dados gerais: {}", idSolicitacao, dadosGeraisDocumentoDto);

        SolicitacaoDocumento solicitacaoDocumento = solicitacaoDocumentoUseCase.buscarPorId(idSolicitacao);

        Documento documento = null;

        TipoDocumento tipoDocumento = solicitacaoDocumento.getTipoDocumento();

        switch (tipoDocumento.getCodigo()){
            case 1:
                documento = new Atestado(
                        null,
                        pacienteMapper.paraDomain(dadosGeraisDocumentoDto.getPaciente()),
                        psicologoMapper.paraDomain(dadosGeraisDocumentoDto.getPsicologo()),
                        dadosGeraisDocumentoDto.getDataEmissao(),
                        dadosGeraisDocumentoDto.getDataValidade(),
                        dadosGeraisDocumentoDto.getAssinaturaPsicologo(),
                        dadosGeraisDocumentoDto.getDataAtendimento(),
                        enderecoMapper.paraDomain(dadosGeraisDocumentoDto.getLocal()),
                        dadosGeraisDocumentoDto.getDescricao(),
                        dadosGeraisDocumentoDto.getDescrcaoEstadoPsicologico(),
                        dadosGeraisDocumentoDto.getPeridoAfastamento(),
                        dadosGeraisDocumentoDto.getFinalidade()
                );
                break;
            case 2:
                documento = new Declaracao(
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
                break;
            case 3:
                documento = new RelatorioPsicologo(
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
                break;
            case 4:
                documento = new LaudoPsicologo(
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
                break;
            case 5:
                documento = new ParecerPsicologo(
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
                break;
        }

        Documento documentoSalvo = gateway.salvar(documento);

        log.info("Documento criado e salvo com sucesso. Documento: {}", documentoSalvo);
        return documentoSalvo;
    }
}
