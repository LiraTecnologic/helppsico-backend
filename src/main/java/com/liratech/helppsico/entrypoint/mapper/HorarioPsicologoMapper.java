package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.HorarioPsicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioPsicologoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface HorarioPsicologoMapper {
    HorarioPsicologo paraDomain (HorarioPsicologoDto horarioPsicologoDto);

    @Mapping(target = "psicologo.foto", ignore = true)
    HorarioPsicologoDto paraDto (HorarioPsicologo horarioPsicologo);
}
