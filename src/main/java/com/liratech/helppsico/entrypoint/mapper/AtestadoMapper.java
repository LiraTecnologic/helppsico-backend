package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.documento.Atestado;
import com.liratech.helppsico.entrypoint.dto.documento.AtestadoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AtestadoMapper {

    private final PacienteMapper pacienteMapper;
    private final PsicologoMapper psicologoMapper;
    private final EnderecoMapper enderecoMapper;

    public Atestado paraDomain(AtestadoDto dto){
        return new Atestado(
                dto.getId(),
                pacienteMapper.paraDomain(dto.getPaciente()),
                psicologoMapper.paraDomain(dto.getPsicologo()),
                dto.getDataEmissao(),
                dto.getDataValidade(),
                dto.getAssinaturaPsicologo(),
                dto.getDataAtendimento(),
                enderecoMapper.paraDomain(dto.getLocal()),
                dto.getDescricao(),
                dto.getDescricaoEstadoPsicologico(),
                dto.getPeriodoAfastamento(),
                dto.getFinalidade()
        );
    }

    public AtestadoDto paraDto(Atestado domain){
        return new AtestadoDto(
                domain.getId(),
                pacienteMapper.paraDto(domain.getPaciente()),
                psicologoMapper.paraDto(domain.getPsicologo()),
                domain.getDataEmissao(),
                domain.getDataValidade(),
                domain.getAssinaturaPsicologo(),
                domain.getDataAtendimento(),
                enderecoMapper.paraDto(domain.getLocal()),
                domain.getDescricao(),
                domain.getDescricaoEstadoPsicologico(),
                domain.getPeriodoAfastamento(),
                domain.getFinalidade()
        );
    }
}
