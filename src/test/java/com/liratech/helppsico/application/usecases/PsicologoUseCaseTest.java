package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.PsicologoExistenteException;
import com.liratech.helppsico.application.exceptions.PsicologoNaoEncontradoException;
import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.TipoGenero;
import com.liratech.helppsico.validator.PsicologoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.liratech.helppsico.application.usecases.PsicologoUseCase.MENSAGEM_PSICOLOGO_JA_EXISTE;
import static com.liratech.helppsico.application.usecases.PsicologoUseCase.MENSAGEM_PSICOLOGO_NAO_ENCONTRADO;

@ExtendWith(MockitoExtension.class)
class PsicologoUseCaseTest {

    @Mock
    private PsicologoGateway gateway;

    @Mock
    private FotoUseCase fotoUseCase;

    @Mock
    private EderecoUseCase enderecoUseCase;

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
        Mockito.when(enderecoUseCase.cadastrar(psicologoNovo.getEnderecoAtendimento())).thenReturn(psicologoNovo.getEnderecoAtendimento());

        Psicologo psicologoCadastrado = useCase.cadastrar(psicologoNovo);

        PsicologoValidator.validaPsicologo(psicologoNovo, psicologoCadastrado);
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

        PsicologoValidator.validaPsicologo(psicologoBuilder, psicologo);
    }

    @Test
    void testePsicologoNaoEncontradoPeloId() {
        UUID id = PsicologoBuilder.gerarPsicologo().getId();

        Mockito.when(gateway.consultarPorId(id))
                .thenReturn(Optional.empty());

        PsicologoNaoEncontradoException exception = Assertions
                .assertThrows(PsicologoNaoEncontradoException.class,
                        () -> useCase.consultarPorId(id));

        Assertions.assertEquals(MENSAGEM_PSICOLOGO_NAO_ENCONTRADO, exception.getMessage());
        Mockito.verify(gateway, Mockito.times(1))
                .consultarPorId(id);
    }

    @Test
    void testeConsultaPsicologosPeloNome() {
        String nomeTeste = PsicologoBuilder.gerarPsicologo().getNome();
        List<Psicologo> psicologoList = PsicologoBuilder.gerarListaDePsicologos();

        Mockito.when(gateway.consultarPorNome(nomeTeste))
                .thenReturn(psicologoList);

        List<Psicologo> psicologos = useCase.consultarPorNome(nomeTeste);

        for (int i = 0; i < psicologoList.size(); i++) {
            PsicologoValidator.validaPsicologo(psicologoList.get(i), psicologos.get(i));
        }
    }

    @Test
    void testeConsultaMelhoresPsicologosAvaliados() {
        Page<Psicologo> psicologosPage = PsicologoBuilder.gerarPageDePsicologos();

        Mockito.when(gateway.consultarMelhoresAvaliados())
                .thenReturn(psicologosPage);

        Page<Psicologo> psicologos = useCase
                .consultarMelhoresAvaliados(PageRequest.of(0, 10));

        for (int i = 0; i < psicologosPage.getSize(); i++) {
            PsicologoValidator.validaPsicologo(psicologosPage.toList().get(i), psicologos.toList().get(i));
        }
    }

    @Test
    void testeConsultaPorCrp() {
        Psicologo psicologoBuilder = PsicologoBuilder.gerarPsicologo();

        Mockito.when(gateway.consultarPorCrp(psicologoBuilder.getCrp()))
                .thenReturn(Optional.of(psicologoBuilder));

        Psicologo psicologo = useCase.consultarPorCrp(psicologoBuilder.getCrp());

        PsicologoValidator.validaPsicologo(psicologoBuilder, psicologo);
    }

    @Test
    void testePsicologoNaoEncontradoPeloCrp() {
        String crp = PsicologoBuilder.gerarPsicologo().getCrp();

        Mockito.when(gateway.consultarPorCrp(crp))
                .thenReturn(Optional.empty());

        PsicologoNaoEncontradoException exception = Assertions
                .assertThrows(PsicologoNaoEncontradoException.class,
                        () -> useCase.consultarPorCrp(crp));

        Assertions.assertEquals(MENSAGEM_PSICOLOGO_NAO_ENCONTRADO, exception.getMessage());

        Mockito.verify(gateway, Mockito.times(1))
                .consultarPorCrp(crp);
    }

    @Test
    void testeListagemPsicologos() {
        Page<Psicologo> psicologoPage = PsicologoBuilder.gerarPageDePsicologos();

        Mockito.when(gateway.listar())
                .thenReturn(psicologoPage);

        Page<Psicologo> psicologos = useCase
                .listar(PageRequest.of(0, 10));

        for (int i = 0; i < psicologoPage.size(); i++) {
            PsicologoValidator.validaPsicologo(psicologoPage.toList().get(i), psicologos.toList().get(i));
        }
    }

    @Test
    void testeAlteracaoDePsicologo() {
        Psicologo psicologoBuilder = PsicologoBuilder.gerarPsicologo();

        Endereco enderecoNovo = Endereco.builder()
                .id(UUID.randomUUID())
                .rua("Rua teste2")
                .numero(124)
                .cep("78950145")
                .cidade("Cidade teste 2")
                .estado("Estado teste 2")
                .build();


        Psicologo psicologoNovo = Psicologo.builder()
                .nome("Psicologo teste 2")
                .crp("0100001")
                .cpf("12332114766")
                .email("emailteste2@gmail.com")
                .telefone("44987415622")
                .dataNascimento(LocalDate.now().plusDays(2))
                .senha("senhateste125!")
                .genero(TipoGenero.FEMININO)
                .enderecoAtendimento(enderecoNovo)
                .fotoUrl("urltestefoto2")
                .biografia("Biografia teste 2")
                .build();

        //Mockar cadastro de endereço

        Mockito.when(gateway.consultarPorId(psicologoBuilder.getId()))
                .thenReturn(Optional.of(psicologoBuilder));

        Mockito.when(gateway.salvar(ArgumentMatchers.any()))
                .thenReturn(captor.capture());


        useCase.alterar(psicologoNovo, psicologoBuilder.getId());

        Psicologo psicologoTeste = captor.getValue();

        PsicologoValidator.validaPsicologo(psicologoBuilder, psicologoTeste);
    }
}