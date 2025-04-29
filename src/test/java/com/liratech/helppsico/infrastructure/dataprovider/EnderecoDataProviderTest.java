package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.EnderecoMapper;
import com.liratech.helppsico.infrastructure.repositories.entities.EnderecoEntity;
import com.liratech.helppsico.validators.EnderecoValidator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class EnderecoDataProviderTest {

    @Mock
    private final EnderecoRepository repository;

    @InjectMocks
    private final EnderecoDataProvider dataProvider;

    private final EnderecoMapper mapper;

    @Test
    void testeSalvarEndereco() {
        Endereco endereco = EnderecoBuilder.criarEndereco();
        endereco.setId(null);

        EnderecoEntity enderecoSalvo = mapper.paraEntity(endereco);
        UUID id = UUID.randomUUID();
        endereco.setId(id);

        Mockito.when(repository.save(Mockito.any())).thenReturn(enderecoSalvo);

        Endereco enderecoResultado = dataProvider.salvar(endereco);
        EnderecoValidator.validaEnderecoDomain(mapper.paraDomain(enderecoSalvo), enderecoResultado);
    }

    @Test
    void testeErroSalvarEndereco() {
        Mockito.when(repository.save(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.salvar(EnderecoBuilder.criarEndereco()));
        Assertions.assertEquals(EnderecoDataProvider.MENSAGEM_ERRO_SALVAR, exception.getMessage());
    }

    @Test
    void testeConsultarEnderecoPorId() {
        Endereco enderecoTeste = EnderecoBuilder.criarEndereco();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(mapper.paraEntity(enderecoTeste)));

        Optional<Endereco> enderecoResultado = dataProvider.consultarPorId(endereco.getId());

        enderecoResultado.ifPresent(endereco -> {
            EnderecoValidator.validaEnderecoDomain(enderecoTeste, endereco);
        });
    }

    @Test
    void testeErroConsultarEnderecoPorId() {
        Mockito.when(repository.findById(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.consultarPorId(EnderecoBuilder.criarEndereco().getId()));

        Assertions.assertEquals(EnderecoDataProvider.MENSAGEM_ERRO_CONSULTAR_POR_ID, exception.getMessage());
    }
}