package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.ConsultaBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.ProntuarioBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.entrypoint.dto.psicologo.ProntuarioDto;
import com.liratech.helppsico.validators.ProntuarioValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProntuarioMapperTest {

    @Mock
    private PsicologoMapper psicologoMapper;

    @Mock
    private PacienteMapper pacienteMapper;

    @Mock
    private ConsultaMapper consultaMapper;

    @InjectMocks
    private ProntuarioMapperImpl mapper;
    private Prontuario domainTest;
    private ProntuarioDto dtoTest;

    @Test
    void testeProntuarioDomainParaDto() {
        domainTest = ProntuarioBuilder.criarProntuario();

        Mockito.when(psicologoMapper.paraDto(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoDto());
        Mockito.when(pacienteMapper.paraDto(Mockito.any())).thenReturn(PacienteBuilder.criarPacienteDto());
        Mockito.when(consultaMapper.paraDto(Mockito.any())).thenReturn(ConsultaBuilder.criarConsultaDto());

        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        ProntuarioValidator.validaProntuarioMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeProntuarioDtoParaDomain() {
        dtoTest = ProntuarioBuilder.criarProntuarioDto();

        Mockito.when(psicologoMapper.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());
        Mockito.when(pacienteMapper.paraDomain(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());
        Mockito.when(consultaMapper.paraDomain(Mockito.any())).thenReturn(ConsultaBuilder.criarConsulta());

        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        ProntuarioValidator.validaProntuarioMapperEntry(domainTest, dtoTest);
    }
}