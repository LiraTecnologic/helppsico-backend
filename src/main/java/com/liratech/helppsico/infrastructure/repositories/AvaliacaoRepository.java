package com.liratech.helppsico.infrastructure.repositories;

import com.liratech.helppsico.infrastructure.repositories.entities.AvaliacaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoEntity, UUID> {
    Page<AvaliacaoEntity> findAllByPsicologoId(UUID id, Pageable pageable);
    Optional<AvaliacaoEntity> findByPacienteIdAndPsicologoId(UUID idPaciente, UUID idPsicologo);
}
