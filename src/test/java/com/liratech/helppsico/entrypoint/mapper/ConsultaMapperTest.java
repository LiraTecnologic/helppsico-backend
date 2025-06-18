package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.*;
import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.entrypoint.dto.consulta.ConsultaDto;
import com.liratech.helppsico.validators.ConsultaValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConsultaMapperTest {

    @Mock
    private PsicologoMapper psicologoMapper;

    @Mock
    private PacienteMapper pacienteMapper;

    @Mock
    private EnderecoMapper enderecoMapper;

    @Mock
    private HorarioMapper horarioMapper;

    @InjectMocks
    private ConsultaMapperImpl mapper;

    private ConsultaDto dtoTest;
    private Consulta domainTest;

    @Test
    void testeConsultaDtoParaDomain() {
        dtoTest = ConsultaBuilder.criarConsultaDto();

        Mockito.when(psicologoMapper.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());
        Mockito.when(pacienteMapper.paraDomain(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());
        Mockito.when(enderecoMapper.paraDomain(Mockito.any())).thenReturn(EnderecoBuilder.criarEndereco());
        Mockito.when(horarioMapper.paraDomain(Mockito.any())).thenReturn(HorarioBuilder.criarHorario());

        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        ConsultaValidator.validaConsultaMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeConsultaDomainParaDto() {
        domainTest = ConsultaBuilder.criarConsulta();

        Mockito.when(psicologoMapper.paraDto(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoDto());
        Mockito.when(pacienteMapper.paraDto(Mockito.any())).thenReturn(PacienteBuilder.criarPacienteDto());
        Mockito.when(enderecoMapper.paraDto(Mockito.any())).thenReturn(EnderecoBuilder.criarEnderecoDto());
        Mockito.when(horarioMapper.paraDto(Mockito.any())).thenReturn(HorarioBuilder.criarHorarioDto());

        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        ConsultaValidator.validaConsultaMapperEntry(domainTest, dtoTest);
    }
}