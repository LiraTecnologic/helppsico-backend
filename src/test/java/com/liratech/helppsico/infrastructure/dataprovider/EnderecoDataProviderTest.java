package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.EnderecoMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.EnderecoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.EnderecoEntity;
import com.liratech.helppsico.validators.EnderecoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class EnderecoDataProviderTest {

    @Mock
    private EnderecoMapperInfra mapper;

    @Mock
    private EnderecoRepository repository;

    @InjectMocks
    private EnderecoDataProvider dataProvider;

    private Endereco enderecoDomainTeste;
    private EnderecoEntity enderecoEntityTeste;

    @BeforeEach
    void inicializar() {
        enderecoDomainTeste = EnderecoBuilder.criarEndereco();
        enderecoEntityTeste = EnderecoBuilder.criarEnderecoEntity();
    }

    @Test
    void testeSalvarEndereco() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(enderecoEntityTeste);
        Mockito.when(mapper.paraEntity(Mockito.any())).thenReturn(enderecoEntityTeste);
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(enderecoDomainTeste);

        Endereco enderecoResultado = dataProvider.salvar(enderecoDomainTeste);
        EnderecoValidator.validaEnderecoDomain(enderecoDomainTeste, enderecoResultado);
    }

    @Test
    void testeExceptionSalvarEndereco() {
        Mockito.when(repository.save(Mockito.any())).thenThrow(RuntimeException.class);
        Mockito.when(mapper.paraEntity(Mockito.any())).thenReturn(enderecoEntityTeste);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.salvar(EnderecoBuilder.criarEndereco()));
        Assertions.assertEquals(EnderecoDataProvider.MENSAGEM_ERRO_SALVAR, exception.getMessage());
    }

    @Test
    void testeConsultarEnderecoPorId() {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(enderecoEntityTeste));

        Optional<Endereco> enderecoResultado = dataProvider.consultarPorId(enderecoDomainTeste.getId());

        enderecoResultado.ifPresent(endereco -> {
            EnderecoValidator.validaEnderecoDomain(enderecoDomainTeste, endereco);
        });
    }

    @Test
    void testeExceptionConsultarEnderecoPorId() {
        Mockito.when(repository.findById(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.consultarPorId(EnderecoBuilder.criarEndereco().getId()));

        Assertions.assertEquals(EnderecoDataProvider.MENSAGEM_ERRO_CONSULTAR_POR_ID, exception.getMessage());
    }
}