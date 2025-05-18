package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.VinculoBuilder;
import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.VinculoMapper;
import com.liratech.helppsico.infrastructure.repositories.VinculoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.VinculoEntity;
import com.liratech.helppsico.validators.VinculoValidator;
import lombok.RequiredArgsConstructor;
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
import org.yaml.snakeyaml.events.Event;

import java.util.Optional;
import java.util.UUID;

import static com.liratech.helppsico.infrastructure.dataprovider.VinculoDataProvider.*;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
public class VinculoDataProviderTest {
    @Mock
    private VinculoRepository repository;

    @InjectMocks
    private VinculoDataProvider dataProvider;

    private final VinculoMapper mapper;
    private Vinculo vinculoDomain;
    private VinculoEntity vinculoEntity;
    private UUID id;
    private Page<Vinculo> vinculoPage;
    private Pageable pageable;

    @BeforeEach
    void inicializarAtributos(){
        vinculoDomain = VinculoBuilder.criarVinculo();
        vinculoEntity = mapper.paraEntity(vinculoDomain);

        id = vinculoDomain.getId();

        vinculoPage = VinculoBuilder.criarPageDeVinculos();
        pageable = PageRequest.of(0, 10);
    }

    @Test
    void testeSalvarVinculo(){
        vinculoDomain.setId(null);

        Mockito.when(repository.save(Mockito.any())).thenReturn(vinculoEntity);

        Vinculo vinculoTeste = dataProvider.salvar(vinculoDomain);

        Assertions.assertNotNull(vinculoTeste.getId());
        VinculoValidator.validaVinculoDomain(mapper.paraDomain(vinculoEntity), vinculoTeste);
    }

    @Test
    void testeExceptionSalvarVinculo(){
        Mockito.when(repository.save(Mockito.any())).thenThrow(DataProviderException.class);

        DataProviderException exception = Assertions.assertThrows(
                DataProviderException.class,
                () -> dataProvider.salvar(vinculoDomain)
        );

        Assertions.assertEquals(MENSAGEM_ERRO_SALVAR, exception.getMessage());
    }

    @Test
    void testeConsultarVinculoPorId(){
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(vinculoEntity));

        Optional<Vinculo> optionalVinculo = dataProvider.consultarPorId(id);

        optionalVinculo.ifPresent(vinculo -> {
            Assertions.assertNotNull(vinculo.getId());
            VinculoValidator.validaVinculoDomain(vinculoDomain, vinculo);
        });
    }

    @Test
    void testeExceptionConsultarVinculoPorId(){
        Mockito.when(repository.findById(Mockito.any())).thenThrow(DataProviderException.class);

        DataProviderException exception = Assertions.assertThrows(
                DataProviderException.class,
                () -> dataProvider.consultarPorId(id)
        );

        Assertions.assertEquals(MENSAGEM_ERRO_CONSULTAR_ID, exception.getMessage());
    }

    @Test
    void testeDeletarVinculo(){
        Mockito.doNothing().when(repository).deleteById(Mockito.any());

        dataProvider.deletar(id);

        Mockito.verify(repository).deleteById(id);
    }

    @Test
    void testeExceptionDeletarVinculo(){
        Mockito.doThrow(DataProviderException.class).when(repository).deleteById(Mockito.any());

        DataProviderException exception = Assertions.assertThrows(
                DataProviderException.class,
                () -> dataProvider.deletar(id)
        );

        Assertions.assertEquals(MENSAGEM_ERRO_DELETAR, exception.getMessage());
    }

    @Test
    void testeListarVinculoPorIdPsicologo(){
        Mockito.when(repository.findAllByPsicologo_Id(Mockito.any(), Mockito.any())).thenReturn(vinculoPage.map(mapper::paraEntity));

        Page<Vinculo> vinculoPageResultado = dataProvider.listarPorIdPsicologo(vinculoDomain.getPsicologo().getId(), pageable);

        vinculoPageResultado.map(vinculo -> {
            Assertions.assertNotNull(vinculo.getId());
            VinculoValidator.validaVinculoDomain(vinculoDomain, vinculo);
            return null;
        });
    }

    @Test
    void testeExceptionListarVinculoPorIdPsicologo(){
        Mockito.doThrow(DataProviderException.class).when(repository).findAllByPsicologo_Id(Mockito.any(), Mockito.any());

        DataProviderException exception = Assertions.assertThrows(
                DataProviderException.class,
                () -> dataProvider.listarPorIdPsicologo(id, pageable)
        );

        Assertions.assertEquals(MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO, exception.getMessage());
    }

    @Test
    void testeConsultarVinculoPorIdPaciente(){
        Mockito.when(repository.findByPaciente_Id(Mockito.any())).thenReturn(Optional.of(vinculoEntity));

        Optional<Vinculo> vinculoOptional = dataProvider.consultarPorIdPaciente(vinculoDomain.getPaciente().getId());

        vinculoOptional.ifPresent(vinculo -> {
            Assertions.assertNotNull(vinculo.getId());
            VinculoValidator.validaVinculoDomain(vinculoDomain, vinculo);
        });
    }

    @Test
    void testeExceptionConsultarVinculoPorIdPaciente(){
        Mockito.doThrow(DataProviderException.class).when(repository).findByPaciente_Id(Mockito.any());

        DataProviderException exception = Assertions.assertThrows(
                DataProviderException.class,
                () -> dataProvider.consultarPorIdPaciente(id)
        );

        Assertions.assertEquals(MENSAGEM_ERRO_CONSULTAR_POR_PACIENTE, exception.getMessage());
    }
}
