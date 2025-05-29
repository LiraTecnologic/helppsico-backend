package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.HorarioPsicologo;
import com.liratech.helppsico.infrastructure.repositories.entities.HorarioPsicologoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        implementationName = "HorarioPsicologoMapperInfraImpl",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {PsicologoMapperInfra.class, HorarioMapperInfra.class}
)
public interface HorarioPsicologoMapperInfra {
    HorarioPsicologoEntity paraEntity (HorarioPsicologo horarioPsicologo);
    HorarioPsicologo paraDomain (HorarioPsicologoEntity horarioPsicologoEntity);
}
