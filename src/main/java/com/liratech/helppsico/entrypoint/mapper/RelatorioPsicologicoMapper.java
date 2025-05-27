package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.documento.RelatorioPsicologico;
import com.liratech.helppsico.entrypoint.dto.documento.RelatorioPsicologicoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RelatorioPsicologicoMapper {

    private final PacienteMapper pacienteMapper;
    private final PsicologoMapper psicologoMapper;

    public RelatorioPsicologico paraDomain(RelatorioPsicologicoDto dto){
        return new RelatorioPsicologico(
                dto.getId(),
                pacienteMapper.paraDomain(dto.getPaciente()),
                psicologoMapper.paraDomain(dto.getPsicologo()),
                dto.getDataEmissao(),
                dto.getDataValidade(),
                dto.getAssinaturaPsicologo(),
                dto.getSolicitante(),
                dto.getObjetivo(),
                dto.getHistorico(),
                dto.getProcedimentosUtilizados(),
                dto.getDescricaoResultados(),
                dto.getConclusao(),
                dto.getRecomendacoes(),
                dto.getSigilo()
        );
    }

    public RelatorioPsicologicoDto paraDto(RelatorioPsicologico domain){
        return new RelatorioPsicologicoDto(
                domain.getId(),
                pacienteMapper.paraDto(domain.getPaciente()),
                psicologoMapper.paraDto(domain.getPsicologo()),
                domain.getDataEmissao(),
                domain.getDataValidade(),
                domain.getAssinaturaPsicologo(),
                domain.getSolicitante(),
                domain.getObjetivo(),
                domain.getHistorico(),
                domain.getProcedimentosUtilizados(),
                domain.getDescricaoResultados(),
                domain.getConclusao(),
                domain.getRecomendacoes(),
                domain.getSigilo()
        );
    }
}
