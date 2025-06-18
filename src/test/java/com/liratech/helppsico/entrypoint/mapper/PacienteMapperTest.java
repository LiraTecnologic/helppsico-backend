package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.infrastructure.mapper.PacienteMapperInfraImpl;
import com.liratech.helppsico.validators.PacienteValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PacienteMapperTest {

    @Mock
    private EnderecoMapper enderecoMapper;

    @InjectMocks
    private PacienteMapperImpl mapper;
    private Paciente domainTest;
    private PacienteDto dtoTest;

    @Test
    void testePacienteDomainParaDto() {
        domainTest = PacienteBuilder.criarPaciente();

        Mockito.when(enderecoMapper.paraDto(Mockito.any())).thenReturn(EnderecoBuilder.criarEnderecoDto());

        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        PacienteValidator.validaPacienteMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testePacienteDtoParaDomain() {
        dtoTest = PacienteBuilder.criarPacienteDto();

        Mockito.when(enderecoMapper.paraDomain(Mockito.any())).thenReturn(EnderecoBuilder.criarEndereco());

        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        PacienteValidator.validaPacienteMapperEntry(domainTest, dtoTest);
    }
}