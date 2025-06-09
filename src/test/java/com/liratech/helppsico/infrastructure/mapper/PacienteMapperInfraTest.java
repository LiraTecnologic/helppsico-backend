package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.validators.PacienteValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PacienteMapperInfraTest {

    @Mock
    private EnderecoMapperInfra enderecoMapperInfra;

    @InjectMocks
    private PacienteMapperInfraImpl mapper;

    private Paciente domainTest;
    private PacienteEntity entityTest;

    @Test
    void testePacienteDomainParaEntity() {
        domainTest = PacienteBuilder.criarPaciente();

        Mockito.when(enderecoMapperInfra.paraEntity(Mockito.any())).thenReturn(EnderecoBuilder.criarEnderecoEntity());

        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        PacienteValidator.validaPacienteMapperInfra(domainTest, entityTest);
    }

    @Test
    void testePacienteDtoParaDomain() {
        entityTest = PacienteBuilder.criarPacienteEntity();

        Mockito.when(enderecoMapperInfra.paraDomain(Mockito.any())).thenReturn(EnderecoBuilder.criarEndereco());

        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        PacienteValidator.validaPacienteMapperInfra(domainTest, entityTest);
    }
}