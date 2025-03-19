package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.PsicologoExistenteException;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Psicologo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.liratech.helppsico.application.usecases.PsicologoUseCase.MENSAGEM_PSICOLOGO_JA_EXISTE;
import static com.liratech.helppsico.application.usecases.PsicologoUseCase.MENSAGEM_PSICOLOGO_NAO_ENCONTRADO;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PsicologoUseCaseTest {

    @Mock
    private PsicologoGateway gateway;

    @Mock
    private FotoUseCase fotoUseCase;

    @Captor
    ArgumentCaptor<Psicologo> captor;

    @InjectMocks
    private PsicologoUseCase useCase;

    @Test
    void testeCadastroDePsicologo() {
        Psicologo psicologoNovo = PsicologoBuilder.gerarPsicologo();

        Mockito.when(gateway.consultarPorCrp(psicologoNovo.getCrp())).thenReturn(Optional.empty());
        Mockito.when(gateway.salvar(psicologoNovo)).thenReturn(psicologoNovo);

        Mockito.when(fotoUseCase.salvarImagem(psicologoNovo.getFoto())).thenReturn(psicologoNovo.getFotoUrl());

        Psicologo psicologoCadastrado = useCase.cadastrar(psicologoNovo);

        PsicologoValidator.validaPsicologo(psicologoCadastrado);
    }

    @Test
    void testeExceptionPsicologoJaCadastrado() {
        Psicologo psicologoNovo = PsicologoBuilder.gerarPsicologo();

        Mockito.when(gateway.consultarPorCrp(psicologoNovo.getCrp()))
                .thenReturn(Optional.of(PsicologoBuilder.gerarPsicologo()));

        PsicologoExistenteException exception = Assertions
                .assertThrows(PsicologoExistenteException.class, () -> useCase.cadastrar(psicologoNovo));

        Assertions.assertEquals(MENSAGEM_PSICOLOGO_JA_EXISTE, exception.getMessage());
        Mockito.verify(gateway, Mockito.times(1)).consultarPorCrp(psicologoNovo.getCrp());
    }

    @Test
    void consultarPorId() {

    }

    @Test
    void consultarPorNome() {
    }

    @Test
    void consultarMelhoresAvaliados() {
    }

    @Test
    void consultarPorCrp() {
    }

    @Test
    void listar() {
    }

    @Test
    void alterar() {
    }
}