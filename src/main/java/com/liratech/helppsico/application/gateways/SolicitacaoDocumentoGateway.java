package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;

import java.util.Optional;
import java.util.UUID;

public interface SolicitacaoDocumentoGateway {
    SolicitacaoDocumento salvar(SolicitacaoDocumento solicitacaoDocumento);

    Optional<SolicitacaoDocumento> consultarPorId(UUID id);

    Optional<SolicitacaoDocumento> consultarPorPacientePsicologo(UUID idPsicologo, UUID ipPaciente);

    void deletar(UUID id);
}
