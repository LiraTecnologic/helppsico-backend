package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.PsicologoMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.PsicologoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import com.liratech.helppsico.validators.PsicologoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
public class PsicologoDataProviderTest {

    @Mock
    private PsicologoRepository repository;

    @InjectMocks
    private PsicologoDataProvider dataProvider;

    private PsicologoMapperInfra mapper;

    @Test
    void testeSalvarPsicologo(){
        Psicologo psicologoTeste = PsicologoBuilder.criarPsicologo();
        psicologoTeste.setId(null);

        PsicologoEntity psicologoEntitySalvo = mapper.paraEntity(psicologoTeste);
        UUID idGerado = UUID.randomUUID();
        psicologoEntitySalvo.setId(idGerado);

        Mockito.when(repository.save(Mockito.any())).thenReturn(psicologoEntitySalvo);

        Psicologo psicologoResultado = dataProvider.salvar(psicologoTeste);
        PsicologoValidator.validaPsicologoDomain(psicologoTeste, psicologoResultado);
        Assertions.assertEquals(idGerado, psicologoResultado.getId());
    }

    @Test
    void testeExceptionSalvarPsicologo(){
        Mockito.when(repository.save(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.salvar(PsicologoBuilder.criarPsicologo()));

        Assertions.assertEquals(PsicologoDataProvider.MENSAGEM_ERRO_SALVAR, exception.getMessage());
    }

    @Test
    void testeConsultarPorId(){
        Psicologo psicologoTeste = PsicologoBuilder.criarPsicologo();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(mapper.paraEntity(psicologoTeste)));

        Optional<Psicologo> psicologoResultado = dataProvider.consultarPorId(psicologoTeste.getId());

        psicologoResultado.ifPresent(psicologo -> {
            PsicologoValidator.validaPsicologoDomain(psicologoTeste, psicologo);
            Assertions.assertEquals(psicologoTeste.getId(), psicologo.getId());
        });
    }

    @Test
    void testeExceptionConsultarPorId(){
        Mockito.when(repository.findById(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.consultarPorId(PsicologoBuilder.criarPsicologo().getId()));

        Assertions.assertEquals(PsicologoDataProvider.MENSAGEM_ERRO_CONSULTAR_POR_ID, exception.getMessage());
    }

    @Test
    void testeConsultarPorNome(){
        List<Psicologo> psicologoListTeste = PsicologoBuilder.gerarListaDePsicologos();
        String nome = psicologoListTeste.getFirst().getNome();

        Mockito.when(repository.findByNome(Mockito.anyString())).thenReturn(mapper.paraEntities(psicologoListTeste));

        List<Psicologo> psicologosListResultado = dataProvider.consultarPorNome(nome);

        Assertions.assertEquals(psicologoListTeste.size(), psicologosListResultado.size());
        IntStream.range(0, psicologoListTeste.size())
                .forEach(i -> PsicologoValidator.validaPsicologoDomain(
                        psicologoListTeste.get(i),
                        psicologosListResultado.get(i)
                ));
    }

    @Test
    void testeExceptionConsultarPorNome(){
        Mockito.when(repository.findByNome(Mockito.anyString())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.consultarPorNome(PsicologoBuilder.criarPsicologo().getNome()));

        Assertions.assertEquals(PsicologoDataProvider.MENSAGEM_ERRO_CONSULTAR_POR_NOME, exception.getMessage());
    }

    @Test
    void testeConsultarMelhoresAvaliados(){
        Page<Psicologo> psicologosPageTeste = PsicologoBuilder.gerarPageDePsicologos();
        Pageable pageable = PageRequest.of(0,10);

        Mockito.when(repository.consultarMelhoresAvaliados(Mockito.any())).thenReturn(mapper.paraEntitiesPage(psicologosPageTeste));

        Page<Psicologo> psicologosPageResultado = dataProvider.consultarMelhoresAvaliados(pageable);

        Assertions.assertEquals(psicologosPageTeste.getTotalElements(), psicologosPageResultado.getTotalElements());
        Assertions.assertEquals(psicologosPageTeste.getSize(), psicologosPageResultado.getSize());
        IntStream.range(0, psicologosPageTeste.getContent().size())
                .forEach(i -> PsicologoValidator.validaPsicologoDomain(
                        psicologosPageTeste.getContent().get(i),
                        psicologosPageResultado.getContent().get(i)
                ));
    }

    @Test
    void testeExceptionConsultarMelhoresAvaliados(){
        Mockito.when(repository.consultarMelhoresAvaliados(Mockito.any())).thenThrow(RuntimeException.class);
        Pageable pageable = PageRequest.of(0,10);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.consultarMelhoresAvaliados(pageable));

        Assertions.assertEquals(PsicologoDataProvider.MENSAGEM_ERRO_CONSULTAR_MELHORES_AVALIADOS, exception.getMessage());
    }

    @Test
    void testeConsultarPorCrp(){
        Psicologo psicologoTeste = PsicologoBuilder.criarPsicologo();

        Mockito.when(repository.findByCrp(Mockito.any())).thenReturn(Optional.of(mapper.paraEntity(psicologoTeste)));

        Optional<Psicologo> psicologoResultado = dataProvider.consultarPorCrp(psicologoTeste.getCrp());

        psicologoResultado.ifPresent(psicologo -> {
            PsicologoValidator.validaPsicologoDomain(psicologoTeste, psicologo);
            Assertions.assertEquals(psicologoTeste.getId(), psicologo.getId());
        });
    }

    @Test
    void testeExceptionConsultarPorCrp(){
        Mockito.when(repository.findByCrp(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.consultarPorCrp(PsicologoBuilder.criarPsicologo().getCrp()));

        Assertions.assertEquals(PsicologoDataProvider.MENSAGEM_ERRO_CONSULTAR_POR_CRP, exception.getMessage());
    }

    @Test
    void testeListarPsicologos(){
        Page<Psicologo> psicologosPageTeste = PsicologoBuilder.gerarPageDePsicologos();
        Pageable pageable = PageRequest.of(0,10);

        Mockito.when(repository.findAll()).thenReturn(mapper.paraEntitiesPage(psicologosPageTeste));

        Page<Psicologo> psicologosPageResultado = dataProvider.listar(pageable);

        Assertions.assertEquals(psicologosPageTeste.getTotalElements(), psicologosPageResultado.getTotalElements());
        Assertions.assertEquals(psicologosPageTeste.getSize(), psicologosPageResultado.getSize());
        IntStream.range(0, psicologosPageTeste.getContent().size())
                .forEach(i -> PsicologoValidator.validaPsicologoDomain(
                        psicologosPageTeste.getContent().get(i),
                        psicologosPageResultado.getContent().get(i)
                ));
    }

    @Test
    void testeExceptionListarPsicologos(){
        Mockito.when(repository.findAll()).thenThrow(RuntimeException.class);
        Pageable pageable = PageRequest.of(0,10);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.listar(pageable));

        Assertions.assertEquals(PsicologoDataProvider.MENSAGEM_ERRO_LISTAR, exception.getMessage());
    }

}
