package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.documento.ParecerPsicologico;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.ParecerPsicologicoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParecerPsicologicoMapperInfra {

    private final PacienteMapperInfra pacienteMapper;
    private final PsicologoMapperInfra psicologoMapper;

    public ParecerPsicologico paraDomain(ParecerPsicologicoEntity entity) {
        return new ParecerPsicologico(
                entity.getId(),
                pacienteMapper.paraDomain(entity.getPaciente()),
                psicologoMapper.paraDomain(entity.getPsicologo()),
                entity.getDataEmissao(),
                entity.getDataValidade(),
                entity.getAssinaturaPsicologo(),
                entity.getSolicitante(),
                entity.getObjetivo(),
                entity.getConclusao(),
                entity.getSigilo(),
                entity.getContextualizacao(),
                entity.getFundamentacao(),
                entity.getAnaliseDoCaso()
        );
    }

    public ParecerPsicologicoEntity paraEntity(ParecerPsicologico domain) {
        return new ParecerPsicologicoEntity(
                domain.getId(),
                pacienteMapper.paraEntity(domain.getPaciente()),
                psicologoMapper.paraEntity(domain.getPsicologo()),
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
