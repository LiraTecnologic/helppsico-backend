package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.gateways.SolicitacaoDocumentoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SolicitacaoDocumentoUseCaseTest {

    @Mock
    private PsicologoUseCase psicologoUseCase;

    @Mock
    private PacienteUseCase pacienteUseCase;

    @Mock
    private SolicitacaoDocumentoGateway gateway;

    @InjectMocks
    private SolicitacaoDocumentoUseCase useCase;

    @Test
    void testeCriarSolicitacao() {
    }

    @Test
    void testeErroCriarSolicitacao() {
    }

    @Test
    void testeBuscarSolicitacaoPorId() {
    }

    @Test
    void testeErroBuscarSolicitacaoPorId() {
    }

    @Test
    void testeDeletarSolicitacao() {
    }

    @Test
    void testeErroDeletarSolicitacao() {
    }
}