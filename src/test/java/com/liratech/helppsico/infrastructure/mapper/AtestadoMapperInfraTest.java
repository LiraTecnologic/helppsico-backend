package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.documento.Atestado;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.AtestadoEntity;
import com.liratech.helppsico.validators.DocumentoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AtestadoMapperInfraTest {

    @Mock
    private PacienteMapperInfra pacienteMapper;

    @Mock
    private PsicologoMapperInfra psicologoMapper;

    @Mock
    private EnderecoMapperInfra enderecoMapper;

    @InjectMocks
    private AtestadoMapperInfra mapper;

    private Atestado domainTest;
    private AtestadoEntity entityTest;

    @Test
    void testeAtestadoEntityParaDomain(){
        entityTest = DocumentoBuilder.criarAtestadoEntity();

        Mockito.when(pacienteMapper.paraDomain(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());
        Mockito.when(psicologoMapper.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());
        Mockito.when(enderecoMapper.paraDomain(Mockito.any())).thenReturn(EnderecoBuilder.criarEndereco());

        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaAtestadoMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeAtestadoDomainParaEntity(){
        domainTest = DocumentoBuilder.criarAtestado();

        Mockito.when(pacienteMapper.paraEntity(Mockito.any())).thenReturn(PacienteBuilder.criarPacienteEntity());
        Mockito.when(psicologoMapper.paraEntity(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoEntity());
        Mockito.when(enderecoMapper.paraEntity(Mockito.any())).thenReturn(EnderecoBuilder.criarEnderecoEntity());

        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaAtestadoMapperInfra(domainTest, entityTest);
    }
}
