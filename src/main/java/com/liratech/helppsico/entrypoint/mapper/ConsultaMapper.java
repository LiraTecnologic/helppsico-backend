package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.entrypoint.dto.consulta.ConsultaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        implementationName = "ConsultaMapperImpl",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {PsicologoMapper.class, PacienteMapper.class, EnderecoMapper.class}
)
public interface ConsultaMapper {
    Consulta paraDomain (ConsultaDto consultaDto);
    ConsultaDto paraDto (Consulta consulta);
}
