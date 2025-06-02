package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConsultaGateway {
    Consulta salvar(Consulta consulta);
    Optional<Consulta> consultarPorId(UUID id);
    Page<Consulta> consultarConsultasFuturasPaciente(UUID idPaciente, UUID idPsicologo, Pageable pageable);
    Page<Consulta> consultarHistoricoPaciente(UUID idPaciente, UUID idPsicologo, Pageable pageable);
    List<Consulta> consultarConsultasMesmoDia(int dayOfMonth, UUID idPsicologo);
    void deletar(UUID id);
}
