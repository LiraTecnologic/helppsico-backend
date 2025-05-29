package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.Horario;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        implementationName = "HorarioMapperImpl",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface HorarioMapper {
    Horario paraDomain(HorarioDto dto);
    HorarioDto paraDto(Horario domain);
}
