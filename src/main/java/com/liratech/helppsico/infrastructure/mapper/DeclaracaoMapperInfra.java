package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.documento.Declaracao;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.DeclaracaoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeclaracaoMapperInfra {

    private final PacienteMapperInfra pacienteMapper;
    private final PsicologoMapperInfra psicologoMapper;

    public Declaracao paraDomain(DeclaracaoEntity entity) {
        return new Declaracao(
                entity.getId(),
                pacienteMapper.paraDomain(entity.getPaciente()),
                psicologoMapper.paraDomain(entity.getPsicologo()),
                entity.getDataEmissao(),
                entity.getDataValidade(),
                entity.getAssinaturaPsicologo(),
                entity.getMotivo(),
                entity.getDescricao(),
                entity.getFinalidade()
        );
    }

    public DeclaracaoEntity paraEntity(Declaracao domain) {
        return new DeclaracaoEntity(
                domain.getId(),
                pacienteMapper.paraEntity(domain.getPaciente()),
                psicologoMapper.paraEntity(domain.getPsicologo()),
                domain.getDataEmissao(),
                domain.getDataValidade(),
                domain.getAssinaturaPsicologo(),
                domain.getMotivo(),
                domain.getDescricao(),
                domain.getFinalidade()
        );
    }
}
