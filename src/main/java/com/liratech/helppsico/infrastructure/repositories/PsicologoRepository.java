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

    Page<PsicologoEntity> findByNome(String nome);

    @Query("""
            SELECT p
            FROM psicologos p
            LEFT JOIN avaliacoes a ON a.id_psicologo = p.id_psicologo
            GROUP BY p.id_psicologo
            ORDER BY AVG(a.nota) DESC
            """)
    Page<PsicologoEntity> consultarMelhoresAvaliados(Pageable pageable);

    Optional<PsicologoEntity> findByCrp(String crp);
}
