package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.documento.Documento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface DocumentoGateway {
    Documento salvar(Documento documento);

    Page<Documento> listarPorPaciente(UUID idPaciente, Pageable pageable);
}
