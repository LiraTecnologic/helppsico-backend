package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ConsultaGateway {
    Consulta salvar(Consulta consulta);
    Optional<Consulta> consultarPorId(UUID id);
    Page<Consulta> consultarConsultasFuturas(UUID idPsicologo, UUID idPaciente, Pageable pageable);
    Page<Consulta> consultarHistorico(UUID idPsicologo, UUID idPaciente, Pageable pageable);
}
