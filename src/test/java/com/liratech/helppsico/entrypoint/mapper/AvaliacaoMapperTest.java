package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.AvaliacaoBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.entrypoint.dto.psicologo.AvaliacaoDto;
import com.liratech.helppsico.validators.AvaliacaoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AvaliacaoMapperTest {

    @Mock
    private PsicologoMapper psicologoMapper;

    @Mock
    private PacienteMapper pacienteMapper;

    @InjectMocks
    private AvaliacaoMapperImpl mapper;

    private Avaliacao domainTest;
    private AvaliacaoDto dtoTest;

    @Test
    void testeAvaliacaoDomainParaDto() {
        domainTest = AvaliacaoBuilder.criarAvaliacao();

        Mockito.when(pacienteMapper.paraDto(Mockito.any())).thenReturn(PacienteBuilder.criarPacienteDto());
        Mockito.when(psicologoMapper.paraDto(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoDto());

        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        AvaliacaoValidator.validaAvaliacaoMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeAvaliacaoDtoParaDomain() {
        dtoTest = AvaliacaoBuilder.criarAvaliacaoDto();

        Mockito.when(pacienteMapper.paraDomain(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());
        Mockito.when(psicologoMapper.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());

        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        AvaliacaoValidator.validaAvaliacaoMapperEntry(domainTest, dtoTest);
    }
}
