package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.SolicitacaoDocumentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",implementationName = "SolicitacaoDocumentoMapperInfraImpl", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface SolicitacaoDocumentoMapperInfra {
    SolicitacaoDocumentoEntity paraEntity(SolicitacaoDocumento domain);
    SolicitacaoDocumento paraDomain(SolicitacaoDocumentoEntity entity);
}
