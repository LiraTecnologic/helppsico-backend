package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.documento.RelatorioPsicologico;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.RelatorioPsicologicoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RelatorioPsicologicoMapperInfra {

    private final PacienteMapperInfra pacienteMapper;
    private final PsicologoMapperInfra psicologoMapper;

    public RelatorioPsicologico paraDomain(RelatorioPsicologicoEntity entity) {
        return new RelatorioPsicologico(
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
                entity.getRecomendacoes(),
                entity.getSigilo()
        );
    }

    public RelatorioPsicologicoEntity paraEntity(RelatorioPsicologico domain) {
        return new RelatorioPsicologicoEntity(
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
                domain.getRecomendacoes(),
                domain.getSigilo()
        );
    }
}
