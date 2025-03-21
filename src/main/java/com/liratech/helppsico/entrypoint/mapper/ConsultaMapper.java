package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.entrypoint.dto.consulta.ConsultaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ConsultaMapper {
    Consulta paraDomain (ConsultaDto consultaDto);

    @Mapping(target = "psicologo.foto", ignore = true)
    @Mapping(target = "paciente.foto", ignore = true)
    ConsultaDto paraDto (Consulta consulta);

    @Mapping(target = "psicologo.foto", ignore = true)
    @Mapping(target = "paciente.foto", ignore = true)
    List<ConsultaDto> paraDtos (List<Consulta> consultas);
}
