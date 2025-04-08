package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.Avaliacao;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AvaliacaoGateway {
    Avaliacao salvar(Avaliacao avaliacao);

    Page<Avaliacao> listarPorPsicologo(UUID id);

    Optional<Avaliacao> buscarPorId(UUID id);

    void deletar(UUID id);
}

