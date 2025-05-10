package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.domain.Psicologo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ProntuarioGateway {
    Prontuario salvar(Prontuario prontuario);
    Optional<Prontuario> consultarPorId(UUID id);
    Page<Prontuario> listarPorPaciente(Paciente paciente, Pageable pageable);
    Page<Prontuario> listarPorPsicologo(Psicologo psicologo, Pageable pageable);
    void deletar(UUID id);
}
