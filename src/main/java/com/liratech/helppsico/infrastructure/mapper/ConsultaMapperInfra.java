package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.infrastructure.repositories.entities.ConsultaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", implementationName = "ConsultaMapperInfraImpl", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ConsultaMapperInfra {
    ConsultaEntity paraEntity (Consulta consulta);
    Consulta paraDomain (ConsultaEntity consultaEntity);
}
