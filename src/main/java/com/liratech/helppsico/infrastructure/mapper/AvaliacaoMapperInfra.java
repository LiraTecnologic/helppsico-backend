package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.infrastructure.repositories.entities.AvaliacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", implementationName = "AvaliacaoMapperInfraImpl",  unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AvaliacaoMapperInfra {
    AvaliacaoEntity paraEntity (Avaliacao avaliacao);
    Avaliacao paraDomain (AvaliacaoEntity avaliacaoEntity);
}
