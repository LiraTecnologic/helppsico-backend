package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.entrypoint.dto.psicologo.AvaliacaoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        implementationName = "AvaliacaoMapperImpl",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {PsicologoMapper.class, PacienteMapper.class}
)
public interface AvaliacaoMapper {
    Avaliacao paraDomain (AvaliacaoDto dto);
    AvaliacaoDto paraDto (Avaliacao domain);
}
