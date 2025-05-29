package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.entrypoint.dto.VinculoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        implementationName = "VinculoMapperImpl",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {PacienteMapper.class, PsicologoMapper.class}
)
public interface VinculoMapper {
    Vinculo paraDomain (VinculoDto dto);
    VinculoDto paraDto (Vinculo domain);
}
