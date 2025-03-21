package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.entrypoint.dto.VinculoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface VinculoMapper {
    Vinculo paraDomain (VinculoDto vinculoDto);
    VinculoDto paraDto (Vinculo vinculo);
}
