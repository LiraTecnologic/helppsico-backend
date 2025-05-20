package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.TipoDocumentoInvalidoException;
import com.liratech.helppsico.application.gateways.DocumentoGateway;
import com.liratech.helppsico.application.usecases.dto.DadosGeraisDocumentoDto;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.documento.*;
import com.liratech.helppsico.entrypoint.mapper.EnderecoMapper;
import com.liratech.helppsico.entrypoint.mapper.PacienteMapper;
import com.liratech.helppsico.entrypoint.mapper.PsicologoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentoUseCase {
    private final SolicitacaoDocumentoUseCase solicitacaoDocumentoUseCase;
    private final DocumentoGateway gateway;

    public Documento salvar(UUID idSolicitacao, DadosGeraisDocumentoDto dadosGeraisDocumentoDto) throws TipoDocumentoInvalidoException {
        log.info("Iniciando salvamento do documento. ID da solicitação: {}, Dados gerais: {}", idSolicitacao, dadosGeraisDocumentoDto);

        SolicitacaoDocumento solicitacaoDocumento = solicitacaoDocumentoUseCase.buscarPorId(idSolicitacao);
        TipoDocumento tipoDocumento = solicitacaoDocumento.getTipoDocumento();

        Documento documento = DocumentoFactory.criar(dadosGeraisDocumentoDto, tipoDocumento);

        Documento documentoSalvo = gateway.salvar(documento);

        log.info("Documento criado e salvo com sucesso. Documento: {}", documentoSalvo);
        return documentoSalvo;
    }

    public Page<Documento> listar (Pageable pageable) {
        log.info("Iniciando processo para listar os documentos.");

        Page<Documento> listagemDoc = gateway.listar(pageable);

        log.info("Listagem de documentos completa. Listagem: {}", listagemDoc);
        return listagemDoc;
    }
}
