package com.liratech.helppsico.infrastructure.repositories;

import com.liratech.helppsico.infrastructure.repositories.entities.documento.DocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoEntity, UUID> {
}
