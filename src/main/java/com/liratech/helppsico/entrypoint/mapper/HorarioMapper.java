package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.Horario;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        implementationName = "HorarioMapperImp",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {PsicologoMapper.class}
)
public interface HorarioMapper {
    Horario paraDomain(HorarioDto dto);
    HorarioDto paraDto(Horario domain);
}
