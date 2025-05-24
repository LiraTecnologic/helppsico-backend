package com.liratech.helppsico.entrypoint.mapper;


import com.liratech.helppsico.domain.ValidacaoCrp;
import com.liratech.helppsico.entrypoint.dto.ValidacaoCrpDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", implementationName = "ValidacaoCrpMapperImpl", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ValidacaoCrpMapper {
    ValidacaoCrpDto paraDto(ValidacaoCrp domain);
    ValidacaoCrp paraDomain(ValidacaoCrpDto dto);
}
