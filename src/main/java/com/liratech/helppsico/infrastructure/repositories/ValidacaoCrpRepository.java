package com.liratech.helppsico.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ValidacaoCrpRepository extends JpaRepository<ValidacaoCrpEntity, UUID> {
}
