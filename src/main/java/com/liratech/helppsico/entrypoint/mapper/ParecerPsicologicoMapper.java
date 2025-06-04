package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.documento.ParecerPsicologico;
import com.liratech.helppsico.entrypoint.dto.documento.ParecerPsicologicoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParecerPsicologicoMapper {

    private final PacienteMapper pacienteMapper;
    private final PsicologoMapper psicologoMapper;

    public ParecerPsicologico paraDomain(ParecerPsicologicoDto dto){
        return new ParecerPsicologico(
                dto.getId(),
                pacienteMapper.paraDomain(dto.getPaciente()),
                psicologoMapper.paraDomain(dto.getPsicologo()),
                dto.getDataEmissao(),
                dto.getDataValidade(),
                dto.getAssinaturaPsicologo(),
                dto.getSolicitante(),
                dto.getObjetivo(),
                dto.getConclusao(),
                dto.getSigilo(),
                dto.getContextualizacao(),
                dto.getFundamentacao(),
                dto.getAnaliseDoCaso()
        );
    }

    public ParecerPsicologicoDto paraDto(ParecerPsicologico domain){
        return new ParecerPsicologicoDto(
                domain.getId(),
                pacienteMapper.paraDto(domain.getPaciente()),
                psicologoMapper.paraDto(domain.getPsicologo()),
                domain.getDataEmissao(),
                domain.getDataValidade(),
                domain.getAssinaturaPsicologo(),
                domain.getSolicitante(),
                domain.getObjetivo(),
                domain.getConclusao(),
                domain.getSigilo(),
                domain.getContextualizacao(),
                domain.getFundamentacao(),
                domain.getAnaliseDoCaso()
        );
    }
}