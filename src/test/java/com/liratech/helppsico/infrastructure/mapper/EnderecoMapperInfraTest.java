package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.entrypoint.mapper.EnderecoMapper;
import com.liratech.helppsico.infrastructure.repositories.entities.EnderecoEntity;
import com.liratech.helppsico.validators.EnderecoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

@AllArgsConstructor
class EnderecoMapperInfraTest {

    private EnderecoMapperInfra mapper;
    private Endereco domainTest;
    private EnderecoEntity entityTest;

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