package com.liratech.helppsico.infrastructure.mappers;

import com.liratech.helppsico.domain.HorarioPsicologo;
import com.liratech.helppsico.infrastructure.repositories.entities.HorarioPsicologoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface HorarioPsicologoMapper {
    HorarioPsicologoEntity paraEntity (HorarioPsicologo horarioPsicologo);
    HorarioPsicologo paraDomain (HorarioPsicologoEntity horarioPsicologoEntity);
}
