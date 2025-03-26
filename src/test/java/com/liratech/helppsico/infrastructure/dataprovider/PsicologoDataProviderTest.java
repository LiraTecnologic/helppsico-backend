package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
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

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PsicologoDataProviderTest {

    @Mock
    private PsicologoRepository repository;

    @InjectMocks
    private PsicologoDataProvider dataProvider;

    private PsicologoMapper mapper;

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
        Psicologo psicologoTeste = PsicologoBuilder.criarPsicologo();

        Mockito.when(repository.findByNome(Mockito.any())).thenReturn();
        //ver o Builder do vitor com criação de Lista Psicologo
    }

    @Test
    void testeExceptionConsultarPorNome(){
        Mockito.when(repository.findByNome(Mockito.any())).thenThrow(RuntimeException.class);
        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.consultarPorNome();//ver oq colocar aqui
        Assertions.assertEquals(PsicologoDataProvider.MENSAGEM_ERRO_CONSULTAR_POR_NOME, exception.getMessage());
    }

    @Test
    void testeConsultarMelhoresAvaliados(){
        Psicologo psicologoTeste = PsicologoBuilder.criarPsicologo();

        Mockito.when(repository.consultarMelhoresAvaliados(Mockito.any())).thenReturn();
        //ver o Builder do vitor com criação de Lista Psicologo
    }

    @Test
    void testeExceptionConsultarMelhoresAvaliados(){
        Mockito.when(repository.consultarMelhoresAvaliados(Mockito.any())).thenThrow(RuntimeException.class);
        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.consultarMelhoresAvaliados();//ver oq colocar aqui
        Assertions.assertEquals(PsicologoDataProvider.MENSAGEM_ERRO_CONSULTAR_MELHORES_AVALIADOS, exception.getMessage());
    }


}
