package com.liratech.helppsico.mapper;

import com.liratech.helppsico.builders.VinculoBuilder;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.StatusVinculo;
import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.entrypoint.dto.StatusVinculoDto;
import com.liratech.helppsico.entrypoint.dto.VinculoDto;
import com.liratech.helppsico.entrypoint.mapper.VinculoMapper;
import com.liratech.helppsico.validator.PacienteValidator;
import com.liratech.helppsico.validator.PsicologoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class VinculoMapperTest {

    private final VinculoMapper vinculoMapper = Mappers.getMapper(VinculoMapper.class);

    @Test
    @DisplayName("Caso de sucesso na transformação de DTO para Domain")
    void transformacaoVinculoDtoParaDomain() {
        VinculoDto vinculoDto = VinculoBuilder.criarVinculoDto();
        Vinculo vinculo = vinculoMapper.paraDomain(vinculoDto);

        Assertions.assertNotNull(vinculo);
        Assertions.assertEquals(vinculoDto.getId(), vinculo.getId());
        PacienteValidator.validaPacienteDtoParaDomain(vinculoDto.getPaciente(), vinculo.getPaciente());
        PsicologoValidator.validaPsicologoDtoParaDomain(vinculoDto.getPsicologo(), vinculo.getPsicologo());
        Assertions.assertEquals(StatusVinculo.valueOf(vinculoDto.getStatus().name()), vinculo.getStatus());
    }

    @Test
    @DisplayName("Caso de sucesso na transformação de Doamin para Dto")
    void transformacaoVinculoDomainParaDto() {
        Vinculo vinculo = VinculoBuilder.criarVinculo();
        VinculoDto vinculoDto = vinculoMapper.paraDto(vinculo);

        Assertions.assertNotNull(vinculoDto);
        Assertions.assertEquals(vinculo.getId(), vinculoDto.getId());
        PacienteValidator.validaPacienteDomainParaDto(vinculo.getPaciente(), vinculoDto.getPaciente());
        PsicologoValidator.validaPsicologoDomainParaDto(vinculo.getPsicologo(), vinculoDto.getPsicologo());
        Assertions.assertEquals(StatusVinculoDto.valueOf(vinculo.getStatus().name()), vinculoDto.getStatus());
    }
}