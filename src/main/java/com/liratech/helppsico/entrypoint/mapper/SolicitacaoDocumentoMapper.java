package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import com.liratech.helppsico.entrypoint.dto.documento.SolicitacaoDocumentoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        implementationName = "SolicitacaoDocumentoMapperImpl",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {PacienteMapper.class, PsicologoMapper.class}
)
public interface SolicitacaoDocumentoMapper {
    SolicitacaoDocumentoDto paraDto(SolicitacaoDocumento domain);
    SolicitacaoDocumento paraDomain(SolicitacaoDocumentoDto dto);
}
