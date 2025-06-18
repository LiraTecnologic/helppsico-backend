package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.documento.Atestado;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.AtestadoEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AtestadoMapperInfra {

    private PacienteMapperInfra pacienteMapper;
    private PsicologoMapperInfra psicologoMapper;
    private EnderecoMapperInfra enderecoMapper;

    public Atestado paraDomain(AtestadoEntity entity){
        return new Atestado(
                entity.getId(),
                pacienteMapper.paraDomain(entity.getPaciente()),
                psicologoMapper.paraDomain(entity.getPsicologo()),
                entity.getDataEmissao(),
                entity.getDataValidade(),
                entity.getAssinaturaPsicologo(),
                entity.getDataAtendimento(),
                enderecoMapper.paraDomain(entity.getLocal()),
                entity.getDescricao(),
                entity.getDescricaoEstadoPsicologico(),
                entity.getPeriodoAfastamento(),
                entity.getFinalidade()
        );
    }

    public AtestadoEntity paraEntity(Atestado domain){
        return new AtestadoEntity(
                domain.getId(),
                pacienteMapper.paraEntity(domain.getPaciente()),
                psicologoMapper.paraEntity(domain.getPsicologo()),
                domain.getDataEmissao(),
                domain.getDataValidade(),
                domain.getAssinaturaPsicologo(),
                domain.getDataAtendimento(),
                enderecoMapper.paraEntity(domain.getLocal()),
                domain.getDescricao(),
                domain.getDescricaoEstadoPsicologico(),
                domain.getPeriodoAfastamento(),
                domain.getFinalidade()
        );
    }
}
