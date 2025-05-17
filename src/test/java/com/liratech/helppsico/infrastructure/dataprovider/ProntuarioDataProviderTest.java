package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.ProntuarioBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.ProntuarioMapper;
import com.liratech.helppsico.infrastructure.repositories.ProntuarioRepository;
import com.liratech.helppsico.validators.ProntuarioValidator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class ProntuarioDataProviderTest {

    @Mock
    private ProntuarioRepository repository;

    @InjectMocks
    private ProntuarioDataProvider dataProvider;

    private final ProntuarioMapper mapper;

    @Test
    void testaSalvarProntuario() {
        Prontuario prontuarioTeste = ProntuarioBuilder.criarProntuario();
        Mockito.when(repository.save(Mockito.any())).thenReturn(mapper.paraEntity(prontuarioTeste));

        prontuarioTeste.setId(null);

        Prontuario resultado = dataProvider.salvar(prontuarioTeste);

        Assertions.assertNotNull(resultado.getId());
        ProntuarioValidator.validaProntuarioDoamain(resultado, prontuarioTeste);
    }

    @Test
    void testeExceptionSalvarProntuario() {
        Prontuario prontuario = ProntuarioBuilder.criarProntuario();
        Mockito.when(repository.save(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.salvar(prontuario));

        Assertions.assertEquals(dataProvider.MENSAGEM_ERRO_SALVAR, exception.getMessage());
    }

    @Test
    void testeConsultaProntuarioPeloId() {
        Prontuario prontuarioTeste = ProntuarioBuilder.criarProntuario();
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(mapper.paraEntity(prontuarioTeste)));

        Optional<Prontuario> resultado = dataProvider.consultarPorId(prontuarioTeste.getId());

        resultado.ifPresent(prontuario -> {
            ProntuarioValidator.validaProntuarioDoamain(prontuario, prontuarioTeste);
        });
    }

    @Test
    void testeExceptionConsultaProntuarioPeloId() {
        Mockito.when(repository.findById(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.consultarPorId(ProntuarioBuilder.criarProntuario().getId()));

        Assertions.assertEquals(dataProvider.MENSAGEM_ERRO_CONSULTAR_POR_ID, exception.getMessage());
    }

    @Test
    void testeListagemProntuarioPorPaciente() {
        Page<Prontuario> prontuarioTeste = ProntuarioBuilder.criarPageProntuarioEntity().map(mapper::paraDomain);
        Mockito.when(repository.findByPaciente(Mockito.any(), Mockito.any())).thenReturn(prontuarioTeste.map(mapper::paraEntity));

        List<Prontuario> resultado = dataProvider
                .listarPorPaciente(PacienteBuilder.criarPaciente(), PageRequest.of(0, 10)).getContent();

        IntStream.range(0, resultado.size())
                .forEach(i -> ProntuarioValidator.validaProntuarioDoamain(
                        prontuarioTeste.getContent().get(i),
                        resultado.get(i)
                ));
    }

    @Test
    void testeExceptionListagemProntuarioPorPaciente() {
        Paciente pacienteTeste = PacienteBuilder.criarPaciente();

        Mockito.when(repository.findByPaciente(Mockito.any(), Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.listarPorPaciente(pacienteTeste, PageRequest.of(0, 10)));

        Assertions.assertEquals(dataProvider.MENSAGEM_ERRO_LISTAR_PACIENTE, exception.getMessage());
    }

    @Test
    void testaListagemPorPsicologo() {
        Page<Prontuario> prontuarioTeste = ProntuarioBuilder.criarPageProntuarioEntity().map(mapper::paraDomain);
        Mockito.when(repository.findByPsicologo(Mockito.any(), Mockito.any())).thenReturn(prontuarioTeste.map(mapper::paraEntity));

        List<Prontuario> resultado = dataProvider
                .listarPorPsicologo(PsicologoBuilder.criarPsicologo(), PageRequest.of(0, 10)).getContent();

        IntStream.range(0, resultado.size())
                .forEach(i -> ProntuarioValidator.validaProntuarioDoamain(
                        prontuarioTeste.getContent().get(i),
                        resultado.get(i)
                ));
    }

    @Test
    void testeExceptionListagemPorPsicologo() {
        Psicologo psicologo = PsicologoBuilder.criarPsicologo();

        Mockito.when(repository.findByPaciente(Mockito.any(), Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.listarPorPsicologo(psicologo, PageRequest.of(0, 10)));

        Assertions.assertEquals(dataProvider.MENSAGEM_ERRO_LISTAR_PSICOLOGO, exception.getMessage());
    }

    @Test
    void testeDeletarProntuario() {
        UUID idTeste = ProntuarioBuilder.criarProntuario().getId();

        Mockito.doNothing().when(repository).deleteById(Mockito.any());

        dataProvider.deletar(idTeste);

        Mockito.verify(repository, Mockito.times(1)).deleteById(idTeste);
    }

    @Test
    void testeExceptionDeletarProntuario() {
        UUID idTeste = ProntuarioBuilder.criarProntuario().getId();

        Mockito.doThrow(DataProviderException.class).when(repository).deleteById(Mockito.any());

        DataProviderException exception = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.deletar(idTeste));

        Assertions.assertEquals(dataProvider.MENSAGEM_ERRO_DELETAR, exception.getMessage());
    }
}