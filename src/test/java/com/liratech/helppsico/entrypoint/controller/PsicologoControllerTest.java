package com.liratech.helppsico.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.liratech.helppsico.application.usecases.PsicologoUseCase;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import com.liratech.helppsico.entrypoint.mapper.PsicologoMapper;
import com.liratech.helppsico.infrastructure.mapper.PsicologoMapper;
import com.liratech.helppsico.infrastructure.repositories.PsicologoRepository;
import com.liratech.helppsico.validators.PsicologoValidator;
import com.liratech.helppsico.validators.json.PsicologoValidatorJson;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RequiredArgsConstructor
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PsicologoControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final PsicologoUseCase useCase;
    private final PsicologoMapper mapperEntry;
    private final PsicologoMapper mapperInfra;

    @MockitoSpyBean
    private PsicologoRepository repository;

    private PsicologoDto psicologoDtoEntrada;
    private Psicologo psicologoDomain;

    @BeforeEach
    void inicializacaoAtributos(){
        this.psicologoDtoEntrada = PsicologoBuilder.criarPsicologoDto();
        this.psicologoDomain = mapperEntry.paraDomain(psicologoDtoEntrada);
    }

    @Test
    void testeCadastrarPsicologoComSucesso() throws Exception {
        psicologoDtoEntrada.setId(null);
        Psicologo psicologoSalvo = mapperInfra.paraEntity(psicologoDomain);
        psicologoSalvo.setId(UUID.randomUUID());

        Mockito.when(repository.findByCrp(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(repository.save(Mockito.any())).thenReturn(mapperInfra.paraEntity(psicologoDomain));

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String psicologoJson = objectMapper.writeValueAsString(psicologoDtoEntrada);

        ResultActions resultado = mockMvc.perform(post("/psicologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(psicologoJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/psicologos/"
                        + mapperEntry.paraDto(psicologoSalvo).getId().toString()));

        PsicologoValidatorJson.validaPsicologoJson(resultado, mapperEntry.paraDto(psicologoSalvo));
    }
}
