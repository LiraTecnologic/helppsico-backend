package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.validators.EnderecoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

@AllArgsConstructor
class EnderecoMapperTest {

    private EnderecoMapper mapper;
    private Endereco domainTest;
    private EnderecoDto dtoTest;

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