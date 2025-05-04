package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.Foto;
import com.liratech.helppsico.infrastructure.repositories.entities.FotoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FotoMapper {
    Foto paraDomain (FotoEntity fotoEntity);
    FotoEntity paraEntity (Foto foto);
}
