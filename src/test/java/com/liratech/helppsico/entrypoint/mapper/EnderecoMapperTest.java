package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.validators.EnderecoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class EnderecoMapperTest {

    private final EnderecoMapper enderecoMapper = Mappers.getMapper(EnderecoMapper.class);

    @Test
    @DisplayName("Caso de sucesso na tranformação de DTO para Domain")
    void testeTransformacaoEnderecoDtoParaDomain() {
        EnderecoDto enderecoDto = EnderecoBuilder.criarEnderecoDto();
        Endereco endereco = enderecoMapper.paraDomain(enderecoDto);

        Assertions.assertNotNull(endereco);
        EnderecoValidator.validaEnderecoDtoParaDomain(enderecoDto, endereco);
    }

    @Test
    @DisplayName("Caso de sucesso na tranformação de Domain para DTO")
    void testeTransformacaoEnderecoDomainParaDto() {
        Endereco endereco = EnderecoBuilder.criarEndereco();
        EnderecoDto enderecoDto = enderecoMapper.paraDto(endereco);

        Assertions.assertNotNull(enderecoDto);
        EnderecoValidator.validaEnderecoDomainParaDto(endereco, enderecoDto);
    }
}