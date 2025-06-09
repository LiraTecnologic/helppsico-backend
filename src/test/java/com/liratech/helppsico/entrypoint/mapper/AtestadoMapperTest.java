package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.documento.Atestado;
import com.liratech.helppsico.entrypoint.dto.documento.AtestadoDto;
import com.liratech.helppsico.validators.DocumentoValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AtestadoMapperTest {

    @Mock
    private PacienteMapper pacienteMapper;

    @Mock
    private PsicologoMapper psicologoMapper;

    @Mock
    private EnderecoMapper enderecoMapper;

    @InjectMocks
    private AtestadoMapper mapper;

    private Atestado domainTest;
    private AtestadoDto dtoTest;

    @Test
    void testeAtestadoDtoParaDomain(){
        domainTest = DocumentoBuilder.criarAtestado();

        Mockito.when(pacienteMapper.paraDto(Mockito.any())).thenReturn(PacienteBuilder.criarPacienteDto());
        Mockito.when(psicologoMapper.paraDto(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoDto());
        Mockito.when(enderecoMapper.paraDto(Mockito.any())).thenReturn(EnderecoBuilder.criarEnderecoDto());

        dtoTest = mapper.paraDto(domainTest);
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaAtestadoMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeAtestadoDomainParaDto(){
        dtoTest = DocumentoBuilder.criarAtestadoDto();

        Mockito.when(pacienteMapper.paraDomain(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());
        Mockito.when(psicologoMapper.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());
        Mockito.when(enderecoMapper.paraDomain(Mockito.any())).thenReturn(EnderecoBuilder.criarEndereco());

        domainTest = mapper.paraDomain(dtoTest);

        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaAtestadoMapperEntry(domainTest, dtoTest);
    }
}
