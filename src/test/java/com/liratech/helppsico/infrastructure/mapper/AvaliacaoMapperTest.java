package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.AvaliacaoBuilder;
import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.infrastructure.repositories.entities.AvaliacaoEntity;
import com.liratech.helppsico.validators.PacienteValidator;
import com.liratech.helppsico.validators.PsicologoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class AvaliacaoMapperTest {

    private final AvaliacaoMapper avaliacaoMapper = Mappers.getMapper(AvaliacaoMapper.class);

    @Test
    @DisplayName("Caso de sucesso na transformação de Domain para Entity")
    void testeTransformacaoDomainParaEntity() {
        Avaliacao avaliacao = AvaliacaoBuilder.criarAvaliacao();
        AvaliacaoEntity avaliacaoEntity = avaliacaoMapper.paraEntity(avaliacao);

        Assertions.assertNotNull(avaliacaoEntity);
        Assertions.assertEquals(avaliacao.getId(), avaliacaoEntity.getId());
        PsicologoValidator.validaPsicologoDomainParaEntity(avaliacao.getPsicologo(), avaliacaoEntity.getPsicologo());
        PacienteValidator.validaPacienteDomainParaEntity(avaliacao.getPaciente(), avaliacaoEntity.getPaciente());
        Assertions.assertEquals(avaliacao.getNota(), avaliacaoEntity.getNota());
        Assertions.assertEquals(avaliacao.getComentario(), avaliacaoEntity.getComentario());
    }

    @Test
    @DisplayName("Caso de sucesso na transformação de Entity para Domain")
    void testeTrasnformacaoEntityParaDomain() {
        AvaliacaoEntity avaliacaoEntity = AvaliacaoBuilder.criarAvaliacaoEntity();
        Avaliacao avaliacao = avaliacaoMapper.paraDomain(avaliacaoEntity);

        Assertions.assertNotNull(avaliacao);
        Assertions.assertEquals(avaliacaoEntity.getId(), avaliacao.getId());
        PsicologoValidator.validaPsicologoEntityParaDomain(avaliacaoEntity.getPsicologo(), avaliacao.getPsicologo());
        PacienteValidator.validaPacienteEntityParaDomain(avaliacaoEntity.getPaciente(), avaliacao.getPaciente());
        Assertions.assertEquals(avaliacaoEntity.getNota(), avaliacao.getNota());
        Assertions.assertEquals(avaliacaoEntity.getComentario(), avaliacao.getComentario());
    }
}