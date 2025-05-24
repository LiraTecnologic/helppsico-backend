package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import com.liratech.helppsico.validators.PsicologoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

class PsicologoMapperTest {

    private final PsicologoMapperInfra psicologoMapper = Mappers.getMapper(PsicologoMapperInfra.class);

    @Test
    @DisplayName("Caso de sucesso na transformação de Psicologo Domian para Entity")
    void testeTransformacaoPsicologoDomainParaEntity() {
        Psicologo psicologo = PsicologoBuilder.criarPsicologo();
        PsicologoEntity psicologoEntity = psicologoMapper.paraEntity(psicologo);

        Assertions.assertNotNull(psicologoEntity);
        PsicologoValidator.validaPsicologoDomainParaEntity(psicologo, psicologoEntity);
    }

    @Test
    @DisplayName("Caso de sucesso na transformação de Psicologo Entity para Domian")
    void testeTransformacaoPsicologoEntityParaDomain() {
        PsicologoEntity psicologoEntity = PsicologoBuilder.criarPsicologoEntity();
        Psicologo psicologo = psicologoMapper.paraDomain(psicologoEntity);

        Assertions.assertNotNull(psicologo);
        PsicologoValidator.validaPsicologoEntityParaDomain(psicologoEntity, psicologo);
    }

    @Test
    @DisplayName("Caso de sucesso na transformação de List Psicologo Entity para Domian")
    void testeTransformacaoListPsicologoEntityParaDomain() {
        List<PsicologoEntity> psicologoEntities = PsicologoBuilder.criarListaPsicologoEntity();
        List<Psicologo> psicologos = psicologoMapper.paraDomains(psicologoEntities);

        Assertions.assertNotNull(psicologos);
        Assertions.assertEquals(psicologoEntities.size(), psicologos.size());

        for(int i=0; i<psicologoEntities.size(); i++){
            PsicologoEntity psicologoEntity = psicologoEntities.get(i);
            Psicologo psicologo = psicologos.get(i);

            PsicologoValidator.validaPsicologoEntityParaDomain(psicologoEntity, psicologo);
        }
    }

    @Test
    @DisplayName("Caso de sucesso na transformação de Page Psicologo Entity para Domian")
    void testeTransformacaoPagePsicologoEntityParaDomain() {
        List<PsicologoEntity> psicologosEntity = PsicologoBuilder.criarListaPsicologoEntity();
        Page<PsicologoEntity> pageEntity = new PageImpl<>(psicologosEntity, PageRequest.of(0, 2), psicologosEntity.size());
        Page<Psicologo> pageDomain = psicologoMapper.paraDomainsPage(pageEntity);

        Assertions.assertNotNull(pageDomain);
        Assertions.assertEquals(pageEntity.getTotalElements(), pageDomain.getTotalElements());
        Assertions.assertEquals(pageEntity.getSize(), pageDomain.getSize());

        List<Psicologo> psicologos = pageDomain.getContent();
        for(int i = 0; i < pageDomain.getSize(); i++){
            PsicologoEntity psicologoEntity = psicologosEntity.get(i);
            Psicologo psicologo = psicologos.get(i);

            PsicologoValidator.validaPsicologoEntityParaDomain(psicologoEntity,psicologo);
        }
    }
}