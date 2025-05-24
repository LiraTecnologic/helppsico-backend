package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.infrastructure.repositories.entities.VinculoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", implementationName = "VinculoMapperInfraImpl", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface VinculoMapperInfra {
    VinculoEntity paraEntity (Vinculo vinculo);
    Vinculo paraDomain (VinculoEntity vinculoEntity);
}
