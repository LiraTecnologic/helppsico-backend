package com.liratech.helppsico.mapper;

import com.liratech.helppsico.builders.HorarioPsicologoBuilder;
import com.liratech.helppsico.domain.HorarioPsicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioPsicologoDto;
import com.liratech.helppsico.entrypoint.mapper.HorarioPsicologoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class HorarioPsicologoMapperTest {

    private final HorarioPsicologoMapper horarioPsicologoMapper = Mappers.getMapper(HorarioPsicologoMapper.class);

    @Test
    @DisplayName("Caso de sucesso na tranformação de DTO para Domain")
    void transformacaoHorarioPsicologoDtoParaDomain() {
        HorarioPsicologoDto horarioPsicologoDto = HorarioPsicologoBuilder.criarHorarioPsicologoDto();
        HorarioPsicologo horarioPsicologo = horarioPsicologoMapper.paraDomain(horarioPsicologoDto);

        Assertions.assertNotNull(horarioPsicologo);
        //Validar todos os Psicologos

        Assertions.assertEquals(horarioPsicologoDto.getData(), horarioPsicologo.getData());
        Assertions.assertEquals(horarioPsicologoDto.getHora(), horarioPsicologo.getHora());
    }

    @Test
    @DisplayName("Caso de sucesso na tranformação de Domain para DTO")
    void transformacaoHorarioPsicologoDomainParaDto() {
        HorarioPsicologo horarioPsicologo = HorarioPsicologoBuilder.criarHorarioPsicologo();
        HorarioPsicologoDto horarioPsicologoDto = horarioPsicologoMapper.paraDto(horarioPsicologo);

        Assertions.assertNotNull(horarioPsicologoDto);
        Assertions.assertEquals(horarioPsicologo.getId(), horarioPsicologoDto.getId());

        Assertions.assertNotNull(horarioPsicologoDto.getPsicologo());
        //Validar todos os Psicologos

        Assertions.assertEquals(horarioPsicologo.getData(), horarioPsicologoDto.getData());
        Assertions.assertEquals(horarioPsicologo.getHora(), horarioPsicologoDto.getHora());
    }
}