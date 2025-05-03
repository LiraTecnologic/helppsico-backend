package com.liratech.helppsico.infrastructure.repositories;

import com.liratech.helppsico.infrastructure.repositories.entities.FotoEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FotoRepository extends JpaRepository<FotoEntity, UUID> {
    Optional<FotoEntity> findByPsicologo(PsicologoEntity psicologo);
    Optional<FotoEntity> findByPaciente(PacienteEntity paciente);
}
