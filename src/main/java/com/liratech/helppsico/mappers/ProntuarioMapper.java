package com.liratech.helppsico.mappers;

import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.infrastructure.repositories.entities.ProntuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "service", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProntuarioMapper {
    ProntuarioEntity paraEntity (Prontuario prontuario);
    Prontuario paraDomain (ProntuarioEntity prontuarioEntity);
}
