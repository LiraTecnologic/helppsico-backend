package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.documento.Documento;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.DocumentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface DocumentoMapper {
    DocumentoEntity paraEntity(Documento domain);
    Documento paraDomain(DocumentoEntity entity);
}
