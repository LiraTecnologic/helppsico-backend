package com.liratech.helppsico.mapper;

import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.entrypoint.dto.psicologo.ProntuarioDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProntuarioMapper {
    Prontuario paraDomain (ProntuarioDto prontuarioDto);
    ProntuarioDto paraDto (Prontuario prontuario);
}
