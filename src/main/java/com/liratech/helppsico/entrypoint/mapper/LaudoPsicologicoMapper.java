package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.documento.LaudoPsicologico;
import com.liratech.helppsico.entrypoint.dto.documento.LaudoPsicologicoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LaudoPsicologicoMapper {

    private final PacienteMapper pacienteMapper;
    private final PsicologoMapper psicologoMapper;

    public LaudoPsicologico paraDomain(LaudoPsicologicoDto dto){
        return new LaudoPsicologico(
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
                dto.getRespostaDemanda(),
                dto.getRecomendacoes(),
                dto.getSigilo()
        );
    }

    public LaudoPsicologicoDto paraDto(LaudoPsicologico domain){
        return new LaudoPsicologicoDto(
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
                domain.getRespostaDemanda(),
                domain.getRecomendacoes(),
                domain.getSigilo()
        );
    }
}
