package com.liratech.helppsico.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.mapper.EnderecoMapper;
import com.liratech.helppsico.entrypoint.mapper.PacienteMapper;
import com.liratech.helppsico.infrastructure.mapper.PacienteMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.PacienteRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.EnderecoEntity;
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
import com.liratech.helppsico.validators.json.PacienteValidatorJson;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RequiredArgsConstructor
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PacienteControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final PacienteMapper mapperEntry;
    private final PacienteMapperInfra mapperInfra;
    private final EnderecoMapper enderecoMapper;

    @MockitoSpyBean
    private final PacienteRepository repository;

    @MockitoSpyBean
    private final EnderecoRepository repositoryEndereco;

    private PacienteDto pacienteDtoEntrada;
    private Paciente pacienteDomain;

    @BeforeEach
    void inicializacaoAtributos(){
        this.pacienteDtoEntrada = PacienteBuilder.criarPacienteDto();
        this.pacienteDomain = mapperEntry.paraDomain(pacienteDtoEntrada);
    }

    @Test
    void testeCadastrarPaciente() throws Exception {
        pacienteDtoEntrada.setId(null);
        EnderecoEntity enderecoEntity = enderecoMapper.paraEntity(pacienteDomain.getEndereco());

        Mockito.when(repository.findByEmail(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(repositoryEndereco.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(repositoryEndereco.save(Mockito.any())).thenReturn(enderecoEntity);
        Mockito.when(repository.save(Mockito.any())).thenReturn(mapperInfra.paraEntity(pacienteDomain));

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String pacienteJson = objectMapper.writeValueAsString(pacienteDtoEntrada);

        ResultActions resultado = mockMvc.perform(post("/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pacienteJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/pacientes/"
                        + mapperEntry.paraDto(pacienteDomain).getId().toString()));

        PacienteValidatorJson.validaPacienteJson(resultado, mapperEntry.paraDto(pacienteDomain));
    }

    @Test
    void testeConsultarPacientePorId() throws Exception {
        UUID idRequest = pacienteDtoEntrada.getId();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(mapperInfra.paraEntity(pacienteDomain)));

        ResultActions resultado = mockMvc.perform(get("/psicologos/{id}", idRequest))
                .andExpect(status().isOk());

        PacienteValidatorJson.validaPacienteJson(resultado, pacienteDtoEntrada);
    }
}