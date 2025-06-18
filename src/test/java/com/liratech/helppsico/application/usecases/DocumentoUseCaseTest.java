package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.TipoDocumentoInvalidoException;
import com.liratech.helppsico.application.gateways.DocumentoGateway;
import com.liratech.helppsico.application.usecases.dto.DadosGeraisDocumentoDto;
import com.liratech.helppsico.builders.DadosGeraisDocumentoBuilder;
import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.builders.SolicitacaoDocumentoBuilder;
import com.liratech.helppsico.domain.documento.*;
import com.liratech.helppsico.entrypoint.mapper.PacienteMapper;
import com.liratech.helppsico.entrypoint.mapper.PsicologoMapper;
import com.liratech.helppsico.validators.DocumentoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@AllArgsConstructor
class DocumentoUseCaseTest {

    @Mock
    private SolicitacaoDocumentoUseCase solicitacaoDocumentoUseCase;

    @Mock
    private DocumentoGateway documentoGateway;

    @Mock
    private PsicologoUseCase psicologoUseCase;

    @Mock
    private PacienteUseCase pacienteUseCase;

    @InjectMocks
    private DocumentoUseCase documentoUseCase;

    @Captor
    private ArgumentCaptor<Documento> captor;

    private DadosGeraisDocumentoDto dadosGeraisDocumentoDto;
    private UUID idSolicitacao;
    private SolicitacaoDocumento solicitacaoDocumento;
    private Atestado atestado;
    private Declaracao declaracao;
    private RelatorioPsicologico relatorioPsicologico;
    private LaudoPsicologico laudoPsicologico;
    private ParecerPsicologico parecerPsicologico;
    private DocumentoBuilder builder;
    private PsicologoMapper psicologoMapper;
    private PacienteMapper pacienteMapper;
    private Page<Documento> documentoPage;

    @BeforeEach()
    void inicializarAtributos(){
        dadosGeraisDocumentoDto = DadosGeraisDocumentoBuilder.criarDadosGeraisDocumentos();
        solicitacaoDocumento = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumento();
        idSolicitacao = solicitacaoDocumento.getId();
        documentoPage = DocumentoBuilder.criarPageDeDocumento();
    }

    @Test
    void testeSalvarAtestado() throws TipoDocumentoInvalidoException {
        atestado = builder.criarAtestadoDadosGerais(dadosGeraisDocumentoDto);

        Mockito.when(solicitacaoDocumentoUseCase.buscarPorId(Mockito.any())).thenReturn(solicitacaoDocumento);
        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(pacienteMapper.paraDomain(dadosGeraisDocumentoDto.getPaciente()));
        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(psicologoMapper.paraDomain(dadosGeraisDocumentoDto.getPsicologo()));
        Mockito.when(documentoGateway.salvar(captor.capture())).thenReturn(atestado);
        Mockito.doNothing().when(solicitacaoDocumentoUseCase).deletar(Mockito.any());

        documentoUseCase.salvar(idSolicitacao, dadosGeraisDocumentoDto);
        Documento documentoSalvo = captor.getValue();
        Atestado atestadoSalvo = (Atestado) documentoSalvo;

        Assertions.assertNotNull(atestadoSalvo.getId());
        DocumentoValidator.validaDocumentoDomain(atestado, atestadoSalvo);
        DocumentoValidator.validaAtestado(atestado, atestadoSalvo);
    }

    @Test
    void testeSalvarDeclaracao() throws TipoDocumentoInvalidoException {
        declaracao = builder.criarDeclaracaoDadosGerais(dadosGeraisDocumentoDto);

        Mockito.when(solicitacaoDocumentoUseCase.buscarPorId(Mockito.any())).thenReturn(solicitacaoDocumento);
        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(pacienteMapper.paraDomain(dadosGeraisDocumentoDto.getPaciente()));
        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(psicologoMapper.paraDomain(dadosGeraisDocumentoDto.getPsicologo()));
        Mockito.when(documentoGateway.salvar(captor.capture())).thenReturn(declaracao);
        Mockito.doNothing().when(solicitacaoDocumentoUseCase).deletar(Mockito.any());

        documentoUseCase.salvar(idSolicitacao, dadosGeraisDocumentoDto);
        Documento documentoSalvo = captor.getValue();
        Declaracao declaracaoSalvo = (Declaracao) documentoSalvo;

        Assertions.assertNotNull(documentoSalvo.getId());
        DocumentoValidator.validaDocumentoDomain(declaracao, declaracaoSalvo);
        DocumentoValidator.validaDeclaracao(declaracao, declaracaoSalvo);
    }

    @Test
    void testeSalvarLaudoPsicologico() throws TipoDocumentoInvalidoException {
        laudoPsicologico = builder.criarLaudoPsicologicoDadosGerais(dadosGeraisDocumentoDto);

        Mockito.when(solicitacaoDocumentoUseCase.buscarPorId(Mockito.any())).thenReturn(solicitacaoDocumento);
        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(pacienteMapper.paraDomain(dadosGeraisDocumentoDto.getPaciente()));
        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(psicologoMapper.paraDomain(dadosGeraisDocumentoDto.getPsicologo()));
        Mockito.when(documentoGateway.salvar(captor.capture())).thenReturn(laudoPsicologico);
        Mockito.doNothing().when(solicitacaoDocumentoUseCase).deletar(Mockito.any());

        documentoUseCase.salvar(idSolicitacao, dadosGeraisDocumentoDto);
        Documento documentoSalvo = captor.getValue();
        LaudoPsicologico laudoPsicologicoSalvo = (LaudoPsicologico) documentoSalvo;

        Assertions.assertNotNull(documentoSalvo.getId());
        DocumentoValidator.validaDocumentoDomain(laudoPsicologico, laudoPsicologicoSalvo);
        DocumentoValidator.validaLaudoPsicologico(laudoPsicologico, laudoPsicologicoSalvo);
    }

    @Test
    void testeSalvarParecerPsicologico() throws TipoDocumentoInvalidoException {
        parecerPsicologico = builder.criarParecerPsicologicoDadosGerais(dadosGeraisDocumentoDto);

        Mockito.when(solicitacaoDocumentoUseCase.buscarPorId(Mockito.any())).thenReturn(solicitacaoDocumento);
        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(pacienteMapper.paraDomain(dadosGeraisDocumentoDto.getPaciente()));
        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(psicologoMapper.paraDomain(dadosGeraisDocumentoDto.getPsicologo()));
        Mockito.when(documentoGateway.salvar(captor.capture())).thenReturn(parecerPsicologico);
        Mockito.doNothing().when(solicitacaoDocumentoUseCase).deletar(Mockito.any());

        documentoUseCase.salvar(idSolicitacao, dadosGeraisDocumentoDto);
        Documento documentoSalvo = captor.getValue();
        ParecerPsicologico parecerPsicologicoSalvo = (ParecerPsicologico) documentoSalvo;

        Assertions.assertNotNull(documentoSalvo.getId());
        DocumentoValidator.validaDocumentoDomain(parecerPsicologico, parecerPsicologicoSalvo);
        DocumentoValidator.validaParecerPsicologico(parecerPsicologico, parecerPsicologicoSalvo);
    }

    @Test
    void testeSalvarRelatorioPsicologico() throws TipoDocumentoInvalidoException {
        relatorioPsicologico = builder.criarRelatorioPsicologicoDadosGerais(dadosGeraisDocumentoDto);

        Mockito.when(solicitacaoDocumentoUseCase.buscarPorId(Mockito.any())).thenReturn(solicitacaoDocumento);
        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(pacienteMapper.paraDomain(dadosGeraisDocumentoDto.getPaciente()));
        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(psicologoMapper.paraDomain(dadosGeraisDocumentoDto.getPsicologo()));
        Mockito.when(documentoGateway.salvar(captor.capture())).thenReturn(relatorioPsicologico);
        Mockito.doNothing().when(solicitacaoDocumentoUseCase).deletar(Mockito.any());

        documentoUseCase.salvar(idSolicitacao, dadosGeraisDocumentoDto);
        Documento documentoSalvo = captor.getValue();
        RelatorioPsicologico relatorioPsicologicoSalvo = (RelatorioPsicologico) documentoSalvo;

        Assertions.assertNotNull(documentoSalvo.getId());
        DocumentoValidator.validaDocumentoDomain(relatorioPsicologico, relatorioPsicologicoSalvo);
        DocumentoValidator.validaRelatorioPsicologico(relatorioPsicologico, relatorioPsicologicoSalvo);
    }

    @Test
    void testeListarPorPaciente() {
        atestado = DocumentoBuilder.criarAtestado();

        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(pacienteMapper.paraDomain(dadosGeraisDocumentoDto.getPaciente()));
        Mockito.when(documentoGateway.listarPorPaciente(Mockito.any(), Mockito.any())).thenReturn(documentoPage);

        Page<Documento> resultado = documentoUseCase.listarPorPaciente(dadosGeraisDocumentoDto.getPaciente().getId(), PageRequest.of(0,10));

        resultado.forEach(documento -> {
            DocumentoValidator.validaDocumentoDomain(atestado, documento);
            DocumentoValidator.validaAtestado(atestado, (Atestado) documento);
        });
    }
}
