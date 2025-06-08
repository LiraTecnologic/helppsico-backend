package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.consulta.ConsultaInvalidaException;
import com.liratech.helppsico.application.exceptions.consulta.ConsultaJaExistenteNaDataException;
import com.liratech.helppsico.application.exceptions.consulta.ConsultaNaoEncontradaException;
import com.liratech.helppsico.application.gateways.ConsultaGateway;
import com.liratech.helppsico.builders.*;
import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.infrastructure.mapper.ConsultaMapperInfra;
import com.liratech.helppsico.validators.ConsultaValidator;
import com.liratech.helppsico.validators.EnderecoValidator;
import com.liratech.helppsico.validators.PacienteValidator;
import com.liratech.helppsico.validators.PsicologoValidator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@AllArgsConstructor
class ConsultaUseCaseTest {

    @Mock
    private PacienteUseCase pacienteUseCase;

    @Mock
    private PsicologoUseCase psicologoUseCase;

    @Mock
    private HorarioUseCase horarioUseCase;

    @Mock
    private VinculoUseCase vinculoUseCase;

    @Mock
    private ConsultaGateway gateway;

    @Captor
    private ArgumentCaptor<Consulta> captor;

    @InjectMocks
    private ConsultaUseCase useCase;

    private Consulta consultaTeste;
    private Consulta dataTeste;
    private List<Consulta> consultaList;
    private Page<Consulta> consultaPage;
    private Vinculo vinculoTeste;

    @BeforeEach
    void inicializarAtributo(){
        consultaTeste = ConsultaBuilder.criarConsulta();

        consultaList = ConsultaBuilder.criarListaConslta();
        consultaList.forEach(consulta -> consulta.setData(consultaTeste.getData()));

        consultaPage = ConsultaBuilder.criarPageConsultaDomain();
        consultaPage.forEach(consulta -> {
            consulta.setPaciente(consultaTeste.getPaciente());
            consulta.setPsicologo(consultaTeste.getPsicologo());
        });

        dataTeste = Consulta.builder()
                .horario(HorarioBuilder.criarHorario())
                .data(LocalDate.now().plus(10, ChronoUnit.MINUTES))
                .build();

        vinculoTeste = VinculoBuilder.criarVinculo();
        vinculoTeste.setPaciente(consultaTeste.getPaciente());
        vinculoTeste.setPsicologo(consultaTeste.getPsicologo());
    }

    @Test
    void testeAgendamentoDeConsulta() {
        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(consultaTeste.getPaciente());
        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(consultaTeste.getPsicologo());
        Mockito.when(horarioUseCase.consultarPorId(Mockito.any())).thenReturn(consultaTeste.getHorario());
        Mockito.when(vinculoUseCase.consultarAtivoPorPaciente(Mockito.any())).thenReturn(vinculoTeste);
        Mockito.when(gateway.consultarConsultasMesmoDia(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(consultaTeste);

        consultaTeste.setId(null);
        useCase.agendar(consultaTeste);
        Consulta resultado = captor.getValue();

        Assertions.assertNotNull(resultado.getId());
        ConsultaValidator.validaConsultaDomain(consultaTeste, resultado);
    }

    @Test
    void testeExceptionConsultaInvalida() {
        vinculoTeste.setPsicologo(PsicologoBuilder.criarPsicologo());

        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());
        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());
        Mockito.when(horarioUseCase.consultarPorId(Mockito.any())).thenReturn(consultaTeste.getHorario());
        Mockito.when(vinculoUseCase.consultarAtivoPorPaciente(Mockito.any())).thenReturn(vinculoTeste);
        Mockito.when(gateway.consultarConsultasMesmoDia(Mockito.any(), Mockito.any())).thenReturn(consultaList);
        Mockito.when(gateway.salvar(Mockito.any())).thenReturn(consultaTeste);

        ConsultaInvalidaException exception = Assertions.assertThrows(
                ConsultaInvalidaException.class,
                () -> useCase.agendar(consultaTeste));

        Assertions.assertEquals(exception.getMessage(), useCase.MENSAGEM_PSICOLOGO_PACIENTE_NAO_VINCULADOS);
    }

    @Test
    void testeExceptionDataNaoDisponivelAgendamentoDeConsulta() {
        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());
        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());
        Mockito.when(horarioUseCase.consultarPorId(Mockito.any())).thenReturn(consultaTeste.getHorario());
        Mockito.when(vinculoUseCase.consultarAtivoPorPaciente(Mockito.any())).thenReturn(vinculoTeste);
        Mockito.when(gateway.consultarConsultasMesmoDia(Mockito.any(), Mockito.any())).thenReturn(consultaList);
        Mockito.when(gateway.salvar(Mockito.any())).thenReturn(consultaTeste);

        ConsultaJaExistenteNaDataException exception = Assertions.assertThrows(ConsultaJaExistenteNaDataException.class,
                () -> useCase.agendar(consultaTeste));

        Assertions.assertEquals(exception.getMessage(), useCase.MENSAGEM_CONSULTA_JA_EXISTENTE_NA_DATA);
    }

    @Test
    void testeCancelarConsulta() {
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(consultaTeste));
        Mockito.doNothing().when(horarioUseCase.cadastrar(Mockito.any()));
        Mockito.doNothing().when(gateway).deletar(Mockito.any());

        useCase.cancelar(consultaTeste.getId());

        Mockito.verify(gateway, Mockito.times(1)).deletar(consultaTeste.getId());
    }

    @Test
    void testeExceptionConsultaNaoEncontradaCancelarConsulta() {
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.empty());
        Mockito.doNothing().when(horarioUseCase.cadastrar(Mockito.any()));
        Mockito.doNothing().when(gateway).deletar(Mockito.any());

        ConsultaNaoEncontradaException exception = Assertions.assertThrows(ConsultaNaoEncontradaException.class,
                () -> useCase.cancelar(consultaTeste.getId()));

        Assertions.assertEquals(exception.getMessage(), useCase.MENSAGEM_CONSULTA_NAO_ENCONTRADA);
    }

    @Test
    void testeConsultarConsultasFuturasPaciente() {
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(consultaTeste.getPaciente());
        Mockito.when(vinculoUseCase.consultarAtivoPorPaciente(Mockito.any())).thenReturn(vinculoTeste);
        Mockito.when(gateway.consultarConsultasFuturasPaciente(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(consultaPage);

        Page<Consulta> resultado = useCase.consultarConsultasFuturasPaciente(vinculoTeste.getPaciente().getId(), pageable);

        resultado.forEach(consulta -> ConsultaValidator.validaConsultaDomain(consulta, consultaTeste));
    }

    @Test
    void testeConsultarHistoricoPaciente() {
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(consultaTeste.getPaciente());
        Mockito.when(vinculoUseCase.consultarAtivoPorPaciente(Mockito.any())).thenReturn(vinculoTeste);
        Mockito.when(gateway.consultarHistoricoPaciente(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(consultaPage);

        Page<Consulta> resultado = useCase.consultarHistoricoPaciente(vinculoTeste.getPaciente().getId(), pageable);

        resultado.forEach(consulta ->
                ConsultaValidator.validaConsultaDomain(consulta, consultaTeste));
    }

    @Test
    void testeConsultarConsultasFuturasPsicologo() {
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(consultaTeste.getPsicologo());
        Mockito.when(gateway.consultarConsultasFuturasPsicologo(Mockito.any(), Mockito.any())).thenReturn(consultaPage);

        Page<Consulta> resultado = useCase.consultarConsultasFuturasPsicologo(vinculoTeste.getPsicologo().getId(), pageable);

        resultado.forEach(consulta -> ConsultaValidator.validaConsultaDomain(consulta, consultaTeste));
    }

    @Test
    void testeConsultarHistoricoPsicologo() {
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(consultaTeste.getPsicologo());
        Mockito.when(gateway.consultarHistoricoPsicologo(Mockito.any(), Mockito.any())).thenReturn(consultaPage);

        Page<Consulta> resultado = useCase.consultarHistoricoPsicologo(vinculoTeste.getPsicologo().getId(), pageable);

        resultado.forEach(consulta ->
                ConsultaValidator.validaConsultaDomain(consulta, consultaTeste));
    }

    @Test
    void testeAlteraracaoDataConsulta() {
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(consultaTeste));
        Mockito.when(gateway.consultarConsultasMesmoDia(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(consultaTeste);

        useCase.alterarData(consultaTeste.getId(), dataTeste);

        Consulta resultado = captor.getValue();

        Assertions.assertEquals(consultaTeste.getId(), resultado.getId());
        ConsultaValidator.validaConsultaDomain(consultaTeste, resultado);
    }

    @Test
    void testeExceptionConsultaNaoEncontradaEmAlteracaoDeData() {
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(gateway.consultarConsultasMesmoDia(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(consultaTeste);

        ConsultaNaoEncontradaException exception = Assertions.assertThrows(ConsultaNaoEncontradaException.class,
                () -> useCase.alterarData(consultaTeste.getId(), dataTeste));

        Assertions.assertEquals(exception.getMessage(), useCase.MENSAGEM_CONSULTA_NAO_ENCONTRADA);
    }

    @Test
    void testeExceptionConsultaExistenteEmAlteracaoDeData() {
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(consultaTeste));
        Mockito.when(gateway.consultarConsultasMesmoDia(Mockito.any(), Mockito.any())).thenReturn(consultaList);
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(consultaTeste);

        useCase.alterarData(consultaTeste.getId(), dataTeste);

        Consulta resultado = captor.getValue();

        Assertions.assertEquals(consultaTeste.getId(), resultado.getId());
        ConsultaValidator.validaConsultaDomain(consultaTeste, resultado);
    }

    @Test
    void testeFinalizarConsulta() {
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(consultaTeste));
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(consultaTeste);

        useCase.finalizar(consultaTeste.getId());

        Consulta resultado = captor.getValue();

        Assertions.assertEquals(consultaTeste.getId(), resultado.getId());
        ConsultaValidator.validaConsultaDomain(consultaTeste, resultado);
    }

    @Test
    void testeExceptionConsultaNaoEncontradaEmFinalizarConsulta() {
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(gateway.salvar(Mockito.any())).thenReturn(consultaTeste);

        ConsultaNaoEncontradaException exception = Assertions.assertThrows(ConsultaNaoEncontradaException.class,
                () -> useCase.finalizar(consultaTeste.getId()));

        Assertions.assertEquals(exception.getMessage(), useCase.MENSAGEM_CONSULTA_NAO_ENCONTRADA);
    }

    @Test
    void testeConsultarPorId() {
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(consultaTeste));

        Consulta resultado = useCase.consultarPorId(consultaTeste.getId());

        Assertions.assertEquals(resultado.getId(), consultaTeste.getId());
        ConsultaValidator.validaConsultaDomain(consultaTeste, resultado);
    }

    @Test
    void testeExceptionConsultaNaoEncontrada() {
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.empty());

        ConsultaNaoEncontradaException exception = Assertions.assertThrows(
                ConsultaNaoEncontradaException.class,
                () -> useCase.consultarPorId(consultaTeste.getId()));

        Assertions.assertEquals(useCase.MENSAGEM_CONSULTA_NAO_ENCONTRADA, exception.getMessage());
    }
}