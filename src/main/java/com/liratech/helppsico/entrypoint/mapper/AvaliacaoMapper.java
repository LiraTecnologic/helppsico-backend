package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.entrypoint.dto.psicologo.AvaliacaoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",implementationName = "AvaliacaoMapperImpl", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AvaliacaoMapper {
    Avaliacao paraDomain (AvaliacaoDto dto);
    AvaliacaoDto paraDto (Avaliacao domain);
}
