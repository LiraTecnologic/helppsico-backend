package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        implementationName = "PsicologoMapperImp",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {EnderecoMapper.class}
)
public interface PsicologoMapper {
    Psicologo paraDomain (PsicologoDto dto);
    PsicologoDto paraDto (Psicologo domain);
}
