package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.documento.LaudoPsicologico;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.LaudoPsicologicoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LaudoPsicologicoMapperInfra {

    private final PacienteMapperInfra pacienteMapper;
    private final PsicologoMapperInfra psicologoMapper;

    public LaudoPsicologico paraDomain(LaudoPsicologicoEntity entity) {
        return new LaudoPsicologico(
                entity.getId(),
                pacienteMapper.paraDomain(entity.getPaciente()),
                psicologoMapper.paraDomain(entity.getPsicologo()),
                entity.getDataEmissao(),
                entity.getDataValidade(),
                entity.getAssinaturaPsicologo(),
                entity.getSolicitante(),
                entity.getObjetivo(),
                entity.getHistorico(),
                entity.getProcedimentosUtilizados(),
                entity.getDescricaoResultados(),
                entity.getConclusao(),
                entity.getRespostaDemanda(),
                entity.getRecomendacoes(),
                entity.getSigilo()
        );
    }

    public LaudoPsicologicoEntity paraEntity(LaudoPsicologico domain) {
        return new LaudoPsicologicoEntity(
                domain.getId(),
                pacienteMapper.paraEntity(domain.getPaciente()),
                psicologoMapper.paraEntity(domain.getPsicologo()),
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