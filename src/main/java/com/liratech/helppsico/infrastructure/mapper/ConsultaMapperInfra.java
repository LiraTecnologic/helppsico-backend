package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.infrastructure.repositories.entities.ConsultaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        implementationName = "ConsultaMapperInfraImp",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {PsicologoMapperInfra.class, PacienteMapperInfra.class, EnderecoMapperInfra.class, HorarioMapperInfra.class}
)
public interface ConsultaMapperInfra {
    ConsultaEntity paraEntity (Consulta domain);
    Consulta paraDomain (ConsultaEntity entity);
}
