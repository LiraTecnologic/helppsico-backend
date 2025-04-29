package com.liratech.helppsico.application.gateways;

import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ValidacaoCrpGateway {
    ValidacaoCrp salvar(ValidacaoCrp validacaoCrp);

    Optional<ValidacaoCrp> consultarPorId(UUID id);

    Page<ValidacaoCrp> listar(Pageable pageable);

    void deletar(UUID id);
}
