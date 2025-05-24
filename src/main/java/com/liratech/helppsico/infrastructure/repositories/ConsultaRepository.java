package com.liratech.helppsico.infrastructure.repositories;

import com.liratech.helppsico.infrastructure.repositories.entities.ConsultaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaEntity, UUID> {

    @Query("SELECT c " +
            "FROM Consulta c " +
            "WHERE c.finalizada = false AND c.psicologo.id = :idPsicologo AND c.paciente.id = :idPaciente")
    Page<ConsultaEntity> consultarConsultasFuturas(
            @Param("idPsicologo") UUID idPsicologo,
            @Param("idPaciente") UUID idPaciente,
            Pageable pageable
    );

    @Query("SELECT c " +
            "FROM Consulta c " +
            "WHERE c.finalizada = true AND c.psicologo.id = :idPsicologo AND c.paciente.id = :idPaciente")
    Page<ConsultaEntity> consultarHistorico(
            @Param("idPsicologo") UUID idPsicologo,
            @Param("idPaciente") UUID idPaciente,
            Pageable pageable
    );

    @Query("SELECT c " +
            "FROM Consulta c " +
            "WHERE DAY(c.dataHora) = :diaDoMes")
    List<ConsultaEntity> consultarConsultasMesmoDia(@Param("diaDoMes") int diaDoMes);
}

