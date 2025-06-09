package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.solicitacaoDocumento.SolicitacaoDocumentoNaoEncontradoException;
import com.liratech.helppsico.application.gateways.SolicitacaoDocumentoGateway;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.builders.SolicitacaoDocumentoBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import com.liratech.helppsico.validators.SolicitacaoDocumentoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.liratech.helppsico.application.usecases.SolicitacaoDocumentoUseCase.MENSAGEM_SOLICITACAO_DOCUMENTO_NAO_ENCONTRADO;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SolicitacaoDocumentoUseCaseTest {

    @Mock
    private PsicologoUseCase psicologoUseCase;

    @Mock
    private PacienteUseCase pacienteUseCase;

    @Mock
    private SolicitacaoDocumentoGateway gateway;

    @Captor
    ArgumentCaptor<SolicitacaoDocumento> captor;

    @InjectMocks
    private SolicitacaoDocumentoUseCase useCase;

    @Test
    void testeCriarSolicitacao() {
        SolicitacaoDocumento solicitacaoTeste = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumento();

        Paciente pacienteTeste = PacienteBuilder.criarPaciente();
        pacienteTeste.setId(solicitacaoTeste.getPsicologo().getId());

        Psicologo psicologoTeste = PsicologoBuilder.criarPsicologo();
        psicologoTeste.setId(solicitacaoTeste.getPsicologo().getId());

        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(pacienteTeste);
        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(psicologoTeste);
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(solicitacaoTeste);

        SolicitacaoDocumento solicitacaoDocumento = useCase.criarSolicitacao(solicitacaoTeste);

        SolicitacaoDocumento solicitacaoCapturada = captor.getValue();
        SolicitacaoDocumentoValidator.validaSolicitacaoDocumentoDomain(solicitacaoDocumento, solicitacaoCapturada);
    }

    @Test
    void testeBuscarSolicitacaoPorId() {
        SolicitacaoDocumento solicitacao = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumento();
        UUID idSolicitacao = solicitacao.getId();

        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(solicitacao));

        SolicitacaoDocumento solicitacaoRetorno = useCase.buscarPorId(idSolicitacao);
        SolicitacaoDocumentoValidator.validaSolicitacaoDocumentoDomain(solicitacao, solicitacaoRetorno);
        Assertions.assertEquals(solicitacao.getId(), solicitacaoRetorno.getId());
    }

    @Test
    void testeErroBuscarSolicitacaoPorId() {
        UUID idSolicitacao = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumento().getId();

        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.empty());

        SolicitacaoDocumentoNaoEncontradoException ex = Assertions
                .assertThrows(SolicitacaoDocumentoNaoEncontradoException.class, () -> useCase.buscarPorId(idSolicitacao));
        Assertions.assertEquals(MENSAGEM_SOLICITACAO_DOCUMENTO_NAO_ENCONTRADO, ex.getMessage());

        Mockito.verify(gateway, Mockito.times(1)).consultarPorId(idSolicitacao);
    }

    @Test
    void testeDeletarSolicitacao() {
        SolicitacaoDocumento solicitacao = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumento();
        UUID idSolicitacao = solicitacao.getId();

        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.of(solicitacao));
        Mockito.doNothing().when(gateway).deletar(Mockito.any());

        useCase.deletar(idSolicitacao);

        Mockito.verify(gateway).consultarPorId(idSolicitacao);
        Mockito.verify(gateway).deletar(idSolicitacao);
    }
}