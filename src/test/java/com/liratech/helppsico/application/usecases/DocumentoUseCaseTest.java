package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.gateways.DocumentoGateway;
import com.liratech.helppsico.application.usecases.dto.DadosGeraisDocumentoDto;
import com.liratech.helppsico.builders.DadosGeraisDocumentoBuilder;
import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.builders.SolicitacaoDocumentoBuilder;
import com.liratech.helppsico.domain.documento.*;
import com.liratech.helppsico.validators.DocumentoValidator;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class DocumentoUseCaseTest {

    @Mock
    private SolicitacaoDocumentoUseCase solicitacaoDocumentoUseCase;

    @Mock
    private DocumentoGateway documentoGateway;

    @InjectMocks
    private DocumentoUseCase documentoUseCase;

    @Captor
    private ArgumentCaptor<Documento> captor;

    private DadosGeraisDocumentoDto dadosGeraisDocumentoDto;
    private UUID idSolicitacao;
    private SolicitacaoDocumento solicitacaoDocumento;
    private Atestado atestado;
    private Declaracao declaracao;
    private RelatorioPsicologo relatorioPsicologo;
    private LaudoPsicologo laudoPsicologo;
    private ParecerPsicologo parecerPsicologo;

    @BeforeEach()
    void inicializarAtributos(){
        dadosGeraisDocumentoDto = DadosGeraisDocumentoBuilder.criarDadosGeraisDocumentos();
        solicitacaoDocumento = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumento();
        idSolicitacao = solicitacaoDocumento.getId();
    }

    @Test
    void testeSalvarAtestado() {
        atestado = DocumentoBuilder.criarAtestadoDadosGerais(dadosGeraisDocumentoDto);

        Mockito.when(solicitacaoDocumentoUseCase.buscarPorId(Mockito.any())).thenReturn(solicitacaoDocumento);
        Mockito.when(documentoGateway.salvar(captor.capture())).thenReturn(atestado);

        documentoUseCase.salvar(idSolicitacao, dadosGeraisDocumentoDto);
        Documento documentoSalvo = captor.getValue();

        Assertions.assertNotNull(documentoSalvo.getId());
        DocumentoValidator.validaAtestadoDomain(atestado, documentoSalvo);
    }
}
