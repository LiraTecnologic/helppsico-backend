package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.documento.*;
import com.liratech.helppsico.entrypoint.dto.documento.DocumentoDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DocumentoMapper {
    public AtestadoMapper atestadoMapper;
    public DeclaracaoMapper declaracaoMapper;
    public LaudoPsicologicoMapper laudoPsicologicoMapper;
    public ParecerPsicologicoMapper parecerPsicologicoMapper;
    public RelatorioPsicologicoMapper relatorioPsicologicoMapper;

    public DocumentoDto paraDto(Documento domain){

        if (domain instanceof Atestado){
            return atestadoMapper.paraDto((Atestado) domain);
        }

        if (domain instanceof Declaracao){
            return declaracaoMapper.paraDto((Declaracao) domain);
        }

        if (domain instanceof RelatorioPsicologico){
            return relatorioPsicologicoMapper.paraDto((RelatorioPsicologico) domain);
        }

        if (domain instanceof LaudoPsicologico){
            return laudoPsicologicoMapper.paraDto((LaudoPsicologico) domain);
        }

        if (domain instanceof ParecerPsicologico){
            return parecerPsicologicoMapper.paraDto((ParecerPsicologico) domain);
        }
        return null;
    }
}
