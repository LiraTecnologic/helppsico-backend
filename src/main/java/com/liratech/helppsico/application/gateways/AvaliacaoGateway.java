package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.Avaliacao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AvaliacaoGateway {
    Avaliacao salvar(Avaliacao avaliacao);

    List<Avaliacao> listarPorPsicologo(UUID id);

    Optional<Avaliacao> buscarPorId(UUID id);

    void deletar(UUID id);
}

