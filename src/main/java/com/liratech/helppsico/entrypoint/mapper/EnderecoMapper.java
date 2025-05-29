package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        implementationName = "EnderecoMapperImpl",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EnderecoMapper {
    Endereco paraDomain (EnderecoDto dto);
    EnderecoDto paraDto (Endereco domain);
}
