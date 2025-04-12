package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.endereco.EnderecoExistenteException;
import com.liratech.helppsico.application.exceptions.endereco.EnderecoNaoEncontradoException;
import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.validators.EnderecoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static com.liratech.helppsico.application.usecases.EnderecoUseCase.MENSAGEM_ENDERECO_JA_EXISTENTE;
import static com.liratech.helppsico.application.usecases.EnderecoUseCase.MENSAGEM_ENDERECO_NAO_ENCONTRADO;

class EnderecoUseCaseTest {

    @Mock
    private EnderecoGateway gateway;

    @InjectMocks
    private EnderecoUseCase useCase;

    @Test
    void testeCadastrarEndereco() {
        Endereco novoEndereco = EnderecoBuilder.criarEndereco();

        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(gateway.salvar(Mockito.any())).thenReturn(novoEndereco);

        Endereco enderecoCadastrado = useCase.cadastrar(novoEndereco);
        EnderecoValidator.validaEnderecoDomain(novoEndereco, enderecoCadastrado);
    }

    @Test
    void testeErroCadastrarEndereco() {
        Endereco novoEndereco = EnderecoBuilder.criarEndereco();

        Mockito.when(gateway.consultarPorId(Mockito.any()))
                .thenReturn(Optional.of(EnderecoBuilder.criarEndereco()));

        EnderecoExistenteException exception = Assertions
                .assertThrows(EnderecoExistenteException.class, () -> useCase.cadastrar(novoEndereco));

        Assertions.assertEquals(MENSAGEM_ENDERECO_JA_EXISTENTE, exception.getMessage());
        Mockito.verify(gateway, Mockito.times(1)).consultarPorId(novoEndereco.getId());
    }

    @Test
    void testeConsultarEnderecoPorId() {
        Endereco enderecoBuilder = EnderecoBuilder.criarEndereco();

        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(enderecoBuilder));
        Endereco endereco = useCase.consultarPorId(enderecoBuilder.getId());

        EnderecoValidator.validaEnderecoDomain(enderecoBuilder, endereco);
    }

    @Test
    void testeErroConsultarEnderecoPorId() {
        UUID id = UUID.randomUUID();

        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.empty());
        EnderecoNaoEncontradoException exception = Assertions.assertThrows(EnderecoNaoEncontradoException.class, () -> useCase.consultarPorId(id));
        Assertions.assertEquals(MENSAGEM_ENDERECO_NAO_ENCONTRADO, exception.getMessage());
        Mockito.verify(gateway, Mockito.times(1))
                .consultarPorId(id);
    }
}