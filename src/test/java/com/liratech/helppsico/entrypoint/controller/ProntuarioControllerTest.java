package com.seu.pacote.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liratech.helppsico.builders.ProntuarioBuilder;
import com.liratech.helppsico.entrypoint.dto.psicologo.ProntuarioDto;
import com.liratech.helppsico.infrastructure.repositories.ProntuarioRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.ProntuarioEntity;
import com.liratech.helppsico.validators.json.ProntuarioValidatorJson;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProntuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoSpyBean
    private ProntuarioRepository repository;

    private ProntuarioDto prontuarioDto;
    private ProntuarioEntity prontuario;
    private Page<ProntuarioEntity> pageProntuarios;
    private UUID prontuarioId;

    @BeforeEach
    void setUp() {
        prontuarioDto = ProntuarioBuilder.criarProntuarioDto();
        prontuario = ProntuarioBuilder.criarProntuarioEntity();
        prontuarioId = prontuarioDto.getId();
        pageProntuarios = ProntuarioBuilder.criarPageProntuarioEntity();
    }

    @Test
    @DisplayName("Deve registrar um novo prontuário com sucesso")
    void deveRegistrarNovoProntuario() throws Exception {
        when(repository.save(Mockito.any())).thenReturn(prontuario);

        ResultActions resultActions = mockMvc.perform(post("/prontuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prontuarioDto)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/prontuarios/" + prontuarioId));

        ProntuarioValidatorJson.validaProntuarioJson(resultActions, prontuarioDto);

    }

    @Test
    @DisplayName("Deve listar prontuários por paciente")
    void deveListarProntuariosPorPaciente() throws Exception {
        when(repository.listarPorPaciente(Mockito.any(), Mockito.any())).thenReturn(pageProntuarios);

        ResultActions resultActions = mockMvc.perform(get("/prontuarios/paciente")
                        .param("idPaciente", prontuarioDto.getPaciente().getId().toString())
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "descricao,asc"))
                .andExpect(status().isOk());

        ProntuarioValidatorJson.validaProntuariosJson(resultActions);
    }

    @Test
    @DisplayName("Deve listar prontuários por psicólogo")
    void deveListarProntuariosPorPsicologo() throws Exception {
        when(repository.listarPorPsicologo(Mockito.any())).thenReturn(pageProntuarios);

        ResultActions resultActions = mockMvc.perform(get("/prontuarios/psicologo")
                        .param("idPsicologo", prontuario.getPsicologo().getId().toString())
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "descricao,asc"))
                .andExpect(status().isOk());

        ProntuarioValidatorJson.validaProntuariosJson(resultActions);
    }

    @Test
    @DisplayName("Deve alterar um prontuário existente")
    void deveAlterarProntuarioExistente() throws Exception {

        when(repository.findById(Mockito.any())).thenReturn(Optional.of(prontuario));
        when(repository.save(Mockito.any())).thenReturn(prontuario);


        ResultActions resultActions = mockMvc.perform(put("/prontuarios/{id}", prontuarioId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prontuarioDto)))
                .andExpect(status().isOk());

        ProntuarioValidatorJson.validaProntuarioJson(resultActions, prontuarioDto);
    }

    @Test
    @DisplayName("Deve alterar parcialmente um prontuário existente")
    void deveAlterarParcialmenteProntuarioExistente() throws Exception {
        when(repository.findById(Mockito.any())).thenReturn(Optional.of(prontuario));
        when(repository.save(Mockito.any())).thenReturn(prontuario);

        prontuarioDto.setConteudo("Nova descrição");

        Map<String, Object> campos = new HashMap<>();

        campos.put("titulo", prontuarioDto);

        ResultActions resultActions = mockMvc.perform(patch("/prontuarios/{id}", prontuarioId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(campos)))
                .andExpect(status().isOk());

        ProntuarioValidatorJson.validaProntuarioJson(resultActions, prontuarioDto);
    }
}