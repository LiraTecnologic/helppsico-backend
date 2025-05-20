package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.documento.Documento;
import com.liratech.helppsico.entrypoint.dto.documento.DocumentoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface DocumentoMapper {
    Documento paraDomain(DocumentoDto dto);
    DocumentoDto paraDto(Documento domain);
}
