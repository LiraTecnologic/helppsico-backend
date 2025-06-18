package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.builders.SolicitacaoDocumentoBuilder;
import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.SolicitacaoDocumentoEntity;
import com.liratech.helppsico.validators.SolicitacaoDocumentoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SolicitacaoDocumentoMapperInfraTest {

    @Mock
    private PacienteMapperInfra pacienteMapperInfra;
    @Mock
    private PsicologoMapperInfra psicologoMapperInfra;

    @InjectMocks
    private SolicitacaoDocumentoMapperInfraImpl mapper;
    private SolicitacaoDocumento domainTest;
    private SolicitacaoDocumentoEntity entityTest;

    @Test
    void testeSolicitacaoDocumentoDomainParaEntity(){
        domainTest = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumento();

        Mockito.when(psicologoMapperInfra.paraEntity(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoEntity());
        Mockito.when(pacienteMapperInfra.paraEntity(Mockito.any())).thenReturn(PacienteBuilder.criarPacienteEntity());

        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        SolicitacaoDocumentoValidator.validaSolicitacaoDocumentoMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeSolicitacaoDocumentoEntityParaDomain(){
        entityTest = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumentoEntity();

        Mockito.when(psicologoMapperInfra.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());
        Mockito.when(pacienteMapperInfra.paraDomain(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());

        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        SolicitacaoDocumentoValidator.validaSolicitacaoDocumentoMapperInfra(domainTest, entityTest);
    }
}
