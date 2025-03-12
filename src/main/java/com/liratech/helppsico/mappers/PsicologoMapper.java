package com.liratech.helppsico.mappers;

import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PsicologoMapper {
    PsicologoEntity paraEntity (Psicologo psicologo);
    Psicologo paraDomain (PsicologoEntity psicologoEntity);
    List<Psicologo> praDomains (List<PsicologoEntity> psicologoEntities);
}
