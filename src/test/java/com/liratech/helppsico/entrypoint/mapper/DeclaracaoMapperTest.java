package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.documento.Declaracao;
import com.liratech.helppsico.entrypoint.dto.documento.DeclaracaoDto;
import com.liratech.helppsico.validators.DocumentoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeclaracaoMapperTest {

    @Mock
    private PacienteMapper pacienteMapper;

    @Mock
    private PsicologoMapper psicologoMapper;

    @InjectMocks
    private DeclaracaoMapper mapper;
    private Declaracao domainTest;
    private DeclaracaoDto dtoTest;

    @Test
    void testeDeclaracaoDomainParaDto(){
        domainTest = DocumentoBuilder.criarDeclaracao();

        Mockito.when(psicologoMapper.paraDto(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoDto());
        Mockito.when(pacienteMapper.paraDto(Mockito.any())).thenReturn(PacienteBuilder.criarPacienteDto());

        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaDeclaracaoMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeDeclaracaoDtoParaDomain(){
        dtoTest = DocumentoBuilder.criarDeclaracaoDto();

        Mockito.when(psicologoMapper.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());
        Mockito.when(pacienteMapper.paraDomain(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());

        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        DocumentoValidator.validaDocumentoEntry(domainTest, dtoTest);
        DocumentoValidator.validaDeclaracaoMapperEntry(domainTest, dtoTest);
    }
}
