package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.ValidacaoCrp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ValidacaoCrpGateway {
    ValidacaoCrp salvar(ValidacaoCrp validacaoCrp);

    Optional<ValidacaoCrp> consultarPorId(UUID id);

    Page<ValidacaoCrp> listar(Pageable pageable);

    Optional<ValidacaoCrp> consultarPorPsicologo(UUID id);

    void deletar(UUID id);
}
