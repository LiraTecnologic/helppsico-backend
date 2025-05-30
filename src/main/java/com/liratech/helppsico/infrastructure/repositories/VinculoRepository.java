package com.liratech.helppsico.infrastructure.repositories;

import com.liratech.helppsico.infrastructure.repositories.entities.VinculoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VinculoRepository extends JpaRepository<VinculoEntity, UUID> {
    Page<VinculoEntity> findAllByPsicologoId(UUID idPsicologo, Pageable pageable);
    Optional<VinculoEntity> findByPacienteId(UUID idPaciente);
}
