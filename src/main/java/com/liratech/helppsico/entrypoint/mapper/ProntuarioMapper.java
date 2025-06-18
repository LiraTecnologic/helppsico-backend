package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.entrypoint.dto.psicologo.ProntuarioDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        implementationName = "ProntuarioMapperImpl",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {PsicologoMapper.class, PacienteMapper.class, ConsultaMapper.class}
)
public interface ProntuarioMapper {
    Prontuario paraDomain (ProntuarioDto dto);
    ProntuarioDto paraDto (Prontuario domain);
}
