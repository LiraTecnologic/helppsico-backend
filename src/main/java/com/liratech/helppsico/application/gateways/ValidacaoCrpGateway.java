package com.liratech.helppsico.application.gateways;

import java.util.Optional;
import java.util.UUID;

public interface ValidacaoCrpGateway {
    ValidacaoCrp salvar(ValidacaoCrp validacaoCrp);

    Optional<ValidacaoCrp> consultarPorId(UUID id);

    void deletar(UUID id);
}
