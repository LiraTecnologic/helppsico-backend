package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.validators.EnderecoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnderecoMapperTest {

    private EnderecoMapperImpl mapper;
    private Endereco domainTest;
    private EnderecoDto dtoTest;

    @BeforeEach
    void inicializar(){
        mapper = new EnderecoMapperImpl();
    }

    @Test
    void testeEnderecoDtoParaDomain() {
        dtoTest = EnderecoBuilder.criarEnderecoDto();
        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        EnderecoValidator.validaEnderecoMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testeEnderecoDomainParaDto() {
        domainTest = EnderecoBuilder.criarEndereco();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        EnderecoValidator.validaEnderecoMapperEntry(domainTest, dtoTest);
    }
}