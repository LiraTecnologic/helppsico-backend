package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.HorarioBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Horario;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioDto;
import com.liratech.helppsico.validators.HorarioValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HorarioMapperTest {

    @Mock
    private PsicologoMapper psicologoMapper;

    @InjectMocks
    private HorarioMapperImpl mapper;
    private Horario domainTest;
    private HorarioDto dtoTest;

    @Test
    void testeHorarioDomainParaDto(){
        domainTest = HorarioBuilder.criarHorario();

        Mockito.when(psicologoMapper.paraDto(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoDto());

        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        HorarioValidator.validaHorarioMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeHorarioDtoParaDomain(){
        dtoTest = HorarioBuilder.criarHorarioDto();

        Mockito.when(psicologoMapper.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());

        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        HorarioValidator.validaHorarioMapperEntry(domainTest, dtoTest);
    }
}
