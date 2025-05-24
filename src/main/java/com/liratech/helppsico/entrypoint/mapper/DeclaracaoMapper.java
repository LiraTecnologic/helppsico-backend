package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.documento.Declaracao;
import com.liratech.helppsico.entrypoint.dto.documento.DeclaracaoDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeclaracaoMapper {

    private final PacienteMapper pacienteMapper;
    private final PsicologoMapper psicologoMapper;

    public Declaracao paraDomain (DeclaracaoDto dto){
        return new Declaracao(
                dto.getId(),
                pacienteMapper.paraDomain(dto.getPaciente()),
                psicologoMapper.paraDomain(dto.getPsicologo()),
                dto.getDataEmissao(),
                dto.getDataValidade(),
                dto.getAssinaturaPsicologo(),
                dto.getMotivo(),
                dto.getDescricao(),
                dto.getFinalidade()
        );
    }
    public DeclaracaoDto paraDto (Declaracao domain){
        return new DeclaracaoDto(
                domain.getId(),
                pacienteMapper.paraDto(domain.getPaciente()),
                psicologoMapper.paraDto(domain.getPsicologo()),
                domain.getDataEmissao(),
                domain.getDataValidade(),
                domain.getAssinaturaPsicologo(),
                domain.getMotivo(),
                domain.getDescricao(),
                domain.getFinalidade()
        );
    }
}
