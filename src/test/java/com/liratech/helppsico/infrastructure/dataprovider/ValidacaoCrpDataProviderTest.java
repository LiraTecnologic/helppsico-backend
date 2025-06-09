package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.ValidacaoCrpBuilder;
import com.liratech.helppsico.domain.ValidacaoCrp;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.ValidacaoCrpMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.ValidacaoCrpRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.ValidacaoCrpEntity;
import com.liratech.helppsico.validators.ValidacaoCrpValidator;
import lombok.AllArgsConstructor;
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
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
@AllArgsConstructor
class ValidacaoCrpDataProviderTest {

    @Mock
    private ValidacaoCrpRepository repository;

    @InjectMocks
    private ValidacaoCrpDataProvider dataProvider;

    private ValidacaoCrpMapperInfra mapper;

    @Test
    void testeSalvarValidacaoCrp() {
        ValidacaoCrp validacaoCrp = ValidacaoCrpBuilder.criarValidacaoCrp();
        validacaoCrp.setId(null);

        ValidacaoCrpEntity validacaoSalva = mapper.paraEntity(validacaoCrp);
        UUID idGerado = UUID.randomUUID();
        validacaoSalva.setId(idGerado);

        Mockito.when(repository.save(Mockito.any())).thenReturn(validacaoSalva);

        ValidacaoCrp validacaoResultado = dataProvider.salvar(validacaoCrp);
        ValidacaoCrpValidator.validaValidacaoCrpDomain(mapper.paraDomain(validacaoSalva), validacaoResultado);
    }

    @Test
    void testeExceptionSalvarValidacaoCrp() {
        Mockito.when(repository.save(Mockito.any())).thenThrow(DataProviderException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.salvar(ValidacaoCrpBuilder.criarValidacaoCrp()));

        Assertions.assertEquals(ValidacaoCrpDataProvider.MENSAGEM_ERRO_SALVAR, exception.getMessage());
    }

    @Test
    void testeConsultarValidacaoCrpPorId() {
        ValidacaoCrp validacaoTeste = ValidacaoCrpBuilder.criarValidacaoCrp();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(mapper.paraEntity(validacaoTeste)));

        Optional<ValidacaoCrp> validacaoResultado = dataProvider.consultarPorId(validacaoTeste.getId());

        validacaoResultado.ifPresent(validacaoCrp -> {
            ValidacaoCrpValidator.validaValidacaoCrpDomain(validacaoTeste, validacaoCrp);
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
        Page<ValidacaoCrp> validacaoCrpPage = ValidacaoCrpBuilder.criarPageValidacaoCrp();
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(repository.findAll(pageable)).thenReturn(validacaoCrpPage.map(mapper::paraEntity));

        Page<ValidacaoCrp> validacaoCrpResultado = dataProvider.listar(pageable);

        Assertions.assertEquals(validacaoCrpPage.getTotalElements(), validacaoCrpResultado.getTotalElements());
        Assertions.assertEquals(validacaoCrpPage.getSize(), validacaoCrpResultado.getSize());
        IntStream.range(0, validacaoCrpPage.getContent().size())
                .forEach(i -> ValidacaoCrpValidator.validaValidacaoCrpDomain(
                        validacaoCrpPage.getContent().get(i),
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
        ValidacaoCrp validacaoTeste = ValidacaoCrpBuilder.criarValidacaoCrp();

        Mockito.when(repository.findByPsicologoId(Mockito.any())).thenReturn(Optional.of(mapper.paraEntity(validacaoTeste)));

        Optional<ValidacaoCrp> validacaoResultado = dataProvider.consultarPorId(validacaoTeste.getPsicologo().getId());

        validacaoResultado.ifPresent(validacaoCrp -> {
            ValidacaoCrpValidator.validaValidacaoCrpDomain(validacaoTeste, validacaoCrp);
        });
    }

    @Test
    void testeConsultarPorPsicologoId(){
        Mockito.when(repository.findByPsicologoId(Mockito.any())).thenThrow(DataProviderException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.consultarPorId(ValidacaoCrpBuilder.criarValidacaoCrp().getPsicologo().getId()));

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