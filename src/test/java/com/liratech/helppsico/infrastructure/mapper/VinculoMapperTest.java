package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.VinculoBuilder;
import com.liratech.helppsico.domain.StatusVinculo;
import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.infrastructure.repositories.entities.StatusVinculoEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.VinculoEntity;
import com.liratech.helppsico.validators.PacienteValidator;
import com.liratech.helppsico.validators.PsicologoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class VinculoMapperTest {

    private final VinculoMapper vinculoMapper = Mappers.getMapper(VinculoMapper.class);

    @Test
    @DisplayName("Caso de sucesso na trasnformação de Vinculo Domain para Entity")
    void testeTrasnformacaoVinculoDomainParaEntity() {
        Vinculo vinculo = VinculoBuilder.criarVinculo();
        VinculoEntity vinculoEntity = vinculoMapper.paraEntity(vinculo);

        Assertions.assertNotNull(vinculoEntity);
        Assertions.assertNotNull(vinculoEntity.getPsicologo());
        PsicologoValidator.validaPsicologoDomainParaEntity(vinculo.getPsicologo(), vinculoEntity.getPsicologo());
        Assertions.assertNotNull(vinculoEntity.getPaciente());
        PacienteValidator.validaPacienteDomainParaEntity(vinculo.getPaciente(), vinculoEntity.getPaciente());
        Assertions.assertEquals(StatusVinculoEntity.valueOf(vinculo.getStatus().name()), vinculoEntity.getStatus());
    }

    @Test
    @DisplayName("Caso de sucesso na trasnformação de Vinculo Entity para Domain")
    void testeTrasnformacaoVinculoEntityParaDomain() {
        VinculoEntity vinculoEntity = VinculoBuilder.criarVinculoEntity();
        Vinculo vinculo = vinculoMapper.paraDomain(vinculoEntity);

        Assertions.assertNotNull(vinculo);
        Assertions.assertNotNull(vinculo.getPsicologo());
        PsicologoValidator.validaPsicologoEntityParaDomain(vinculoEntity.getPsicologo(), vinculo.getPsicologo());
        Assertions.assertNotNull(vinculo.getPaciente());
        PacienteValidator.validaPacienteEntityParaDomain(vinculoEntity.getPaciente(), vinculo.getPaciente());
        Assertions.assertEquals(StatusVinculo.valueOf(vinculoEntity.getStatus().name()), vinculo.getStatus());
    }
}