package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.infrastructure.repositories.HorarioPsicologoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HorarioPsicologoDataProviderTest {

    @Mock
    private HorarioPsicologoRepository repository;

    @InjectMocks
    private HorarioPsicologoDataProvider dataProvider;


    @Test
    void testeSalvarHorario(){

    }

    @Test
    void testeExceptionSalvarHorario(){

    }

    @Test
    void testeListarHorarioPorPsicologo(){

    }

    @Test
    void testeExceptionListarHorarioPorPsicologo(){

    }

    @Test
    void testeBuscarHorarioPorId(){

    }

    @Test
    void testeExceptionBuscarHorarioPorId(){

    }

    @Test
    void testeDeletarHorario(){

    }

    @Test
    void testeExceptionDeletarHorario(){

    }
}
