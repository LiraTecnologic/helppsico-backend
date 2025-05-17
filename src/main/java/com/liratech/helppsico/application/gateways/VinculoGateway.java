package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.Vinculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface VinculoGateway {
    Vinculo salvar(Vinculo vinculo);
    Optional<Vinculo> consultarPorId(UUID id);
    void deletar(UUID id);
    Page<Vinculo> listarPorIdPsicologo(UUID idPsicologo, Pageable pageable);
    Optional<Vinculo> consultarPorIdPaciente(UUID idPaciente);
}
