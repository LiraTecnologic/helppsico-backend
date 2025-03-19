package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Psicologo;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
    void cadastrar() {
        Psicologo psicologoNovo = PsicologoBuilder.gerarPsicologo();

        Mockito.when(gateway.consultarPorCrp(psicologoNovo.getCrp())).thenReturn(Optional.empty());
        Mockito.when(gateway.salvar(psicologoNovo)).thenReturn(psicologoNovo);

        Mockito.when(fotoUseCase.salvarImagem(psicologoNovo.getFoto())).thenReturn("urltestefoto");

        Psicologo psicologoCadastrado = useCase.cadastrar(psicologoNovo);

        PsicologoValidator.validaPsicologo(psicologoCadastrado);
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