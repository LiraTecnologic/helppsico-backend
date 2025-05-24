package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", implementationName = "PacienteMapperImpl", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PacienteMapper {
    Paciente paraDomain(PacienteDto pacienteDto);
    PacienteDto paraDto(Paciente paciente);
}
