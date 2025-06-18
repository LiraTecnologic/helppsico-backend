package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.domain.documento.*;
import com.liratech.helppsico.entrypoint.dto.documento.DocumentoDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentoMapper {
    private final AtestadoMapper atestadoMapper;
    private final DeclaracaoMapper declaracaoMapper;
    private final LaudoPsicologicoMapper laudoPsicologicoMapper;
    private final ParecerPsicologicoMapper parecerPsicologicoMapper;
    private final RelatorioPsicologicoMapper relatorioPsicologicoMapper;

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
