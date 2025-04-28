package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.infrastructure.repositories.entities.EnderecoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EnderecoMapper {
    Endereco paraDomain (EnderecoEntity enderecoEntity);
    EnderecoEntity paraEntity (Endereco endereco);
}
