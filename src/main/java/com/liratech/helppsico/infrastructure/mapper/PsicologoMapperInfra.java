package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        implementationName = "PsicologoMapperInfraImp",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {EnderecoMapperInfra.class}
)
public interface PsicologoMapperInfra {
    PsicologoEntity paraEntity (Psicologo psicologo);
    Psicologo paraDomain (PsicologoEntity psicologoEntity);
}
