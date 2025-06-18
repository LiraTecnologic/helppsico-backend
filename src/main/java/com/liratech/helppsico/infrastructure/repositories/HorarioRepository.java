package com.liratech.helppsico.infrastructure.repositories;

import com.liratech.helppsico.infrastructure.repositories.entities.HorarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HorarioRepository extends JpaRepository<HorarioEntity, UUID> {
    List<HorarioEntity> findAllByPsicologoId(UUID idPsicologo);
}
