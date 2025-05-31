package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.Horario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HorarioGateway {
    Horario salvar(Horario horario);
    List<Horario> listarPorPsicologo(UUID idPsicologo);
    Optional<Horario> consultarPorId(UUID idHorario);
    void deletar(UUID idHorario);
}
