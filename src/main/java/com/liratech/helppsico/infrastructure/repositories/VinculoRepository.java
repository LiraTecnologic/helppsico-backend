package com.liratech.helppsico.infrastructure.repositories;

import com.liratech.helppsico.infrastructure.repositories.entities.VinculoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VinculoRepository extends JpaRepository<VinculoEntity, UUID> {
    @Query("""
            SELECT v
            FROM Vinculo v
            WHERE v.psicologo.id = :idPsicologo
            """)
    Page<VinculoEntity> findAllByPsicologoId(@Param("idPsicologo") UUID idPsicologo, Pageable pageable);

    @Query("""
            SELECT v
            FROM Vinculo v
            WHERE v.paciente.id = :idPaciente
            """)
    Page<VinculoEntity> findAllByPacienteId(@Param("idPaciente") UUID idPaciente, Pageable pageable);

    @Query("""
            SELECT v
            FROM Vinculo v
            WHERE v.paciente.id = :idPaciente AND v.status = 1
            """)
    Optional<VinculoEntity> consultarAtivoPorPaciente(UUID idPaciente);
}
