package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",implementationName = "PacienteMapperInfraImpl", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PacienteMapperInfra {
    PacienteEntity paraEntity(Paciente paciente);
    Paciente paraDomain(PacienteEntity pacienteEntity);
}
