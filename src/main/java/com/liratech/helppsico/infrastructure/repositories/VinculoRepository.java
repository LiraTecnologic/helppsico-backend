package com.liratech.helppsico.infrastructure.repositories;

import com.liratech.helppsico.infrastructure.repositories.entities.VinculoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VinculoRepository extends JpaRepository<VinculoEntity, UUID> {
    Page<VinculoEntity> findAllByPsicologo_Id(UUID idPsicologo);
    Optional<VinculoEntity> findByPaciente_Id(UUID idPaciente);
}
