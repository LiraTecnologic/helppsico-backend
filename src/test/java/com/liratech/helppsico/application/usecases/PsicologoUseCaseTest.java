package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.PsicologoNaoEncontradoException;
import com.liratech.helppsico.validator.PsicologoValidator;
import com.liratech.helppsico.application.exceptions.PsicologoExistenteException;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Psicologo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static com.liratech.helppsico.application.usecases.PsicologoUseCase.MENSAGEM_PSICOLOGO_JA_EXISTE;
import static com.liratech.helppsico.application.usecases.PsicologoUseCase.MENSAGEM_PSICOLOGO_NAO_ENCONTRADO;

import java.util.List;
import java.util.Optional;

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
    void testeConsultaPsicologoPeloId() {
        Psicologo psicologoBuilder = PsicologoBuilder.gerarPsicologo();

        Mockito.when(gateway.consultarPorId(psicologoBuilder.getId()))
                .thenReturn(Optional.of(psicologoBuilder));

        Psicologo psicologo = useCase.consultarPorId(psicologoBuilder.getId());

        PsicologoValidator.validaPsicologo(psicologo);
    }

    @Test
    void testePsicologoNaoEncontrado() {
        Psicologo psicologoBuilder = PsicologoBuilder.gerarPsicologo();

        Mockito.when(gateway.consultarPorId(psicologoBuilder.getId()))
                .thenReturn(Optional.empty());

        PsicologoNaoEncontradoException exception = Assertions
                .assertThrows(PsicologoNaoEncontradoException.class,
                        () -> useCase.consultarPorId(psicologoBuilder.getId()));

        Assertions.assertEquals(MENSAGEM_PSICOLOGO_NAO_ENCONTRADO, exception.getMessage());
        Mockito.verify(gateway, Mockito.times(1))
                .consultarPorId(psicologoBuilder.getId());
    }

    @Test
    void testeConsultaPsicologosPeloNome() {
        String nomeTeste = PsicologoBuilder.gerarPsicologo().getNome();
        List<Psicologo> psicologoList = PsicologoBuilder.gerarListaDePsicologos();

        Mockito.when(gateway.consultarPorNome(nomeTeste))
                .thenReturn(psicologoList);

        List<Psicologo> psicologos = useCase.consultarPorNome(nomeTeste);

        psicologos.forEach(PsicologoValidator::validaPsicologo);
    }

    @Test
    void testeConsultaMelhoresPsicologosAvaliados() {
        Page<Psicologo> psicologoPage = PsicologoBuilder.gerarPageDePsicologos();

        Mockito.when(gateway.consultarMelhoresAvaliados())
                .thenReturn(psicologoPage);

        Page<Psicologo> psicologos = useCase
                .consultarMelhoresAvaliados(PageRequest.of(0, 10));

        psicologos.forEach(PsicologoValidator::validaPsicologo);
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