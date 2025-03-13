package com.liratech.helppsico.mapper;

import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.entrypoint.dto.consulta.ConsultaDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ConsultaMapper {
    Consulta paraDomain (ConsultaDto consultaDto);
    ConsultaDto paraDto (Consulta consulta);
    List<ConsultaDto> paraDtos (List<Consulta> consultas);
}
