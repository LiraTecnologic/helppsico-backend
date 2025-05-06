package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.HorarioPsicologoBuilder;
import com.liratech.helppsico.domain.HorarioPsicologo;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.HorarioPsicologoMapper;
import com.liratech.helppsico.infrastructure.repositories.HorarioPsicologoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.HorarioPsicologoEntity;
import com.liratech.helppsico.validators.HorarioPsicologoValidator;
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

import static com.liratech.helppsico.infrastructure.dataprovider.HorarioPsicologoDataProvider.*;

@ExtendWith(MockitoExtension.class)
public class HorarioPsicologoDataProviderTest {

    @Mock
    private HorarioPsicologoRepository repository;

    @InjectMocks
    private HorarioPsicologoDataProvider dataProvider;

    private HorarioPsicologo horarioPsicologoDomain;
    private HorarioPsicologoEntity horarioPsicologoEntity;
    private HorarioPsicologoMapper mapper;
    private Page<HorarioPsicologo> horarioPsicologoPage;
    private Page<HorarioPsicologoEntity> horarioPsicologoEntityPage;
    private UUID id;
    private Pageable pageable;

    @BeforeEach
    void inicializarAtributos(){
        horarioPsicologoDomain = HorarioPsicologoBuilder.criarHorarioPsicologo();
        horarioPsicologoEntity = HorarioPsicologoBuilder.criarHorarioPsicologoEntity();
        horarioPsicologoPage = HorarioPsicologoBuilder.criarPageDeHorarioPsicologos();
        horarioPsicologoEntityPage = horarioPsicologoPage.map(mapper::paraEntity);

        id = horarioPsicologoDomain.getId();
        pageable = PageRequest.of(0,10);
    }

    @Test
    void testeSalvarHorario(){
        horarioPsicologoDomain.setId(null);

        Mockito.when(repository.save(Mockito.any())).thenReturn(horarioPsicologoEntity);

        HorarioPsicologo horarioTeste = dataProvider.salvar(horarioPsicologoDomain);

        Assertions.assertNotNull(horarioTeste.getId());
        HorarioPsicologoValidator.validaHorarioPsicologoDomain(mapper.paraDomain(horarioPsicologoEntity), horarioTeste);
    }

    @Test
    void testeExceptionSalvarHorario(){
        Mockito.when(repository.save(Mockito.any())).thenThrow(Exception.class);

        DataProviderException exception = Assertions.assertThrows(
                DataProviderException.class,
                () -> dataProvider.salvar(horarioPsicologoDomain)
        );

        Assertions.assertEquals(MENSAGEM_ERRO_SALVAR, exception.getMessage());
    }

    @Test
    void testeListarHorarioPorPsicologo(){
        Mockito.when(repository.buscarPorPsicologo(Mockito.any(), Mockito.any())).thenReturn(horarioPsicologoEntityPage);

        Page<HorarioPsicologo> horarioPsicologoTeste = dataProvider.listarPorPsicologo(id, pageable);

        horarioPsicologoTeste.map(horarioPsicologo -> {
            Assertions.assertNotNull(horarioPsicologo.getId());
            HorarioPsicologoValidator.validaHorarioPsicologoDomain(horarioPsicologoDomain, horarioPsicologo);
            return null;
        });
    }

    @Test
    void testeExceptionListarHorarioPorPsicologo(){
        Mockito.when(repository.buscarPorPsicologo(Mockito.any(), Mockito.any())).thenThrow(Exception.class);

        DataProviderException exception = Assertions.assertThrows(
                DataProviderException.class,
                () -> dataProvider.listarPorPsicologo(id, pageable)
        );

        Assertions.assertEquals(MENSAGEM_ERRO_BUSCAR_POR_PSICOLOGO, exception.getMessage());
    }

    @Test
    void testeBuscarHorarioPorId(){
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(horarioPsicologoEntity));

        Optional<HorarioPsicologo> horarioPsicologoOptional = dataProvider.buscarPorId(id);

        horarioPsicologoOptional.ifPresent(horarioPsicologo -> {
            Assertions.assertNotNull(horarioPsicologo.getId());
            HorarioPsicologoValidator.validaHorarioPsicologoDomain(horarioPsicologoDomain, horarioPsicologo);
        });
    }

    @Test
    void testeExceptionBuscarHorarioPorId(){
        Mockito.when(repository.findById(Mockito.any())).thenThrow(Exception.class);

        DataProviderException exception = Assertions.assertThrows(
                DataProviderException.class,
                () -> dataProvider.buscarPorId(id)
        );

        Assertions.assertEquals(MENSAGEM_ERRO_BUSCAR_POR_ID, exception.getMessage());
    }

    @Test
    void testeDeletarHorario(){
        Mockito.doNothing().when(repository).deleteById(Mockito.any());

        dataProvider.deletar(id);

        Mockito.verify(repository).deleteById(Mockito.any());
    }

    @Test
    void testeExceptionDeletarHorario(){
        Mockito.doThrow(Exception.class).when(repository).deleteById(Mockito.any());

        DataProviderException exception = Assertions.assertThrows(
                DataProviderException.class,
                () -> dataProvider.deletar(id)
        );

        Assertions.assertEquals(MENSAGEM_ERRO_DELETAR_HORARIO, exception.getMessage());
    }
}
