package com.liratech.helppsico.infrastructure.mappers;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PacienteMapper {
    PacienteEntity paraEntity(Paciente paciente);
    Paciente paraDomain(PacienteEntity pacienteEntity);
}
