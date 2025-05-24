package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.entrypoint.dto.psicologo.ProntuarioDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", implementationName = "ProntuarioMapperImpl", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProntuarioMapper {
    Prontuario paraDomain (ProntuarioDto prontuarioDto);
    ProntuarioDto paraDto (Prontuario prontuario);
}
