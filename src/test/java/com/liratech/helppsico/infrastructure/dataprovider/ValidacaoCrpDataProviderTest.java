package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.ValidacaoCrpBuilder;
import com.liratech.helppsico.domain.ValidacaoCrp;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.ValidacaoCrpMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.ValidacaoCrpRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.ValidacaoCrpEntity;
import com.liratech.helppsico.validators.ValidacaoCrpValidator;
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
class ValidacaoCrpDataProviderTest {

    @Mock
    private ValidacaoCrpMapperInfra mapper;

    @Mock
    private ValidacaoCrpRepository repository;

    @InjectMocks
    private ValidacaoCrpDataProvider dataProvider;

    private ValidacaoCrp validacaoCrpTeste;
    private ValidacaoCrpEntity validacaoCrpEntityTeste;
    private Page<ValidacaoCrpEntity> validacaoCrpEntityPage;

    @BeforeEach
    void inicializar() {
        validacaoCrpTeste = ValidacaoCrpBuilder.criarValidacaoCrp();
        validacaoCrpEntityTeste = ValidacaoCrpBuilder.criarValidacaoCrpEntity();
        validacaoCrpEntityPage = ValidacaoCrpBuilder.criarPageValidacaoCrpEntity();
    }

    @Test
    void testeSalvarValidacaoCrp() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(validacaoCrpEntityTeste);
        Mockito.when(mapper.paraEntity(Mockito.any())).thenReturn(validacaoCrpEntityTeste);
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(validacaoCrpTeste);

        ValidacaoCrp validacaoResultado = dataProvider.salvar(validacaoCrpTeste);

        ValidacaoCrpValidator.validaValidacaoCrpDomain(validacaoCrpTeste, validacaoResultado);
    }

    @Test
    void testeExceptionSalvarValidacaoCrp() {
        Mockito.when(repository.save(Mockito.any())).thenThrow(DataProviderException.class);
        Mockito.when(mapper.paraEntity(Mockito.any())).thenReturn(validacaoCrpEntityTeste);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.salvar(ValidacaoCrpBuilder.criarValidacaoCrp()));

        Assertions.assertEquals(ValidacaoCrpDataProvider.MENSAGEM_ERRO_SALVAR, exception.getMessage());
    }

    @Test
    void testeConsultarValidacaoCrpPorId() {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(validacaoCrpEntityTeste));
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(validacaoCrpTeste);

        Optional<ValidacaoCrp> validacaoResultado = dataProvider.consultarPorId(validacaoCrpEntityTeste.getId());

        validacaoResultado.ifPresent(validacaoCrp -> {
            ValidacaoCrpValidator.validaValidacaoCrpDomain(validacaoCrpTeste, validacaoCrp);
        });
    }

    @Test
    void testeExceptionConsultarValidacaoCrpPorId() {
        Mockito.when(repository.findById(Mockito.any())).thenThrow(DataProviderException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.consultarPorId(ValidacaoCrpBuilder.criarValidacaoCrp().getId()));

        Assertions.assertEquals(ValidacaoCrpDataProvider.MENSAGEM_ERRO_CONSULTAR_POR_ID, exception.getMessage());
    }

    @Test
    void testeListarValidacaoCrp() {
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(repository.findAll(pageable)).thenReturn(validacaoCrpEntityPage);
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(validacaoCrpTeste);

        Page<ValidacaoCrp> validacaoCrpResultado = dataProvider.listar(pageable);

        Assertions.assertEquals(validacaoCrpEntityPage.getTotalElements(), validacaoCrpResultado.getTotalElements());
        Assertions.assertEquals(validacaoCrpEntityPage.getSize(), validacaoCrpResultado.getSize());
        IntStream.range(0, validacaoCrpEntityPage.getContent().size())
                .forEach(i -> ValidacaoCrpValidator.validaValidacaoCrpDomain(
                        validacaoCrpTeste,
                        validacaoCrpResultado.getContent().get(i)
                ));
    }

    @Test
    void testeExceptionListarValidacaoCrp() {
        Mockito.when(repository.findAll()).thenThrow(RuntimeException.class);
        Pageable pageable = PageRequest.of(0, 10);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.listar(pageable));

        Assertions.assertEquals(ValidacaoCrpDataProvider.MENSAGEM_ERRO_LISTAR, exception.getMessage());
    }


    @Test
    void testeConsultarPorPsicologo(){
        Mockito.when(repository.findByPsicologoId(Mockito.any())).thenReturn(Optional.of(validacaoCrpEntityTeste));
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(validacaoCrpTeste);

        Optional<ValidacaoCrp> validacaoResultado = dataProvider.consultarPorPsicologo(validacaoCrpTeste.getPsicologo().getId());

        validacaoResultado.ifPresent(validacaoCrp -> {
            ValidacaoCrpValidator.validaValidacaoCrpDomain(validacaoCrp, validacaoCrp);
        });
    }

    @Test
    void testeExceptionConsultarPorPsicologoId(){
        Mockito.when(repository.findByPsicologoId(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.consultarPorPsicologo(ValidacaoCrpBuilder.criarValidacaoCrp().getPsicologo().getId()));

        Assertions.assertEquals(ValidacaoCrpDataProvider.MENSAGEM_ERRO_CONSULTAR_POR_PSICOLOGO, exception.getMessage());
    }

    @Test
    void testeDeletarValidacaoCrp() {
        UUID idGerado = ValidacaoCrpBuilder.criarValidacaoCrp().getId();

        Mockito.doNothing().when(repository).deleteById(Mockito.any());

        dataProvider.deletar(idGerado);

        Mockito.verify(repository, Mockito.times(1)).deleteById(idGerado);
    }

    @Test
    void testeExceptionDeletarValidacaoCrp() {
        Mockito.doThrow(DataProviderException.class).when(repository).deleteById(Mockito.any());

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.deletar(ValidacaoCrpBuilder.criarValidacaoCrp().getId()));

        Assertions.assertEquals(ValidacaoCrpDataProvider.MENSAGEM_ERRO_DELETAR, exception.getMessage());
    }
}