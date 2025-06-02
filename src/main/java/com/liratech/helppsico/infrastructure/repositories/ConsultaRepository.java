package com.liratech.helppsico.infrastructure.repositories;

import com.liratech.helppsico.infrastructure.repositories.entities.ConsultaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaEntity, UUID> {

    @Query("SELECT c " +
            "FROM Consulta c " +
            "WHERE c.finalizada = false " +
            "AND c.psicologo.id = :idPsicologo " +
            "AND c.paciente.id = :idPaciente" +
            "AND c.data >= :hoje")
    Page<ConsultaEntity> consultarConsultasFuturasPaciente(
            @Param("idPaciente") UUID idPaciente,
            @Param("idPsicologo") UUID idPsicologo,
            @Param("hoje") LocalDate hoje,
            Pageable pageable
    );

    @Query("SELECT c " +
            "FROM Consulta c " +
            "WHERE c.finalizada = true AND c.psicologo.id = :idPsicologo AND c.paciente.id = :idPaciente")
    Page<ConsultaEntity> consultarHistorico(
            @Param("idPaciente") UUID idPaciente,
            @Param("idPsicologo") UUID idPsicologo,
            Pageable pageable
    );

    @Query("""
            SELECT c
            FROM Consulta c
            WHERE FUNCTION('DAY', c.data) = :diaDoMes AND c.psicologo.id = :idPsicologo
            """)
    List<ConsultaEntity> consultarConsultasMesmoDia(
            @Param("diaDoMes") int diaDoMes,
            @Param("idPsicologo") UUID idPsicologo
    );
}

