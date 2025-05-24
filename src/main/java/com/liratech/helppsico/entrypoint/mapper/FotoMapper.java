package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.Foto;
import com.liratech.helppsico.domain.HorarioPsicologo;
import com.liratech.helppsico.entrypoint.dto.FotoDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioPsicologoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", implementationName = "FotoMapperImpl", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FotoMapper {
    Foto paraDomain (FotoDto fotoDto);
    FotoDto paraDto (Foto foto);
}
