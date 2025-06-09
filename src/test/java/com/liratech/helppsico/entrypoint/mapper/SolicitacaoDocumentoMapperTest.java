package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.builders.SolicitacaoDocumentoBuilder;
import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import com.liratech.helppsico.entrypoint.dto.documento.SolicitacaoDocumentoDto;
import com.liratech.helppsico.validators.SolicitacaoDocumentoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SolicitacaoDocumentoMapperTest {

    @Mock
    private PacienteMapper pacienteMapper;
    @Mock
    private PsicologoMapper psicologoMapper;

    @InjectMocks
    private SolicitacaoDocumentoMapperImpl mapper;
    private SolicitacaoDocumento domainTest;
    private SolicitacaoDocumentoDto dtoTest;

    @Test
    void testeSolicitacaoDocumentoDomainParaDto(){
        domainTest = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumento();

        Mockito.when(psicologoMapper.paraDto(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoDto());
        Mockito.when(pacienteMapper.paraDto(Mockito.any())).thenReturn(PacienteBuilder.criarPacienteDto());

        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        SolicitacaoDocumentoValidator.validaSolicitacaoDocumentoMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeSolicitacaoDocumentoDtoParaDomain(){
        dtoTest = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumentoDto();

        Mockito.when(psicologoMapper.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());
        Mockito.when(pacienteMapper.paraDomain(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());

        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        SolicitacaoDocumentoValidator.validaSolicitacaoDocumentoMapperEntry(domainTest, dtoTest);
    }
}
