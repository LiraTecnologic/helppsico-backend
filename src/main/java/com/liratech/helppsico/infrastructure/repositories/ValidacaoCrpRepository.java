package com.liratech.helppsico.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ValidacaoCrpRepository extends JpaRepository<ValidacaoCrpEntity, UUID> {
}
