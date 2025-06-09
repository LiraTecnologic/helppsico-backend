package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.infrastructure.repositories.entities.EnderecoEntity;
import com.liratech.helppsico.validators.EnderecoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnderecoMapperInfraTest {

    private EnderecoMapperInfraImpl mapper;

    private Endereco domainTest;
    private EnderecoEntity entityTest;

    @BeforeEach
    void inicializar(){
        mapper = new EnderecoMapperInfraImpl();
    }

    @Test
    void testeEnderecoEntityParaDomain() {
        entityTest = EnderecoBuilder.criarEnderecoEntity();
        domainTest = mapper.paraDomain(entityTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        EnderecoValidator.validaEnderecoMapperInfra(domainTest, entityTest);
    }

    @Test
    void testeEnderecoDomainParaEntity() {
        domainTest = EnderecoBuilder.criarEndereco();
        entityTest = mapper.paraEntity(domainTest);

        Assertions.assertEquals(domainTest.getId(), entityTest.getId());
        EnderecoValidator.validaEnderecoMapperInfra(domainTest, entityTest);
    }
}