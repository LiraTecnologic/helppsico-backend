package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.usecases.dto.DadosGeraisDocumentoDto;
import com.liratech.helppsico.builders.DadosGeraisDocumentoBuilder;
import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.domain.documento.*;
import com.liratech.helppsico.validators.DocumentoValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@AllArgsConstructor
public class DocumentoFactoryTest {

    @InjectMocks
    private DocumentoFactory documentoFactory;

    private Documento documentoTeste;
    private DadosGeraisDocumentoDto dadosGeraisDocumentoDto;
    private DocumentoBuilder builder;

    @BeforeEach
    void inicializar() {
        dadosGeraisDocumentoDto = DadosGeraisDocumentoBuilder.criarDadosGeraisDocumentos();
    }

    @Test
    void testeCriacaoDeAtestado() {
        documentoTeste = builder.criarAtestadoDadosGerais(dadosGeraisDocumentoDto);
        TipoDocumento tipoDocumento = TipoDocumento.ATESTADO;

        Documento documentoResultado = documentoFactory.criar(dadosGeraisDocumentoDto, tipoDocumento);

        Assertions.assertEquals(documentoTeste.getId(), documentoResultado.getId());
        DocumentoValidator.validaDocumentoDomain(documentoTeste, documentoResultado);
        DocumentoValidator.validaAtestado((Atestado) documentoTeste, (Atestado) documentoResultado);
    }

    @Test
    void testeCriacaoDeDeclaracao() {
        documentoTeste = builder.criarDeclaracaoDadosGerais(dadosGeraisDocumentoDto);
        TipoDocumento tipoDocumento = TipoDocumento.DECLARACAO;

        Documento documentoResultado = documentoFactory.criar(dadosGeraisDocumentoDto, tipoDocumento);

        Assertions.assertEquals(documentoTeste.getId(), documentoResultado.getId());
        DocumentoValidator.validaDocumentoDomain(documentoTeste, documentoResultado);
        DocumentoValidator.validaDeclaracao((Declaracao) documentoTeste, (Declaracao) documentoResultado);
    }

    @Test
    void testeCriacaoDeLaudoPsicologico() {
        documentoTeste = builder.criarLaudoPsicologicoDadosGerais(dadosGeraisDocumentoDto);
        TipoDocumento tipoDocumento = TipoDocumento.LAUDO_PSICOLOGICO;

        Documento documentoResultado = documentoFactory.criar(dadosGeraisDocumentoDto, tipoDocumento);

        Assertions.assertEquals(documentoTeste.getId(), documentoResultado.getId());
        DocumentoValidator.validaDocumentoDomain(documentoTeste, documentoResultado);
        DocumentoValidator.validaLaudoPsicologico((LaudoPsicologico) documentoTeste, (LaudoPsicologico) documentoResultado);
    }

    @Test
    void testeCriacaoDeParecerPsicologico() {
        documentoTeste = builder.criarLaudoPsicologicoDadosGerais(dadosGeraisDocumentoDto);
        TipoDocumento tipoDocumento = TipoDocumento.PARECER_PSICOLOGICO;

        Documento documentoResultado = documentoFactory.criar(dadosGeraisDocumentoDto, tipoDocumento);

        Assertions.assertEquals(documentoTeste.getId(), documentoResultado.getId());
        DocumentoValidator.validaDocumentoDomain(documentoTeste, documentoResultado);
        DocumentoValidator.validaParecerPsicologico((ParecerPsicologico) documentoTeste, (ParecerPsicologico) documentoResultado);
    }

    @Test
    void testeCriacaoDeRelatorioPsicologico() {
        documentoTeste = builder.criarLaudoPsicologicoDadosGerais(dadosGeraisDocumentoDto);
        TipoDocumento tipoDocumento = TipoDocumento.RELATORIO_PSICOLOGICO;

        Documento documentoResultado = documentoFactory.criar(dadosGeraisDocumentoDto, tipoDocumento);

        Assertions.assertEquals(documentoTeste.getId(), documentoResultado.getId());
        DocumentoValidator.validaDocumentoDomain(documentoTeste, documentoResultado);
        DocumentoValidator.validaRelatorioPsicologico((RelatorioPsicologico) documentoTeste, (RelatorioPsicologico) documentoResultado);
    }
}