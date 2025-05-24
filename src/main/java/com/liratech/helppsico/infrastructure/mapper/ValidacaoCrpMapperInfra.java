package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.ValidacaoCrp;
import com.liratech.helppsico.infrastructure.repositories.entities.ValidacaoCrpEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", implementationName = "ValidacaoCrpMapperInfraImpl", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ValidacaoCrpMapperInfra {
    ValidacaoCrpEntity paraEntity(ValidacaoCrp domain);
    ValidacaoCrp paraDomain(ValidacaoCrpEntity entity);
}
