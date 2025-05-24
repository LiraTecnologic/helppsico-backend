package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.HorarioPsicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioPsicologoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", implementationName = "HorarioPsicologoMapperImpl", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface HorarioPsicologoMapper {
    HorarioPsicologo paraDomain (HorarioPsicologoDto horarioPsicologoDto);
    HorarioPsicologoDto paraDto (HorarioPsicologo horarioPsicologo);
}
