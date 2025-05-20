package com.liratech.helppsico.infrastructure.repositories;

import com.liratech.helppsico.infrastructure.repositories.entities.HorarioPsicologoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HorarioPsicologoRepository extends JpaRepository<HorarioPsicologoEntity, UUID> {
    Page<HorarioPsicologoEntity> findAllByPsicologoId(UUID idPsicologo, Pageable pageable);
}
