package com.liratech.helppsico.infrastructure.mapper;

import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.infrastructure.repositories.entities.EnderecoEntity;
import com.liratech.helppsico.validators.EnderecoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class EnderecoMapperTest {

    private final EnderecoMapperInfra enderecoMapper = Mappers.getMapper(EnderecoMapperInfra.class);

    @Test
    @DisplayName("Caso de sucesso na transformação de Endereco Entity para Domain")
    void testeTransformacaoEnderecoEntityParaDomain() {
        EnderecoEntity enderecoEntity = EnderecoBuilder.criarEnderecoEntity();
        Endereco endereco = enderecoMapper.paraDomain(enderecoEntity);

        Assertions.assertNotNull(endereco);
        EnderecoValidator.validaEnderecoEntityParaDomain(enderecoEntity, endereco);
    }

    @Test
    @DisplayName("Caso de sucesso na transformação de Endereco Domain para Entity")
    void testeTrasnformacaoEnderecoDomainParaEntity() {
        Endereco endereco = EnderecoBuilder.criarEndereco();
        EnderecoEntity enderecoEntity = enderecoMapper.paraEntity(endereco);

        Assertions.assertNotNull(endereco);
        EnderecoValidator.validaEnderecoDomainParaEntity(endereco, enderecoEntity);
    }
}