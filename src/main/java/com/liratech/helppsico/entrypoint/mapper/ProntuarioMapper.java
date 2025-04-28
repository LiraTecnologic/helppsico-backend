package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.entrypoint.dto.psicologo.ProntuarioDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProntuarioMapper {

    Prontuario paraDomain (ProntuarioDto prontuarioDto);

    @Mapping(target = "psicologo.foto", ignore = true)
    @Mapping(target = "paciente.foto", ignore = true)
    ProntuarioDto paraDto (Prontuario prontuario);
}
