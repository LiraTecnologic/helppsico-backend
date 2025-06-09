package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.documento.RelatorioPsicologico;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.RelatorioPsicologicoEntity;
import com.liratech.helppsico.validators.DocumentoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RelatorioPsicologicoMapperInfraTest {

    @Mock
    private PacienteMapperInfra pacienteMapper;

    @Mock
    private PsicologoMapperInfra psicologoMapper;

    @InjectMocks
    private RelatorioPsicologicoMapperInfra mapper;
    private RelatorioPsicologico domainTest;
    private RelatorioPsicologicoEntity entityTest;

    @Test
    void testeRelatorioPsicologicoDomainParaEntity(){
        domainTest = DocumentoBuilder.criarRelatorioPsicologico();

        Mockito.when(pacienteMapper.paraEntity(Mockito.any())).thenReturn(PacienteBuilder.criarPacienteEntity());
        Mockito.when(psicologoMapper.paraEntity(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoEntity());

        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaRelatorioPsicologicoMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeRelatorioPsicologicoEntityParaDomain(){
        entityTest = DocumentoBuilder.criarRelatorioPsicologicoEntity();

        Mockito.when(pacienteMapper.paraDomain(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());
        Mockito.when(psicologoMapper.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());

        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaRelatorioPsicologicoMapperInfra(domainTest, entityTest);
    }
}
