package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface SolicitacaoDocumentoGateway {
    SolicitacaoDocumento salvar(SolicitacaoDocumento solicitacaoDocumento);

    Page<SolicitacaoDocumento> listarPorPsicologo(UUID idPsicologo, Pageable pageable);

    Optional<SolicitacaoDocumento> consultarPorId(UUID id);

    void deletar(UUID id);
}
