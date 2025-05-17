package com.liratech.helppsico.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liratech.helppsico.builders.ValidacaoCrpBuilder;
import com.liratech.helppsico.infrastructure.mapper.PacienteMapper;
import com.liratech.helppsico.infrastructure.mapper.PsicologoMapper;
import com.liratech.helppsico.infrastructure.repositories.PacienteRepository;
import com.liratech.helppsico.infrastructure.repositories.PsicologoRepository;
import com.liratech.helppsico.infrastructure.repositories.ValidacaoCrpRepository;
import com.liratech.helppsico.validators.ValidacaoCrpValidator;
import com.liratech.helppsico.validators.json.ValidacaoCrpValidatorJson;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.awt.*;
import java.util.Optional;
import java.util.UUID;

import static javax.swing.UIManager.put;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
@ActiveProfiles("test")
public class ValidacaoCrpControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final ValidacaoCrpMapper mapperEntry;
    private final com.liratech.helppsico.infrastructure.mapper.ValidacaoCrpMapper mapperInfra;

    private final PacienteMapper pacienteMapper;
    private final PsicologoMapper psicologoMapper;

    @MockitoSpyBean
    private final ValidacaoCrpRepository repository;

    @MockitoSpyBean
    private final PacienteRepository pacienteRepository;

    @MockitoSpyBean
    private final PsicologoRepository psicologoRepository;

    private ValidacaoCrpDto validacaoEntrada;
    private ValidacaoCrp validacaoDomain;
    private ValidacaoCrpEntity validacaoEntity;

    public ValidacaoCrpControllerTest(MockMvc mockMvc, ObjectMapper objectMapper, ValidacaoCrpMapper mapperEntry,
                                      com.liratech.helppsico.infrastructure.mapper.ValidacaoCrpMapper mapperInfra,
                                      ValidacaoCrpRepository repository, PacienteRepository pacienteRepository,
                                      PsicologoRepository psicologoRepository, PacienteMapper pacienteMapper,
                                      PsicologoMapper psicologoMapper, PacienteMapper pacienteMapper1,
                                      PsicologoMapper psicologoMapper1, PacienteRepository pacienteRepository1,
                                      PsicologoRepository psicologoRepository1) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.mapperEntry = mapperEntry;
        this.mapperInfra = mapperInfra;
        this.repository = repository;
        this.pacienteMapper = pacienteMapper1;
        this.psicologoMapper = psicologoMapper1;
        this.pacienteRepository = pacienteRepository1;
        this.psicologoRepository = psicologoRepository1;
    }

    @BeforeEach
    void inicializarAtributos(){
        this.validacaoEntrada = ValidacaoCrpBuilder.criarValidacaoCrpDto();
        this.validacaoDomain = mapperEntry.paraDomain(validacaoEntrada);
        this.validacaoEntity = mapperInfra.paraEntity(validacaoDomain);
    }

    @Test
    void testeCriarValidacaoCrp() throws Exception{
        validacaoEntrada.setId(null);

        Mockito.when(psicologoRepository.findById(Mockito.any())).thenReturn(Optional.of(psicologoMapper.paraEntity(validacaoDomain.getPsicologo())));
        Mockito.when(repository.save(Mockito.any())).thenReturn(validacaoEntity);

        String validacaoJson = objectMapper.writeValueAsString(validacaoEntrada);

        ResultActions result = mockMvc.perform(post("/validacao-crp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validacaoJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/validacao-crp/" + validacaoDomain.getId().toString()));

        ValidacaoCrpValidatorJson.verificaValidacaoJson(result, mapperEntry.paraDto(validacaoDomain));
    }

    @Test
    void testeAprovarCrp() throws Exception{
        UUID id = validacaoDomain.getId();
        validacaoEntrada.setMotivoReprovacao(null);

        Mockito.when(psicologoRepository.findById(Mockito.any())).thenReturn(Optional.of(psicologoMapper.paraEntity(validacaoDomain.getPsicologo())));

        String validacaoJson = objectMapper.writeValueAsString(validacaoEntrada);

        ResultActions result = mockMvc.perform(put("/validacao-crp/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validacaoJson))
                .andExpect(status().isOk());

        ValidacaoCrpValidatorJson.verificaValidacaoJson(result, mapper.paraDto(validacaoDomain));
    }

    @Test
    void testeReprovarCrp() throws Exception{
        UUID id = validacaoDomain.getId();

        String motivoReprovacao = "teste para recusar";
        validacaoEntrada.setMotivoReprovacao(motivoReprovacao);

        Mockito.when(psicologoRepository.findById(Mockito.any())).thenReturn(Optional.of(psicologoMapper.paraEntity(validacaoDomain.getPsicologo())));

        String validacaoJson = objectMapper.writeValueAsString(validacaoEntrada);

        ResultActions result = mockMvc.perform(put("/validacao-crp/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validacaoJson))
                .andExpect(status().isOk());

        Mockito.when(psicologoRepository.save(Mockito.any())).thenReturn(psicoloEntity);

        ValidacaoCrpValidatorJson.verificaValidacaoJson(result, mapper.paraDto(validacaoDomain));
    }

    @Test
    void testeBuscarValidacaoCrpPorId() throws Exception{
        UUID idRequest = validacaoDomain.getId();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(validacaoEntity));

        ResultActions resultActions = mockMvc.perform(get("/validacao-crp/{id}", idRequest))
                .andExpect(status().isOk());

        ValidacaoCrpValidatorJson.verificaValidacaoJson(resultActions, validacaoEntrada);
    }

    @Test
    void testeListarValidacaoCrp() throws Exception {
        int page = 0;
        int size = 10;
        String sort = "nome,asc";

        Pageable pageable = PageRequest.of(page,size);

        Page<ValidacaoCrp> paginaDomain = ValidacaoCrpBuilder.criarValidacaoCrp();
        Page<ValidacaCrpEntity> paginaEntity = paginaDomain.map(mapperInfra::paraEntity);

        Mockito.when(repository.findAll(pageable)).thenReturn(paginaEntity);

        ResultActions resultado = mockMvc.perform(get("/validacao-crp")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("sort", sort))
                .andExpect(status().isOk());

        ValidacaoCrpValidator.validaPageResponse(resultado);
    }
}