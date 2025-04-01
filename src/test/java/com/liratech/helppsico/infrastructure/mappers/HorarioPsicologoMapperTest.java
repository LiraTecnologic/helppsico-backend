package com.liratech.helppsico.infrastructure.mappers;

import com.liratech.helppsico.builders.HorarioPsicologoBuilder;
import com.liratech.helppsico.domain.HorarioPsicologo;
import com.liratech.helppsico.infrastructure.repositories.entities.HorarioPsicologoEntity;
import com.liratech.helppsico.validators.PsicologoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class HorarioPsicologoMapperTest {

    private final HorarioPsicologoMapper horarioPsicologoMapper = Mappers.getMapper(HorarioPsicologoMapper.class);

    @Test
    @DisplayName("Caso de sucesso na tranformação de HorarioPsicologo Domain para Entity")
    void testeTransdormacaoHorarioPsicologoDomainParaEntity() {
        HorarioPsicologo horarioPsicologo = HorarioPsicologoBuilder.criarHorarioPsicologo();
        HorarioPsicologoEntity horarioPsicologoEntity = horarioPsicologoMapper.paraEntity(horarioPsicologo);

        Assertions.assertNotNull(horarioPsicologoEntity);
        Assertions.assertEquals(horarioPsicologo.getId(), horarioPsicologoEntity.getId());
        PsicologoValidator.validaPsicologoDomainParaEntity(horarioPsicologo.getPsicologo(), horarioPsicologoEntity.getPsicologo());
        Assertions.assertEquals(horarioPsicologo.getData(), horarioPsicologoEntity.getData());
        Assertions.assertEquals(horarioPsicologo.getHora(), horarioPsicologoEntity.getHora());
    }

    @Test
    @DisplayName("Caso de sucesso na tranformação de HorarioPsicologo Entity para Domain")
    void testeTransdormacaoHorarioPsicologoEntityParaDomain() {
        HorarioPsicologoEntity horarioPsicologoEntity = HorarioPsicologoBuilder.criarHorarioPsicologoEntity();
        HorarioPsicologo horarioPsicologo = horarioPsicologoMapper.paraDomain(horarioPsicologoEntity);

        Assertions.assertNotNull(horarioPsicologo);
        Assertions.assertEquals(horarioPsicologoEntity.getId(), horarioPsicologo.getId());
        PsicologoValidator.validaPsicologoEntityParaDomain(horarioPsicologoEntity.getPsicologo(), horarioPsicologo.getPsicologo());
        Assertions.assertEquals(horarioPsicologoEntity.getData(), horarioPsicologo.getData());
        Assertions.assertEquals(horarioPsicologoEntity.getHora(), horarioPsicologo.getHora());
    }
}