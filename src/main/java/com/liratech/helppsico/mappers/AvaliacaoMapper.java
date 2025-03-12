package com.liratech.helppsico.mappers;

import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.infrastructure.repositories.entities.AvaliacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AvaliacaoMapper {
    AvaliacaoEntity paraEntity (Avaliacao avaliacao);
    Avaliacao paraDomain (AvaliacaoEntity avaliacaoEntity);
}
