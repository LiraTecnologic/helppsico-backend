package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.documento.Declaracao;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.DeclaracaoEntity;
import com.liratech.helppsico.validators.DocumentoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeclaracaoMapperInfraTest {

    @Mock
    private PacienteMapperInfra pacienteMapperInfra;

    @Mock
    private PsicologoMapperInfra psicologoMapperInfra;

    @InjectMocks
    private DeclaracaoMapperInfra mapper;

    private Declaracao domainTest;
    private DeclaracaoEntity entityTest;

    @Test
    void testeDeclaracaoDomainParaEntity(){
        domainTest = DocumentoBuilder.criarDeclaracao();

        Mockito.when(psicologoMapperInfra.paraEntity(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologoEntity());
        Mockito.when(pacienteMapperInfra.paraEntity(Mockito.any())).thenReturn(PacienteBuilder.criarPacienteEntity());

        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaDeclaracaoMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeDeclaracaoEntityParaDomain(){
        entityTest = DocumentoBuilder.criarDeclaracaoEntity();

        Mockito.when(psicologoMapperInfra.paraDomain(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());
        Mockito.when(pacienteMapperInfra.paraDomain(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());

        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        DocumentoValidator.validaDocumentoInfra(domainTest, entityTest);
        DocumentoValidator.validaDeclaracaoMapperInfra(domainTest, entityTest);
    }
}
