package com.liratech.helppsico.mapper;

import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.entrypoint.mapper.EnderecoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class EnderecoMapperTest {

    private final EnderecoMapper enderecoMapper = Mappers.getMapper(EnderecoMapper.class);

    @Test
    @DisplayName("Caso de sucesso na tranformação de DTO para Domain")
    void transformacaoEnderecoDtoParaDomain() {
        EnderecoDto enderecoDto = EnderecoBuilder.criarEnderecoDto();
        Endereco endereco = enderecoMapper.paraDomain(enderecoDto);

        Assertions.assertNotNull(endereco);
        Assertions.assertEquals(enderecoDto.getId(), endereco.getId());
        Assertions.assertEquals(enderecoDto.getRua(), endereco.getRua());
        Assertions.assertEquals(enderecoDto.getNumero(), endereco.getNumero());
        Assertions.assertEquals(enderecoDto.getCep(), endereco.getCep());
        Assertions.assertEquals(enderecoDto.getCidade(), endereco.getCidade());
        Assertions.assertEquals(enderecoDto.getEstado(), endereco.getEstado());
    }

    @Test
    @DisplayName("Caso de sucesso na tranformação de Domain para DTO")
    void transformacaoEnderecoDomainParaDto() {
        Endereco endereco = EnderecoBuilder.criarEndereco();
        EnderecoDto enderecoDto = enderecoMapper.paraDto(endereco);

        Assertions.assertNotNull(enderecoDto);
        Assertions.assertEquals(endereco.getId(), enderecoDto.getId());
        Assertions.assertEquals(endereco.getRua(), enderecoDto.getRua());
        Assertions.assertEquals(endereco.getNumero(), enderecoDto.getNumero());
        Assertions.assertEquals(endereco.getCep(), enderecoDto.getCep());
        Assertions.assertEquals(endereco.getCidade(), enderecoDto.getCidade());
        Assertions.assertEquals(endereco.getEstado(), enderecoDto.getEstado());
    }
}