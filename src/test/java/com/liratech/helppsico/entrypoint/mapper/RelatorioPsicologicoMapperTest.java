package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.documento.RelatorioPsicologico;
import com.liratech.helppsico.entrypoint.dto.documento.RelatorioPsicologicoDto;
import com.liratech.helppsico.validators.DocumentoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RelatorioPsicologicoMapperTest {

    @Mock
    private PacienteMapper pacienteMapper;

    @Mock
    private PsicologoMapper psicologoMapper;

    @InjectMocks
    private RelatorioPsicologicoMapper mapper;
    private RelatorioPsicologico domainTest;
    private RelatorioPsicologicoDto dtoTest;

    @Test
    void testeRelatorioPsicologicoDomainParaDto(){
        domainTest = DocumentoBuilder.criarRelatorioPsicologico();

        Mockito.when(pacienteMapper.paraDto(Mockito.any())).thenReturn(PacienteBuilder.criarPacienteDto());
        Mockito.when(psicologoMapper.paraDto(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoDto());

        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaRelatorioPsicologicoMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeRelatorioPsicologicoDtoParaDomain(){
        dtoTest = DocumentoBuilder.criarRelatorioPsicologicoDto();

        Mockito.when(pacienteMapper.paraDomain(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());
        Mockito.when(psicologoMapper.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());

        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaRelatorioPsicologicoMapperEntry(domainTest, dtoTest);
    }
}
