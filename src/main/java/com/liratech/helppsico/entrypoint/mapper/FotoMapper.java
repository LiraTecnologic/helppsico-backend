package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.Foto;
import com.liratech.helppsico.entrypoint.dto.FotoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        implementationName = "FotoMapperImpl",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {PsicologoMapper.class, PacienteMapper.class}
)
public interface FotoMapper {
    Foto paraDomain (FotoDto fotoDto);
    FotoDto paraDto (Foto foto);
}
