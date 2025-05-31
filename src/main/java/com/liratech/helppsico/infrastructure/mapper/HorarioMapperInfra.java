package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.Horario;
import com.liratech.helppsico.infrastructure.repositories.entities.HorarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        implementationName = "HorarioMapperInfraImp",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {PsicologoMapperInfra.class}
)
public interface HorarioMapperInfra {
    HorarioEntity paraEntity(Horario domain);
    Horario paraDomain(HorarioEntity entity);
}
