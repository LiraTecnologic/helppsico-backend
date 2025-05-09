package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.horario.HorarioNaoEncontradoException;
import com.liratech.helppsico.application.gateways.HorarioPsicologoGateway;
import com.liratech.helppsico.builders.HorarioBuilder;
import com.liratech.helppsico.builders.HorarioPsicologoBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.HorarioPsicologo;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.validators.HorarioPsicologoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.liratech.helppsico.application.usecases.HorarioPsicologoUseCase.ERRO_HORARIO_NAO_ENCONTRADO;

@AllArgsConstructor
@ExtendWith(MockitoExtension.class)
public class HorarioPsicologoUseCaseTest {

    @Mock
    private PsicologoUseCase psicologoUseCase;

    @Mock
    private HorarioPsicologoGateway gateway;

    @Captor
    private ArgumentCaptor<HorarioPsicologo> captor;

    @InjectMocks
    private HorarioPsicologoUseCase useCase;

    private Psicologo psicologo;
    private HorarioPsicologo horarioPsicologoEntrada;
    private HorarioPsicologo horarioPsicologoSaida;
    private HorarioPsicologo horarioPsicologoAlterado;
    private UUID idHorario;
    private UUID idPsicologo;
    private Page<HorarioPsicologo> horarioPsicologoPage;

    @BeforeEach
    void inicializarAtributos(){
        psicologo = PsicologoBuilder.criarPsicologo();
        horarioPsicologoEntrada = HorarioPsicologoBuilder.criarHorarioPsicologo();
        horarioPsicologoSaida = horarioPsicologoEntrada;
        horarioPsicologoAlterado = horarioPsicologoEntrada;
        horarioPsicologoPage = HorarioPsicologoBuilder.criarPageDeHorarioPsicologos();

        idHorario = horarioPsicologoEntrada.getId();
        idPsicologo = horarioPsicologoEntrada.getPsicologo().getId();
        horarioPsicologoEntrada.setId(null);

        horarioPsicologoAlterado.setHorarios(List.of(HorarioBuilder.criarHorarioDiaQuarta()));
    }

    @Test
    void testeCadastrarHorario(){
        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(psicologo);
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(horarioPsicologoSaida);

        useCase.cadastrar(horarioPsicologoEntrada);
        HorarioPsicologo horarioPsicologo = captor.getValue();

        Assertions.assertNotNull(horarioPsicologo.getId());
        HorarioPsicologoValidator.validaHorarioPsicologoDomain(horarioPsicologoSaida, horarioPsicologo);
    }

    @Test
    void testeAlterarHorario(){
        Mockito.when(useCase.consultarPorId(Mockito.any())).thenReturn(horarioPsicologoSaida);
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(horarioPsicologoAlterado);

        useCase.alterar(idHorario, horarioPsicologoAlterado);
        HorarioPsicologo horarioPsicologo = captor.getValue();

        Assertions.assertNotNull(horarioPsicologo.getId());
        HorarioPsicologoValidator.validaHorarioPsicologoDomain(horarioPsicologoAlterado, horarioPsicologo);
    }

    @Test
    void testeListarHorariosPorPsicologo(){
        Mockito.when(gateway.listarPorPsicologo(Mockito.any(), Mockito.any())).thenReturn(horarioPsicologoPage);

        Page<HorarioPsicologo> pageTeste = useCase.listarPorPsicologo(idPsicologo, PageRequest.of(0,10));

        for (int i = 0; i < horarioPsicologoPage.getSize(); i++) {
            HorarioPsicologoValidator.validaHorarioPsicologoDomain(
                    horarioPsicologoPage.getContent().get(i),
                    pageTeste.getContent().get(i));
        }
    }

    @Test
    void testeConsultarHorarioPorId(){
        Mockito.when(gateway.buscarPorId(Mockito.any())).thenReturn(Optional.of(horarioPsicologoSaida));

        HorarioPsicologo horarioPsicologoBuscado = useCase.consultarPorId(idHorario);

        Assertions.assertNotNull(horarioPsicologoBuscado.getId());
        HorarioPsicologoValidator.validaHorarioPsicologoDomain(horarioPsicologoSaida, horarioPsicologoBuscado);
    }

    @Test
    void testeHorarioNaoEncontradoException(){
        Mockito.when(gateway.buscarPorId(Mockito.any())).thenThrow(RuntimeException.class);

        HorarioNaoEncontradoException exception = Assertions.assertThrows(
                HorarioNaoEncontradoException.class,
                () -> useCase.consultarPorId(idHorario)
        );

        Assertions.assertEquals(ERRO_HORARIO_NAO_ENCONTRADO, exception.getMessage());
    }

    @Test
    void testeDeletarHorario(){
        Mockito.when(useCase.consultarPorId(Mockito.any())).thenReturn(horarioPsicologoSaida);
        Mockito.doNothing().when(gateway).deletar(Mockito.any());

        useCase.deletar(idHorario);
        Mockito.verify(gateway).deletar(Mockito.any());
    }

}
