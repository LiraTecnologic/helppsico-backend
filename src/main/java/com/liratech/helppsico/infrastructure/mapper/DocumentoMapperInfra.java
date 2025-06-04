package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.domain.documento.*;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DocumentoMapperInfra {
    public AtestadoMapperInfra atestadoMapper;
    public DeclaracaoMapperInfra declaracaoMapper;
    public RelatorioPsicologicoMapperInfra relatorioPsicologicoMapper;
    public LaudoPsicologicoMapperInfra laudoPsicologicoMapper;
    public ParecerPsicologicoMapperInfra parecerPsicologicoMapper;

    public DocumentoEntity paraEntity(Documento domain) {

        if (domain instanceof Atestado){
            return atestadoMapper.paraEntity((Atestado) domain);
        }else if (domain instanceof Declaracao){
            return declaracaoMapper.paraEntity((Declaracao) domain);
        }else if (domain instanceof RelatorioPsicologico){
            return relatorioPsicologicoMapper.paraEntity((RelatorioPsicologico) domain);
        }else if (domain instanceof LaudoPsicologico){
            return laudoPsicologicoMapper.paraEntity((LaudoPsicologico) domain);
        }else if (domain instanceof ParecerPsicologico){
            return parecerPsicologicoMapper.paraEntity((ParecerPsicologico) domain);
        }

        throw new IllegalArgumentException("Tipo de documento não reconhecido: " + domain.getClass().getName());
    }

    public Documento paraDomain(DocumentoEntity entity) {

        if (entity instanceof AtestadoEntity){
            return atestadoMapper.paraDomain((AtestadoEntity) entity);
        }else if (entity instanceof DeclaracaoEntity){
            return declaracaoMapper.paraDomain((DeclaracaoEntity) entity);
        }else if (entity instanceof RelatorioPsicologicoEntity){
            return relatorioPsicologicoMapper.paraDomain((RelatorioPsicologicoEntity) entity);
        }else if (entity instanceof LaudoPsicologicoEntity){
            return laudoPsicologicoMapper.paraDomain((LaudoPsicologicoEntity) entity);
        }else if (entity instanceof ParecerPsicologicoEntity){
            return parecerPsicologicoMapper.paraDomain((ParecerPsicologicoEntity) entity);
        }

        throw new IllegalArgumentException("Tipo de documento não reconhecido: " + entity.getClass().getName());
    }
}
