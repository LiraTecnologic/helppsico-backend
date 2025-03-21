package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.entrypoint.dto.psicologo.ProntuarioDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProntuarioMapper {

    @Mapping(target = "paciente.cpf", source = "cpf")
    @Mapping(target = "psicologo.crp", source = "crp")
    Prontuario paraDomain (ProntuarioDto prontuarioDto);

    @Mapping(target = "cpf", source = "paciente.cpf")
    @Mapping(target = "crp", source = "psicologo.crp")
    ProntuarioDto paraDto (Prontuario prontuario);
}
