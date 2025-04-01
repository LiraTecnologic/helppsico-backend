package com.liratech.helppsico.infrastructure.mappers;

import com.liratech.helppsico.builders.ProntuarioBuilder;
import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.infrastructure.repositories.entities.ProntuarioEntity;
import com.liratech.helppsico.validators.PacienteValidator;
import com.liratech.helppsico.validators.PsicologoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ProntuarioMapperTest {

    private final ProntuarioMapper prontuarioMapper = Mappers.getMapper(ProntuarioMapper.class);

    @Test
    @DisplayName("Caso de sucesso na transformação de Prontuario Domain para Entity")
    void testeTransformacaoProntuarioDomainParaEntity() {
        Prontuario prontuario = ProntuarioBuilder.criarProntuario();
        ProntuarioEntity prontuarioEntity = prontuarioMapper.paraEntity(prontuario);

        Assertions.assertNotNull(prontuarioEntity);
        Assertions.assertEquals(prontuario.getId(), prontuarioEntity.getId());
        Assertions.assertNotNull(prontuarioEntity.getPsicologo());
        PsicologoValidator.validaPsicologoDomainParaEntity(prontuario.getPsicologo(), prontuarioEntity.getPsicologo());
        Assertions.assertNotNull(prontuarioEntity.getPaciente());
        PacienteValidator.validaPacienteDomainParaEntity(prontuario.getPaciente(), prontuarioEntity.getPaciente());
        Assertions.assertEquals(prontuario.getTitulo(), prontuarioEntity.getTitulo());
        Assertions.assertEquals(prontuario.getConteudo(), prontuarioEntity.getConteudo());
    }

    @Test
    @DisplayName("Caso de sucesso na transformação de Prontuario Entity para Domain")
    void testeTransformacaoProntuarioEntityParaDomain() {
        ProntuarioEntity prontuarioEntity = ProntuarioBuilder.criarProntuarioEntity();
        Prontuario prontuario = prontuarioMapper.paraDomain(prontuarioEntity);

        Assertions.assertNotNull(prontuario);
        Assertions.assertEquals(prontuarioEntity.getId(), prontuario.getId());
        Assertions.assertNotNull(prontuario.getPsicologo());
        PsicologoValidator.validaPsicologoEntityParaDomain(prontuarioEntity.getPsicologo(),prontuario.getPsicologo());
        Assertions.assertNotNull(prontuario.getPaciente());
        PacienteValidator.validaPacienteEntityParaDomain(prontuarioEntity.getPaciente(), prontuario.getPaciente());
        Assertions.assertEquals(prontuarioEntity.getTitulo(), prontuario.getTitulo());
        Assertions.assertEquals(prontuarioEntity.getConteudo(), prontuario.getConteudo());
    }
}