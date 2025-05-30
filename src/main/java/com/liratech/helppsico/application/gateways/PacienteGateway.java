package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface PacienteGateway {
    Paciente salvar(Paciente paciente);

    Optional<Paciente> consultarPorId(UUID id);

    Optional<Paciente> consultarPorEmail(String email);

    Page<Paciente> listarPorPsicologo(UUID idPsicologo, Pageable pageable);
}
