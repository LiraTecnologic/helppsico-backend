package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.HorarioPsicologo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface HorarioPsicologoGateway {
    HorarioPsicologo salvar(HorarioPsicologo horario);
    Page<HorarioPsicologo> listarPorPsicologo(UUID id, Pageable pageable);
    Optional<HorarioPsicologo> buscarPorId(UUID id);
    void deletar(UUID id);
}
