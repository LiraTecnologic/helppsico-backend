package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.prontuarios.ErroAtualizarCamposEspecificosExcpetion;
import com.liratech.helppsico.application.gateways.ProntuarioGateway;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.ProntuarioBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.infrastructure.mapper.ProntuarioMapperInfra;
import com.liratech.helppsico.validators.ProntuarioValidator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class ProntuarioUseCaseTest {

    @Mock
    private ProntuarioGateway gateway;

    @Mock
    private PsicologoUseCase psicologoUseCase;

    @Mock
    private PacienteUseCase pacienteUseCase;

    @InjectMocks
    private ProntuarioUseCase useCase;

    @Captor
    ArgumentCaptor<Prontuario> captor;

    private Prontuario prontuarioTeste;
    private Psicologo psicologoTeste;
    private Paciente pacienteTeste;
    private Page<Prontuario> prontuarioPage;
    private final ProntuarioMapperInfra mapper;

    @BeforeEach
    void inicializarAtributos() {
        prontuarioTeste = ProntuarioBuilder.criarProntuario();
        psicologoTeste = PsicologoBuilder.criarPsicologo();
        pacienteTeste = PacienteBuilder.criarPaciente();
        prontuarioPage = ProntuarioBuilder.criarPageProntuarioEntity().map(mapper::paraDomain);
    }


    @Test
    void testeRegistroProntuario() {
        prontuarioTeste.setId(null);

        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(psicologoTeste);
        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(pacienteTeste);
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(prontuarioTeste);

        useCase.registrar(prontuarioTeste);

        Prontuario resultado = captor.getValue();

        ProntuarioValidator.validaProntuarioDomain(resultado, prontuarioTeste);
    }

    @Test
    void testeListagemDeProntuarioPorPaciente() {
        Mockito.when(gateway.listarPorPaciente(Mockito.any(), Mockito.any())).thenReturn(prontuarioPage);

        Page<Prontuario> resultado = useCase.listarPorPaciente(pacienteTeste.getId(), PageRequest.of(0, 10));

        resultado.forEach(prontuario -> ProntuarioValidator.validaProntuarioDomain(prontuario, mapper.paraDomain(ProntuarioBuilder.criarProntuarioEntity())));
    }

    @Test
    void testeListagemDeProntuariosPorPsicologo() {
        Mockito.when(gateway.listarPorPsicologo(Mockito.any(), Mockito.any())).thenReturn(prontuarioPage);

        Page<Prontuario> resultado = useCase.listarPorPsicologo(psicologoTeste.getId(), PageRequest.of(0, 10));

        resultado.forEach(prontuario -> ProntuarioValidator.validaProntuarioDomain(prontuario, mapper.paraDomain(ProntuarioBuilder.criarProntuarioEntity())));
    }

    @Test
    void testeAlteracaoTotalDeProntuario() {
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(prontuarioTeste));
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(prontuarioTeste);

        prontuarioTeste.setTitulo("Novo titulo");
        prontuarioTeste.setConteudo("Novo conteudo");

        useCase.alterar(prontuarioTeste, prontuarioTeste.getId());

        Prontuario resultado = captor.getValue();

        ProntuarioValidator.validaProntuarioDomain(resultado, prontuarioTeste);
    }

    @Test
    void testeAlteracaoParcialDeTituloProntuario() {
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(prontuarioTeste));
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(prontuarioTeste);

        Map<String, Object> campos = new HashMap<>();

        String novoPaciente = "Novo Paciente";
        campos.put("titulo", novoPaciente);

        useCase.alterarParcial(campos, prontuarioTeste.getId());

        Prontuario prontuario = captor.getValue();

        Assertions.assertEquals(prontuario.getTitulo(), novoPaciente);
    }

    @Test
    void testeAlteracaoParcialDeDescricaoProntuario() {
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(prontuarioTeste));
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(prontuarioTeste);

        Map<String, Object> campos = new HashMap<>();

        String novoConteudo = "Nova descriçao";
        campos.put("conteudo", novoConteudo);

        useCase.alterarParcial(campos, prontuarioTeste.getId());

        Prontuario prontuario = captor.getValue();

        Assertions.assertEquals(prontuario.getConteudo(), novoConteudo);
    }

    @Test
    void testeExceptionAlteracaoParcial() {
        Map<String, Object> campos = new HashMap<>();
        campos.put("campoInvalido", "valor");

        ErroAtualizarCamposEspecificosExcpetion ex = assertThrows(
                ErroAtualizarCamposEspecificosExcpetion.class,
                () -> useCase.alterarParcial(campos, prontuarioTeste.getId())
        );

        Assertions.assertEquals(ex.getMessage(), "Erro ao atualizar campo: campoInvalido");
    }

    @Test
    void testeDelecaoProntuario() {
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(prontuarioTeste));
        Mockito.doNothing().when(gateway).deletar(Mockito.any());

        useCase.deletar(prontuarioTeste.getId());

        Mockito.verify(gateway, Mockito.times(1)).consultarPorId(prontuarioTeste.getId());
        Mockito.verify(gateway, Mockito.times(1)).deletar(prontuarioTeste.getId());
    }

    @Test
    void testeExceptionDelecao() {
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(prontuarioTeste));
        Mockito.doNothing().when(gateway).deletar(Mockito.any());

        useCase.deletar(prontuarioTeste.getId());

        Mockito.verify(gateway, Mockito.times(1)).consultarPorId(prontuarioTeste.getId());
        Mockito.verify(gateway, Mockito.times(1)).deletar(prontuarioTeste.getId());
    }
}