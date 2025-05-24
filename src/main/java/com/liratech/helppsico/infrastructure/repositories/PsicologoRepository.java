package com.liratech.helppsico.infrastructure.repositories;

import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PsicologoRepository extends JpaRepository<PsicologoEntity, UUID> {

    Page<PsicologoEntity> findAllByNome(String nome, Pageable pageable);

    @Query("""
            SELECT p
            FROM Psicologo p
            LEFT JOIN Avaliacao a ON a.psicologo.id = p.id
            GROUP BY p.id
            ORDER BY AVG(a.nota) DESC
            """)
    Page<PsicologoEntity> consultarMelhoresAvaliados(Pageable pageable);

    Optional<PsicologoEntity> findByCrp(String crp);
}
