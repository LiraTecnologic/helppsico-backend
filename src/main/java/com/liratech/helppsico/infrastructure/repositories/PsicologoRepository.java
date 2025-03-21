package com.liratech.helppsico.infrastructure.repositories;

import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PsicologoRepository extends JpaRepository<PsicologoEntity, UUID> {

    Optional<PsicologoEntity> findByNome(String nome);

    Page<PsicologoEntity> findAllMelhoresAvaliados(Pageable pageable);

    Optional<PsicologoEntity> findByCrp(String crp);

    Page<PsicologoEntity> findAllPsicologos(Pageable pageable);
}
