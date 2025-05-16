package com.liratech.helppsico.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.liratech.helppsico.builders.HorarioPsicologoBuilder;
import com.liratech.helppsico.domain.HorarioPsicologo;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioPsicologoDto;
import com.liratech.helppsico.entrypoint.mapper.HorarioPsicologoMapper;
import com.liratech.helppsico.infrastructure.mapper.PsicologoMapper;
import com.liratech.helppsico.infrastructure.repositories.HorarioPsicologoRepository;
import com.liratech.helppsico.infrastructure.repositories.PsicologoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.HorarioPsicologoEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import com.liratech.helppsico.validators.json.HorarioPsicologoValidatorJson;
import lombok.RequiredArgsConstructor;
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
@ActiveProfiles("test")
@RequiredArgsConstructor
class HorarioPsicologoControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final HorarioPsicologoMapper mapperEntry;
    private final com.liratech.helppsico.infrastructure.mapper.HorarioPsicologoMapper mapperInfra;
    private final PsicologoMapper psicologoMapper;

    @MockitoSpyBean
    private HorarioPsicologoRepository repository;

    @MockitoSpyBean
    private PsicologoRepository psicologoRepository;

    private Psicologo psicologoDomain;
    private PsicologoEntity psicologoEntity;
    private HorarioPsicologo horarioPsicologoDomain;
    private HorarioPsicologoEntity horarioPsicologoEntity;
    private HorarioPsicologoDto horarioPsicologoDtoEntrada;
    private Page<HorarioPsicologo> horarioPsicologoPage;
    private UUID idHorario;
    private UUID idPsicologo;

    @BeforeEach
    void inicializarAtributos(){
        horarioPsicologoDomain = HorarioPsicologoBuilder.criarHorarioPsicologo();
        horarioPsicologoEntity = mapperInfra.paraEntity(horarioPsicologoDomain);
        horarioPsicologoDtoEntrada = mapperEntry.paraDto(horarioPsicologoDomain);
        horarioPsicologoPage = HorarioPsicologoBuilder.criarPageDeHorarioPsicologos();

        psicologoDomain = horarioPsicologoDomain.getPsicologo();
        psicologoEntity = psicologoMapper.paraEntity(psicologoDomain);

        horarioPsicologoDtoEntrada.setId(null);

        idHorario = horarioPsicologoDomain.getId();
        idPsicologo = psicologoDomain.getId();
    }

    @Test
    void testeCadastrarHorarioPsicologo() throws Exception {
        Mockito.when(psicologoRepository.findById(Mockito.any())).thenReturn(Optional.of(psicologoEntity));
        Mockito.when(repository.save(Mockito.any())).thenReturn(horarioPsicologoEntity);

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String horarioPsicologoJson = objectMapper.writeValueAsString(horarioPsicologoDtoEntrada);

        ResultActions resultActions = mockMvc.perform(post("/horarios-psicologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(horarioPsicologoJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/horarios-psicologos/" + horarioPsicologoEntity.getId()));

        HorarioPsicologoValidatorJson.validaHorariosPsicologoJson(resultActions, mapperEntry.paraDto(horarioPsicologoDomain));
    }

    @Test
    void testeListarHorarioPorPsicologo() throws Exception {
        int page = 0;
        int size = 10;
        String sort = "nome,asc";
        Pageable pageable = PageRequest.of(page,size);

        Mockito.when(repository.buscarPorPsicologo(Mockito.any(), Mockito.any())).thenReturn(horarioPsicologoPage.map(mapperInfra::paraEntity));

        ResultActions resultActions = mockMvc.perform(get("/horarios-psicologos/psicologo/{id}", idPsicologo)
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("sort", sort))
                .andExpect(status().isOk());

        HorarioPsicologoValidatorJson.validaPageResponse(resultActions, mapperEntry.paraDto(horarioPsicologoDomain));
    }

    @Test
    void testeConsultarHorarioPorId() throws Exception{
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(horarioPsicologoEntity));

        ResultActions resultActions = mockMvc.perform(get("/horarios-psicologos/{id}", idHorario))
                .andExpect(status().isOk());

        HorarioPsicologoValidatorJson.validaHorariosPsicologoJson(resultActions, mapperEntry.paraDto(horarioPsicologoDomain));
    }

    @Test
    void testeAlterarHorario() throws Exception{
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(horarioPsicologoEntity));
        Mockito.when(repository.save(Mockito.any())).thenReturn(horarioPsicologoEntity);

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String horarioPsicologoJson = objectMapper.writeValueAsString(horarioPsicologoDtoEntrada);

        ResultActions resultActions = mockMvc.perform(put("/horarios-psicologos/{id}", idHorario)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(horarioPsicologoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(idHorario.toString()));

        HorarioPsicologoValidatorJson.validaHorariosPsicologoJson(resultActions, mapperEntry.paraDto(horarioPsicologoDomain));
    }

    @Test
    void testeDeletarHorarioPsicologo() throws Exception{
            Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(horarioPsicologoEntity));

            mockMvc.perform(delete("/horarios-psicologos/{id}", idHorario))
                    .andExpect(status().isNoContent());
    }
}