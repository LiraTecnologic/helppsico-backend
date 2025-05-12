package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.Vinculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentoGateway {
    Vinculo salvar(Vinculo vinculo);

    Page<Vinculo> listar(Pageable pageable);
}
