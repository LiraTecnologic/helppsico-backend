package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PacienteMapper {
    Paciente paraDomain(PacienteDto pacienteDto);
    PacienteDto paraDto(Paciente paciente);
}
