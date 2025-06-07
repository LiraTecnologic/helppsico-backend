package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.HorarioBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.domain.Horario;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.HorarioMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.HorarioRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.HorarioEntity;
import com.liratech.helppsico.validators.HorarioValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@AllArgsConstructor
public class HorarioDataProviderTest {

    @Mock
    private HorarioRepository repository;

    @InjectMocks
    private HorarioDataProvider dataProvider;

    private Horario horarioDomain;
    private HorarioEntity horarioEntity;
    private List<Horario> horarioList;
    private HorarioMapperInfra mapper;

    @BeforeEach
    void inicializarAtributos(){
        horarioDomain = HorarioBuilder.criarHorario();
        horarioEntity = mapper.paraEntity(horarioDomain);
        horarioList = HorarioBuilder.criarListaHorarioDomain();
    }

    @Test
    void testeSalvarHorario(){
        horarioDomain.setId(null);

        Mockito.when(repository.save(Mockito.any())).thenReturn(horarioEntity);

        Horario horarioResultado = dataProvider.salvar(horarioDomain);

        Assertions.assertEquals(horarioResultado.getId(), horarioEntity.getId());
        HorarioValidator.validaHorarioDomain(horarioDomain, horarioResultado);
    }

    @Test
    void testeExceptionSalvarHorario(){
        Mockito.when(repository.save(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.salvar(horarioDomain));

        Assertions.assertEquals(HorarioDataProvider.MENSAGEM_ERRO_SALVAR, exception.getMessage());
    }

    @Test
    void testeListarHorariosPorPsicologo(){
        Mockito.when(repository.findAllByPsicologoId(Mockito.any())).thenReturn(horarioList.stream().map(mapper::paraEntity).toList());

        List<Horario> horariosResultado = dataProvider.listarPorPsicologo(horarioDomain.getPsicologo().getId());

        for (Horario horario : horariosResultado) {
            Assertions.assertEquals(horario.getId(), horarioDomain.getId());
            HorarioValidator.validaHorarioDomain(horario, horarioDomain);
        }
    }

    @Test
    void testeExceptionListarHorariosPorPsicologo(){
        Mockito.when(repository.findAllByPsicologoId(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.listarPorPsicologo(horarioDomain.getPsicologo().getId()));

        Assertions.assertEquals(HorarioDataProvider.MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO, exception.getMessage());
    }

    @Test
    void testeConsultarHorarioPorId(){
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(horarioEntity));

        Optional<Horario> horarioResultado = dataProvider.consultarPorId(horarioDomain.getId());

        horarioResultado.ifPresent(horario -> {
                Assertions.assertEquals(horario.getId(), horarioDomain.getId());
                HorarioValidator.validaHorarioDomain(horario, horarioDomain);
        });
    }

    @Test
    void testeExceptionConsultarHorarioPorId(){
        Mockito.when(repository.findById(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.consultarPorId(horarioDomain.getId()));

        Assertions.assertEquals(HorarioDataProvider.MENSAGEM_ERRO_CONSULTAR_POR_ID, exception.getMessage());
    }

    @Test
    void testeDeletarHorario(){
        Mockito.doNothing().when(repository).deleteById(Mockito.any());

        dataProvider.deletar(horarioDomain.getId());

        Mockito.verify(repository).deleteById(horarioDomain.getId());
    }

    @Test
    void testeExceptionDeletarHorario(){
        Mockito.doThrow(DataProviderException.class).when(repository).deleteById(Mockito.any());

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.deletar(horarioDomain.getId()));

        Assertions.assertEquals(HorarioDataProvider.MENSAGEM_ERRO_DELETAR, exception.getMessage());
    }
}
