package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;

import java.util.Optional;
import java.util.UUID;

public interface SolicitacaoDocumentoGateway {
    SolicitacaoDocumento salvar(SolicitacaoDocumento solicitacaoDocumento);

    Optional<SolicitacaoDocumento> consultarPorId(UUID id);

    void deletar(UUID id);
}
