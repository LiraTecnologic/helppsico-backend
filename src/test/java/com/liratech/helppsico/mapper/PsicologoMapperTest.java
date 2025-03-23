package com.liratech.helppsico.mapper;

import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import com.liratech.helppsico.entrypoint.mapper.PsicologoMapper;
import com.liratech.helppsico.validator.PsicologoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

class PsicologoMapperTest {

    private final PsicologoMapper psicologoMapper = Mappers.getMapper(PsicologoMapper.class);

    @Test
    @DisplayName("Caso de sucesso na transformação de DTO para Domain")
    void transformacaoPsicologoDtoParaDomain() {
        PsicologoDto psicologoDto = PsicologoBuilder.criarPsicologoDto();
        Psicologo psicologo = psicologoMapper.paraDomain(psicologoDto);

        Assertions.assertNotNull(psicologo);
        PsicologoValidator.validaPsicologoDtoParaDomain(psicologoDto, psicologo);
    }

    @Test
    @DisplayName("Caso de sucesso na transformação de Domain para Dto")
    void transformacaoPsicologoDomainParaDto() {
        Psicologo psicologo = PsicologoBuilder.criarPsicologo();
        PsicologoDto psicologoDto = psicologoMapper.paraDto(psicologo);

        Assertions.assertNotNull(psicologoDto);
        PsicologoValidator.validaPsicologoDomainParaDto(psicologo, psicologoDto);
    }

    @Test
    @DisplayName("Caso de sucesso na transformação de Domain para Dtos")
    void transformacaoPsicologoDomainParaDtos() {
        List<Psicologo> psicologos = PsicologoBuilder.criarListaPsicologo();
        List<PsicologoDto> psicologoDtos = psicologoMapper.paraDtos(psicologos);

        Assertions.assertNotNull(psicologoDtos);
        Assertions.assertEquals(psicologos.size(), psicologoDtos.size());

        for(int i = 0; i < psicologoDtos.size(); i++){
            Psicologo psicologo = psicologos.get(i);
            PsicologoDto psicologoDto = psicologoDtos.get(i);

            PsicologoValidator.validaPsicologoDomainParaDto(psicologo, psicologoDto);
        }
    }
}