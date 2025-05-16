package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.documento.Documento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentoGateway {
    Documento salvar(Documento documento);

    Page<Documento> listar(Pageable pageable);
}
