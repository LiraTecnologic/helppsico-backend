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
        DocumentoEntity documentoEntity = null;

        if (domain instanceof Atestado){
            documentoEntity = atestadoMapper.paraEntity((Atestado) domain);
        }

        if (domain instanceof Declaracao){
            documentoEntity = declaracaoMapper.paraEntity((Declaracao) domain);
        }

        if (domain instanceof RelatorioPsicologico){
            documentoEntity = relatorioPsicologicoMapper.paraEntity((RelatorioPsicologico) domain);
        }

        if (domain instanceof LaudoPsicologico){
            documentoEntity = laudoPsicologicoMapper.paraEntity((LaudoPsicologico) domain);
        }

        if (domain instanceof ParecerPsicologico){
            documentoEntity = parecerPsicologicoMapper.paraEntity((ParecerPsicologico) domain);
        }

        return documentoEntity;
    }

    public Documento paraDomain(DocumentoEntity entity) {
        Documento documentoDomain = null;

        if (entity instanceof AtestadoEntity){
            documentoDomain = atestadoMapper.paraDomain((AtestadoEntity) entity);
        }

        if (entity instanceof DeclaracaoEntity){
            documentoDomain = declaracaoMapper.paraDomain((DeclaracaoEntity) entity);
        }

        if (entity instanceof RelatorioPsicologicoEntity){
            documentoDomain = relatorioPsicologicoMapper.paraDomain((RelatorioPsicologicoEntity) entity);
        }

        if (entity instanceof LaudoPsicologicoEntity){
            documentoDomain = laudoPsicologicoMapper.paraDomain((LaudoPsicologicoEntity) entity);
        }

        if (entity instanceof ParecerPsicologicoEntity){
            documentoDomain = parecerPsicologicoMapper.paraDomain((ParecerPsicologicoEntity) entity);
        }

        return documentoDomain;
    }
}
