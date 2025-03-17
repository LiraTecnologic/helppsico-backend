package com.liratech.helppsico.infrastructure.mappers;

import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.infrastructure.repositories.entities.ConsultaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ConsultaMapper {
    ConsultaEntity paraEntity (Consulta consulta);
    Consulta paraDomain (ConsultaEntity consultaEntity);
    List<Consulta> paraDomains(List<ConsultaEntity> consultaEntities);
}
