package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.Vinculo;
import org.springframework.data.domain.Page;

public interface DocumentoGateway {
    Vinculo salvar(Vinculo vinculo);

    Page<Vinculo> listar();
}
