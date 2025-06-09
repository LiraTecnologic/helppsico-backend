package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.builders.VinculoBuilder;
import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.entrypoint.dto.VinculoDto;
import com.liratech.helppsico.infrastructure.mapper.PacienteMapperInfra;
import com.liratech.helppsico.infrastructure.mapper.PsicologoMapperInfra;
import com.liratech.helppsico.infrastructure.mapper.VinculoMapperInfraImpl;
import com.liratech.helppsico.validators.VinculoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VinculoMapperTest {

    @Mock
    private PacienteMapper pacienteMapper;

    @Mock
    private PsicologoMapper psicologoMapper;

    @InjectMocks
    private VinculoMapperImpl mapper;
    private Vinculo domainTest;
    private VinculoDto dtoTest;

    @Test
    void testeVinculoDomainParaDto() {
        domainTest = VinculoBuilder.criarVinculo();

        Mockito.when(pacienteMapper.paraDto(Mockito.any())).thenReturn(PacienteBuilder.criarPacienteDto());
        Mockito.when(psicologoMapper.paraDto(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoDto());

        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        VinculoValidator.validaVinculoMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeVinculoDtoParaDomain() {
        dtoTest = VinculoBuilder.criarVinculoDto();

        Mockito.when(pacienteMapper.paraDomain(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());
        Mockito.when(psicologoMapper.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());

        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        VinculoValidator.validaVinculoMapperEntry(domainTest, dtoTest);
    }
}