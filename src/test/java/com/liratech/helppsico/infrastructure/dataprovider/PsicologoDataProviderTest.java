package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.PsicologoMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.PsicologoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import com.liratech.helppsico.validators.PsicologoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
public class PsicologoDataProviderTest {

    @Mock
    private PsicologoMapperInfra mapper;

    @Mock
    private PsicologoRepository repository;

    @InjectMocks
    private PsicologoDataProvider dataProvider;

    private Psicologo psicologoTeste;
    private PsicologoEntity psicologoEntityTeste;
    private Page<PsicologoEntity> psicologoPageEntity;

    @BeforeEach
    void inicializar() {
        psicologoTeste = PsicologoBuilder.criarPsicologo();
        psicologoEntityTeste = PsicologoBuilder.criarPsicologoEntity();
        psicologoPageEntity = PsicologoBuilder.criarPageDePsicologosEntity();
    }

    @Test
    void testeSalvarPsicologo(){
        Mockito.when(repository.save(Mockito.any())).thenReturn(psicologoEntityTeste);
        Mockito.when(mapper.paraEntity(Mockito.any())).thenReturn(psicologoEntityTeste);
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(psicologoTeste);

        Psicologo psicologoResultado = dataProvider.salvar(psicologoTeste);

        PsicologoValidator.validaPsicologoDomain(psicologoTeste, psicologoResultado);
    }

    @Test
    void testeExceptionSalvarPsicologo(){
        Mockito.when(repository.save(Mockito.any())).thenThrow(RuntimeException.class);
        Mockito.when(mapper.paraEntity(Mockito.any())).thenReturn(psicologoEntityTeste);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.salvar(PsicologoBuilder.criarPsicologo()));

        Assertions.assertEquals(PsicologoDataProvider.MENSAGEM_ERRO_SALVAR, exception.getMessage());
    }

    @Test
    void testeConsultarPorId(){
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(psicologoEntityTeste));

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
        String nome = psicologoPageEntity.getContent().getFirst().getNome();
        Pageable pageable = PageRequest.of(0,10);

        Mockito.when(repository.findAllByNome(Mockito.any(), Mockito.any())).thenReturn(psicologoPageEntity);
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(psicologoTeste);

        Page<Psicologo> psicologosListResultado = dataProvider.consultarPorNome(nome, pageable);

        Assertions.assertEquals(psicologoPageEntity.getTotalElements(), psicologosListResultado.getTotalElements());
        IntStream.range(0, psicologoPageEntity.getTotalPages())
                .forEach(i -> PsicologoValidator.validaPsicologoDomain(
                        psicologoTeste,
                        psicologosListResultado.getContent().get(i)
                ));
    }

    @Test
    void testeExceptionConsultarPorNome(){
        Pageable pageable = PageRequest.of(0,10);

        Mockito.when(repository.findAllByNome(Mockito.any(), Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.consultarPorNome(PsicologoBuilder.criarPsicologo().getNome(), pageable));

        Assertions.assertEquals(PsicologoDataProvider.MENSAGEM_ERRO_CONSULTAR_POR_NOME, exception.getMessage());
    }

    @Test
    void testeConsultarMelhoresAvaliados(){
        Pageable pageable = PageRequest.of(0,10);

        Mockito.when(repository.consultarMelhoresAvaliados(Mockito.any())).thenReturn(psicologoPageEntity);
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(psicologoTeste);

        Page<Psicologo> psicologosPageResultado = dataProvider.consultarMelhoresAvaliados(pageable);

        Assertions.assertEquals(psicologoPageEntity.getTotalElements(), psicologosPageResultado.getTotalElements());
        Assertions.assertEquals(psicologoPageEntity.getSize(), psicologosPageResultado.getSize());
        IntStream.range(0, psicologoPageEntity.getContent().size())
                .forEach(i -> PsicologoValidator.validaPsicologoDomain(
                        psicologoTeste,
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
        Mockito.when(repository.findByCrp(Mockito.any())).thenReturn(Optional.of(psicologoEntityTeste));
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(psicologoTeste);

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
        Pageable pageable = PageRequest.of(0,10);

        Mockito.when(repository.consultarPsicologosAprovados(Mockito.any())).thenReturn(psicologoPageEntity);
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(psicologoTeste);

        Page<Psicologo> psicologosPageResultado = dataProvider.listar(pageable);

        Assertions.assertEquals(psicologoPageEntity.getTotalElements(), psicologosPageResultado.getTotalElements());
        Assertions.assertEquals(psicologoPageEntity.getSize(), psicologosPageResultado.getSize());
        IntStream.range(0, psicologoPageEntity.getContent().size())
                .forEach(i -> PsicologoValidator.validaPsicologoDomain(
                        psicologoTeste,
                        psicologosPageResultado.getContent().get(i)
                ));
    }

    @Test
    void testeExceptionListarPsicologos(){
        Mockito.when(repository.consultarPsicologosAprovados(Mockito.any())).thenThrow(RuntimeException.class);
        Pageable pageable = PageRequest.of(0,10);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.listar(pageable));

        Assertions.assertEquals(PsicologoDataProvider.MENSAGEM_ERRO_LISTAR, exception.getMessage());
    }

}
