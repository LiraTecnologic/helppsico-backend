package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.consulta.ConsultaJaExistenteNaDataException;
import com.liratech.helppsico.application.exceptions.consulta.ConsultaNaoEncontradaException;
import com.liratech.helppsico.application.gateways.ConsultaGateway;
import com.liratech.helppsico.builders.ConsultaBuilder;
import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.infrastructure.mapper.ConsultaMapperInfra;
import com.liratech.helppsico.validators.ConsultaValidator;
import com.liratech.helppsico.validators.EnderecoValidator;
import com.liratech.helppsico.validators.PacienteValidator;
import com.liratech.helppsico.validators.PsicologoValidator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class ConsultaUseCaseTest {

    @Mock
    private PacienteUseCase pacienteUseCase;

    @Mock
    private PsicologoUseCase psicologoUseCase;

    @Mock
    private ConsultaGateway gateway;

    @Captor
    ArgumentCaptor<Consulta> captor;

    @InjectMocks
    private ConsultaUseCase useCase;

    private final ConsultaMapperInfra mapperInfra;

    private final Consulta consultaTeste = ConsultaBuilder.criarConsulta();

    private final List<Consulta> consultasNaData = List.of(
            Consulta.builder()
                    .id(UUID.randomUUID())
                    .paciente(PacienteBuilder.criarPaciente())
                    .psicologo(PsicologoBuilder.criarPsicologo())
                    .dataHora(consultaTeste.getDataHora())
                    .valor(new BigDecimal(400))
                    .finalizada(false)
                    .endereco(EnderecoBuilder.criarEndereco())
                    .build(),

            Consulta.builder()
                    .id(UUID.randomUUID())
                    .paciente(PacienteBuilder.criarPaciente())
                    .psicologo(PsicologoBuilder.criarPsicologo())
                    .dataHora(LocalDateTime.now().plusHours(3))
                    .valor(new BigDecimal(400))
                    .finalizada(false)
                    .endereco(EnderecoBuilder.criarEndereco())
                    .build(),

            Consulta.builder()
                    .id(UUID.randomUUID())
                    .paciente(PacienteBuilder.criarPaciente())
                    .psicologo(PsicologoBuilder.criarPsicologo())
                    .dataHora(LocalDateTime.now().plusHours(5))
                    .valor(new BigDecimal(400))
                    .finalizada(false)
                    .endereco(EnderecoBuilder.criarEndereco())
                    .build()
    );

    @Test
    void testaAgendamentoDeConsulta() {


        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());
        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());
        Mockito.when(gateway.consultarConsultasMesmoDia(Mockito.any())).thenReturn(new ArrayList<>());
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(consultaTeste);

        consultaTeste.setId(null);
        useCase.agendar(consultaTeste);

        Consulta resultado = captor.getValue();

        Assertions.assertNotNull(resultado.getId());
        ConsultaValidator.validaConsultaDomain(consultaTeste, resultado);
    }

    @Test
    void testaExcpetionDataNaoDisponivelAgendamentoDeConsulta() {
        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(PacienteBuilder.criarPaciente());
        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(PsicologoBuilder.criarPsicologo());
        Mockito.when(gateway.consultarConsultasMesmoDia(Mockito.any())).thenReturn(consultasNaData);
        Mockito.when(gateway.salvar(Mockito.any())).thenReturn(consultaTeste);

        ConsultaJaExistenteNaDataException exception = Assertions.assertThrows(ConsultaJaExistenteNaDataException.class,
                () -> useCase.agendar(consultaTeste));

        Assertions.assertEquals(exception.getMessage(), useCase.MENSAGEM_CONSULTA_JA_EXISTENTE_NA_DATA);
    }

    @Test
    void testeCancelarConsulta() {
        Mockito.doNothing().when(gateway).deletar(Mockito.any());
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(consultaTeste));

        useCase.cancelar(consultaTeste.getId());

        Mockito.verify(gateway, Mockito.times(1)).deletar(consultaTeste.getId());
    }

    @Test
    void testeExceptionConsultaNaoEncontradaCancelarConsulta() {
        Mockito.doNothing().when(gateway).deletar(Mockito.any());
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.empty());

        ConsultaNaoEncontradaException exception = Assertions.assertThrows(ConsultaNaoEncontradaException.class,
                () -> useCase.cancelar(consultaTeste.getId()));

        Assertions.assertEquals(exception.getMessage(), useCase.MENSAGEM_CONSULTA_NAO_ENCONTRADA);
    }

    @Test
    void testeConsultarConsultasFuturas() {
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(gateway.consultarConsultasFuturas(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(ConsultaBuilder.criarPageConsultaEntity().map(mapperInfra::paraDomain));

        Page<Consulta> resultado = useCase.consultarConsultasFuturas(PacienteBuilder.criarPaciente().getId(),
                PsicologoBuilder.criarPsicologo().getId(), pageable);

        resultado.forEach(consulta -> ConsultaValidator.validaConsultaDomain(consulta, mapperInfra.paraDomain(ConsultaBuilder.criarConsultaEntity())));
    }

    @Test
    void testeConsultarHistorico() {
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(gateway.consultarHistorico(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(ConsultaBuilder.criarPageConsultaEntity().map(mapperInfra::paraDomain));

        Page<Consulta> resultado = useCase.consultarHistorico(PacienteBuilder.criarPaciente().getId(),
                PsicologoBuilder.criarPsicologo().getId(), pageable);

        resultado.forEach(consulta -> ConsultaValidator.validaConsultaDomain(consulta, mapperInfra.paraDomain(ConsultaBuilder.criarConsultaEntity())));
    }

    @Test
    void testeAlteraracaoDataConsulta() {
        LocalDateTime dataTeste = LocalDateTime.now().plusDays(3);

        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(consultaTeste));
        Mockito.when(gateway.consultarConsultasMesmoDia(Mockito.any())).thenReturn(new ArrayList<>());
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(consultaTeste);

        useCase.alterarData(consultaTeste.getId(), dataTeste);

        Consulta resultado = captor.getValue();

        Assertions.assertEquals(consultaTeste.getId(), resultado.getId());
        PsicologoValidator.validaPsicologoDomain(consultaTeste.getPsicologo(), resultado.getPsicologo());
        PacienteValidator.validaPacienteDomain(consultaTeste.getPaciente(), resultado.getPaciente());
        Assertions.assertNotEquals(consultaTeste.getDataHora(), resultado.getDataHora());
        EnderecoValidator.validaEnderecoDomain(consultaTeste.getEndereco(), resultado.getEndereco());
        Assertions.assertEquals(consultaTeste.getFinalizada(), resultado.getFinalizada());
    }

    @Test
    void testeExceptionConsultaNaoEncontradaEmAlteracaoDeData() {
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(gateway.consultarConsultasMesmoDia(Mockito.any())).thenReturn(new ArrayList<>());
        Mockito.when(gateway.salvar(Mockito.any())).thenReturn(consultaTeste);

        ConsultaNaoEncontradaException exception = Assertions.assertThrows(ConsultaNaoEncontradaException.class,
                () -> useCase.alterarData(consultaTeste.getId(), LocalDateTime.now().plusDays(3)));

        Assertions.assertEquals(exception.getMessage(), useCase.MENSAGEM_CONSULTA_NAO_ENCONTRADA);
    }

    @Test
    void testeExceptionConsultaExistenteEmAlteracaoDeData() {
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(consultaTeste));
        Mockito.when(gateway.consultarConsultasMesmoDia(Mockito.any())).thenReturn(consultasNaData);
        Mockito.when(gateway.salvar(Mockito.any())).thenReturn(consultaTeste);

        ConsultaJaExistenteNaDataException exception = Assertions.assertThrows(ConsultaJaExistenteNaDataException.class,
                () -> useCase.alterarData(consultaTeste.getId(), consultaTeste.getDataHora()));

        Assertions.assertEquals(exception.getMessage(), useCase.MENSAGEM_CONSULTA_JA_EXISTENTE_NA_DATA);
    }

    @Test
    void testeFinalizarConsulta() {
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(consultaTeste));
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(consultaTeste);

        useCase.finalizar(consultaTeste.getId());

        Consulta resultado = captor.getValue();

        Assertions.assertEquals(consultaTeste.getId(), resultado.getId());
        PsicologoValidator.validaPsicologoDomain(consultaTeste.getPsicologo(), resultado.getPsicologo());
        PacienteValidator.validaPacienteDomain(consultaTeste.getPaciente(), resultado.getPaciente());
        Assertions.assertEquals(consultaTeste.getDataHora(), resultado.getDataHora());
        EnderecoValidator.validaEnderecoDomain(consultaTeste.getEndereco(), resultado.getEndereco());
        Assertions.assertTrue(resultado.getFinalizada());
    }

    @Test
    void testeExceptionConsultaNaoEncontradaEmFinalizarConsulta() {
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(gateway.salvar(Mockito.any())).thenReturn(consultaTeste);

        ConsultaNaoEncontradaException exception = Assertions.assertThrows(ConsultaNaoEncontradaException.class,
                () -> useCase.finalizar(consultaTeste.getId()));

        Assertions.assertEquals(exception.getMessage(), useCase.MENSAGEM_CONSULTA_NAO_ENCONTRADA);
    }
}