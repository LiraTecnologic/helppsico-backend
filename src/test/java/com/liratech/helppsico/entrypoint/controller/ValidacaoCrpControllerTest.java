package com.liratech.helppsico.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liratech.helppsico.builders.ValidacaoCrpBuilder;
import com.liratech.helppsico.domain.ValidacaoCrp;
import com.liratech.helppsico.entrypoint.dto.ValidacaoCrpDto;
import com.liratech.helppsico.entrypoint.mapper.ValidacaoCrpMapper;
import com.liratech.helppsico.infrastructure.mapper.PacienteMapperInfra;
import com.liratech.helppsico.infrastructure.mapper.PsicologoMapperInfra;
import com.liratech.helppsico.infrastructure.mapper.ValidacaoCrpMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.PacienteRepository;
import com.liratech.helppsico.infrastructure.repositories.PsicologoRepository;
import com.liratech.helppsico.infrastructure.repositories.ValidacaoCrpRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.ValidacaoCrpEntity;
import com.liratech.helppsico.validators.ValidacaoCrpValidator;
import com.liratech.helppsico.validators.json.ValidacaoCrpValidatorJson;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AllArgsConstructor
@ActiveProfiles("test")
public class ValidacaoCrpControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private ValidacaoCrpMapper mapperEntry;
    private ValidacaoCrpMapperInfra mapperInfra;

    private PacienteMapperInfra pacienteMapper;
    private PsicologoMapperInfra psicologoMapper;

    @MockitoSpyBean
    private ValidacaoCrpRepository repository;

    @MockitoSpyBean
    private PacienteRepository pacienteRepository;

    @MockitoSpyBean
    private PsicologoRepository psicologoRepository;

    private ValidacaoCrpDto validacaoEntrada;
    private ValidacaoCrp validacaoDomain;
    private ValidacaoCrpEntity validacaoEntity;

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
        validacaoEntrada.setMotivoReprova(null);

        Mockito.when(psicologoRepository.findById(Mockito.any())).thenReturn(Optional.of(psicologoMapper.paraEntity(validacaoDomain.getPsicologo())));
        Mockito.when(psicologoRepository.save(Mockito.any())).thenReturn(psicologoMapper.paraEntity(validacaoDomain.getPsicologo()));

        String validacaoJson = objectMapper.writeValueAsString(validacaoEntrada);

        ResultActions result = mockMvc.perform(put("/validacao-crp/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validacaoJson)
                )
                .andExpect(status().isOk());

        ValidacaoCrpValidatorJson.verificaValidacaoJson(result, mapperEntry.paraDto(validacaoDomain));
    }

    @Test
    void testeReprovarCrp() throws Exception{
        UUID id = validacaoDomain.getId();

        String motivoReprovacao = "teste para recusar";
        validacaoEntrada.setMotivoReprova(motivoReprovacao);

        Mockito.when(psicologoRepository.findById(Mockito.any())).thenReturn(Optional.of(psicologoMapper.paraEntity(validacaoDomain.getPsicologo())));
        Mockito.when(psicologoRepository.save(Mockito.any())).thenReturn(Optional.of(psicologoMapper.paraEntity(validacaoDomain.getPsicologo())));

        String validacaoJson = objectMapper.writeValueAsString(validacaoEntrada);

        ResultActions result = mockMvc.perform(put("/validacao-crp/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validacaoJson))
                .andExpect(status().isOk());

        ValidacaoCrpValidatorJson.verificaValidacaoJson(result, mapperEntry.paraDto(validacaoDomain));
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

        Page<ValidacaoCrp> paginaDomain = ValidacaoCrpBuilder.criarPageValidacaoCrp();
        Page<ValidacaoCrpEntity> paginaEntity = paginaDomain.map(mapperInfra::paraEntity);

        Mockito.when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(paginaEntity);

        ResultActions resultado = mockMvc.perform(get("/validacao-crp")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))
                .andExpect(status().isOk());

        ValidacaoCrpValidatorJson.verificaValidacaoJson(resultado, validacaoEntrada);
    }
}