package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.Endereco;

import java.util.Optional;
import java.util.UUID;

public interface EnderecoGateway {
    Endereco salvar(Endereco endereco);

    Optional<Endereco> consultarPorId(UUID id);
}
