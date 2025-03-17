package com.liratech.helppsico.infrastructure.mappers;

import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.infrastructure.repositories.entities.VinculoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface VinculoMapper {
    VinculoEntity paraEntity (Vinculo vinculo);
    Vinculo paraDomain (VinculoEntity vinculoEntity);
}
