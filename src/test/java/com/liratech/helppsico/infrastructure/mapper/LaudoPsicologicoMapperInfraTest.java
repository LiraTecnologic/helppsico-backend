package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.documento.LaudoPsicologico;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.LaudoPsicologicoEntity;
import com.liratech.helppsico.validators.DocumentoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LaudoPsicologicoMapperInfraTest {

    @Mock
    private PacienteMapperInfra pacienteMapper;

    @Mock
    private PsicologoMapperInfra psicologoMapper;

    @InjectMocks
    private LaudoPsicologicoMapperInfra mapper;

    private LaudoPsicologico domainTest;
    private LaudoPsicologicoEntity entityTest;

    @Test
    void testeLaudoPsicologicoDomainParaEntity(){
        domainTest = DocumentoBuilder.criarLaudoPsicologico();

        Mockito.when(pacienteMapper.paraEntity(Mockito.any())).thenReturn(PacienteBuilder.criarPacienteEntity());
        Mockito.when(psicologoMapper.paraEntity(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoEntity());

        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaLaudoPsicologicoMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeLaudoPsicologicoEntityParaDomain(){
        entityTest = DocumentoBuilder.criarLaudoPsicologicoEntity();

        Mockito.when(pacienteMapper.paraDomain(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());
        Mockito.when(psicologoMapper.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());

        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaLaudoPsicologicoMapperInfra(domainTest, entityTest);
    }
}
