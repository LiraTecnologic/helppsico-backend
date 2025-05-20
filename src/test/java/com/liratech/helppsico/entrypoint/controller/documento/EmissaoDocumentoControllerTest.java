package com.liratech.helppsico.entrypoint.controller.documento;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.liratech.helppsico.application.usecases.dto.DadosGeraisDocumentoDto;
import com.liratech.helppsico.builders.DadosGeraisDocumentoBuilder;
import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.builders.SolicitacaoDocumentoBuilder;
import com.liratech.helppsico.domain.documento.*;
import com.liratech.helppsico.entrypoint.mapper.DocumentoMapper;
import com.liratech.helppsico.infrastructure.repositories.DocumentoRepository;
import com.liratech.helppsico.infrastructure.repositories.SolicitacaoDocumentoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.SolicitacaoDocumentoEntity;
import com.liratech.helppsico.validators.json.DocumentoValidatorJson;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.xml.stream.Location;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AllArgsConstructor
public class EmissaoDocumentoControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockitoSpyBean
    private SolicitacaoDocumentoRepository solicitacaoDocumentoRepository;

    @MockitoSpyBean
    private DocumentoRepository repository;

    private final DocumentoMapper mapperEntry;
    private final com.liratech.helppsico.infrastructure.mapper.DocumentoMapper mapperInfra;
    private DadosGeraisDocumentoDto dadosGeraisTeste;
    private SolicitacaoDocumentoEntity solicitacaoDocumentoRetorno;
    private Documento documentoRetorno;
    private UUID idSolicitacao;


    @BeforeEach
    void inicializarAtributo(){
        dadosGeraisTeste = DadosGeraisDocumentoBuilder.criarDadosGeraisDocumentos();
        solicitacaoDocumentoRetorno = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumentoEntity();
        idSolicitacao = solicitacaoDocumentoRetorno.getId();
    }

    @Test
    void testeEmitirAtestado() throws Exception{
        documentoRetorno = DocumentoBuilder.criarAtestadoDadosGerais(dadosGeraisTeste);

        Mockito.when(solicitacaoDocumentoRepository.findById(Mockito.any())).thenReturn(Optional.of(solicitacaoDocumentoRetorno));
        Mockito.when(repository.save(Mockito.any())).thenReturn(mapperInfra.paraEntity(documentoRetorno));

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String dadosGeraisJson = objectMapper.writeValueAsString(dadosGeraisTeste);

        ResultActions resultActions = mockMvc.perform(post("/documentos/{id}", idSolicitacao)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosGeraisJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/documentos/" + idSolicitacao));

        DocumentoValidatorJson.validaDocumentoJson(resultActions, documentoRetorno);
        DocumentoValidatorJson.validaAtestadoJson(resultActions, (Atestado) documentoRetorno);
    }

    @Test
    void testeEmitirDeclaracao() throws Exception{
        documentoRetorno = DocumentoBuilder.criarDeclaracaoDadosGerais(dadosGeraisTeste);
        solicitacaoDocumentoRetorno.setTipoDocumento(TipoDocumento.DECLARACAO);

        Mockito.when(solicitacaoDocumentoRepository.findById(Mockito.any())).thenReturn(Optional.of(solicitacaoDocumentoRetorno));
        Mockito.when(repository.save(Mockito.any())).thenReturn(mapperInfra.paraEntity(documentoRetorno));

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String dadosGeraisJson = objectMapper.writeValueAsString(dadosGeraisTeste);

        ResultActions resultActions = mockMvc.perform(post("/documentos/{id}", idSolicitacao)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosGeraisJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/documentos/" + idSolicitacao));

        DocumentoValidatorJson.validaDocumentoJson(resultActions, documentoRetorno);
        DocumentoValidatorJson.validaDeclaracaoJson(resultActions, (Declaracao) documentoRetorno);
    }

    @Test
    void testeEmitirLaudoPsicologico() throws Exception{
        documentoRetorno = DocumentoBuilder.criarLaudoPsicologicoDadosGerais(dadosGeraisTeste);
        solicitacaoDocumentoRetorno.setTipoDocumento(TipoDocumento.LAUDO_PSICOLOGICO);

        Mockito.when(solicitacaoDocumentoRepository.findById(Mockito.any())).thenReturn(Optional.of(solicitacaoDocumentoRetorno));
        Mockito.when(repository.save(Mockito.any())).thenReturn(mapperInfra.paraEntity(documentoRetorno));

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String dadosGeraisJson = objectMapper.writeValueAsString(dadosGeraisTeste);

        ResultActions resultActions = mockMvc.perform(post("/documentos/{id}", idSolicitacao)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosGeraisJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/documentos/" + idSolicitacao));

        DocumentoValidatorJson.validaDocumentoJson(resultActions, documentoRetorno);
        DocumentoValidatorJson.validaLaudoPsicologicoJson(resultActions, (LaudoPsicologico) documentoRetorno);
    }

    @Test
    void testeEmitirRelatorioPsicologico() throws Exception{
        documentoRetorno = DocumentoBuilder.criarRelatorioPsicologicoDadosGerais(dadosGeraisTeste);
        solicitacaoDocumentoRetorno.setTipoDocumento(TipoDocumento.RELATORIO_PSICOLOGICO);

        Mockito.when(solicitacaoDocumentoRepository.findById(Mockito.any())).thenReturn(Optional.of(solicitacaoDocumentoRetorno));
        Mockito.when(repository.save(Mockito.any())).thenReturn(mapperInfra.paraEntity(documentoRetorno));

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String dadosGeraisJson = objectMapper.writeValueAsString(dadosGeraisTeste);

        ResultActions resultActions = mockMvc.perform(post("/documentos/{id}", idSolicitacao)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosGeraisJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/documentos/" + idSolicitacao));

        DocumentoValidatorJson.validaDocumentoJson(resultActions, documentoRetorno);
        DocumentoValidatorJson.validaRelatorioPsicologicoJson(resultActions, (RelatorioPsicologico) documentoRetorno);
    }

    @Test
    void testeEmitirParecerPsicologico() throws Exception{
        documentoRetorno = DocumentoBuilder.criarParecerPsicologicoDadosGerais(dadosGeraisTeste);
        solicitacaoDocumentoRetorno.setTipoDocumento(TipoDocumento.DECLARACAO);

        Mockito.when(solicitacaoDocumentoRepository.findById(Mockito.any())).thenReturn(Optional.of(solicitacaoDocumentoRetorno));
        Mockito.when(repository.save(Mockito.any())).thenReturn(mapperInfra.paraEntity(documentoRetorno));

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String dadosGeraisJson = objectMapper.writeValueAsString(dadosGeraisTeste);

        ResultActions resultActions = mockMvc.perform(post("/documentos/{id}", idSolicitacao)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosGeraisJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/documentos/" + idSolicitacao));

        DocumentoValidatorJson.validaDocumentoJson(resultActions, documentoRetorno);
        DocumentoValidatorJson.validaParecerPsicologicoJson(resultActions, (ParecerPsicologico) documentoRetorno);
    }
}
