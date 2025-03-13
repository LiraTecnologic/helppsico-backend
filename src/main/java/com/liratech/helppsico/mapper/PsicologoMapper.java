package com.liratech.helppsico.mapper;

import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PsicologoMapper {
    Psicologo paraDomain (PsicologoDto psicologoDto);
    PsicologoDto paraDto (Psicologo psicologo);
    List<PsicologoDto> paraDtos (List<Psicologo> psicologos);
}
