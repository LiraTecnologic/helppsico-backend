package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.documento.*;
import com.liratech.helppsico.entrypoint.dto.documento.DocumentoDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
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
        }else if (domain instanceof Declaracao){
            return declaracaoMapper.paraDto((Declaracao) domain);
        }else if (domain instanceof RelatorioPsicologico){
            return relatorioPsicologicoMapper.paraDto((RelatorioPsicologico) domain);
        }else if (domain instanceof LaudoPsicologico){
            return laudoPsicologicoMapper.paraDto((LaudoPsicologico) domain);
        }else if (domain instanceof ParecerPsicologico){
            return parecerPsicologicoMapper.paraDto((ParecerPsicologico) domain);
        }

        throw new IllegalArgumentException("Tipo de documento não reconhecido: " + domain.getClass().getName());
    }
}
