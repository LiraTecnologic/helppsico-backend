package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.infrastructure.repositories.entities.EnderecoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        implementationName = "EnderecoMapperInfraImpl",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EnderecoMapperInfra {
    Endereco paraDomain (EnderecoEntity entity);
    EnderecoEntity paraEntity (Endereco domain);
}
