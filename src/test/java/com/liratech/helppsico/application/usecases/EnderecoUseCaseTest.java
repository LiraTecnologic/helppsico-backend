package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.endereco.EnderecoNaoEncontradoException;
import com.liratech.helppsico.application.gateways.EnderecoGateway;
import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.validators.EnderecoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.liratech.helppsico.application.usecases.EnderecoUseCase.MENSAGEM_ENDERECO_NAO_ENCONTRADO;

@ExtendWith(MockitoExtension.class)
class EnderecoUseCaseTest {

    @Mock
    private EnderecoGateway gateway;

    @Captor
    ArgumentCaptor<Endereco> captor;

    @InjectMocks
    private EnderecoUseCase useCase;

    @Test
    void testeCadastrarEndereco() {
        Endereco novoEndereco = EnderecoBuilder.criarEndereco();

        Mockito.when(gateway.salvar(captor.capture())).thenReturn(novoEndereco);
        novoEndereco.setId(null);

        useCase.cadastrar(novoEndereco);
        Endereco enderecoCapturado = captor.getValue();

        EnderecoValidator.validaEnderecoDomain(novoEndereco, enderecoCapturado);
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
        EnderecoNaoEncontradoException exception = Assertions
                .assertThrows(EnderecoNaoEncontradoException.class, () -> useCase.consultarPorId(id));
        Assertions.assertEquals(MENSAGEM_ENDERECO_NAO_ENCONTRADO, exception.getMessage());
        Mockito.verify(gateway, Mockito.times(1))
                .consultarPorId(id);
    }
}