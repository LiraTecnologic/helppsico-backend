package com.liratech.helppsico.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.liratech.helppsico.builders.EnderecoBuilder;
import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.entrypoint.mapper.EnderecoMapper;
import com.liratech.helppsico.infrastructure.mapper.EnderecoMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.EnderecoRepository;
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

import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RequiredArgsConstructor
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class EnderecoControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final EnderecoMapperInfra mapperInfra;
    private final EnderecoMapper mapperEntry;

    @MockitoSpyBean
    private final EnderecoRepository repository;

    private EnderecoDto enderecoDtoEntrada;
    private Endereco enderecoDomain;

    @BeforeEach
    void inicilizarAtributos(){
        this.enderecoDtoEntrada = EnderecoBuilder.criarEnderecoDto();
        this.enderecoDomain = mapperEntry.paraDomain(enderecoDtoEntrada);
    }

    @Test
    void testeCadastrarEndereco() throws Exception {
        enderecoDtoEntrada.setId(null);

        Mockito.when(repository.save(Mockito.any())).thenReturn(mapperInfra.paraEntity(enderecoDomain));

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String enderecoJson = objectMapper.writeValueAsString(enderecoDtoEntrada);

        ResultActions resultado = mockMvc.perform(post("/enderecos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(enderecoJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/enderecos/"
                        + mapperEntry.paraDto(enderecoDomain).getId().toString()));

        EnderecoValidatorJson.validaEnderecoJson(resultado, mapperEntry.paraDto(enderecoDomain));
    }

    @Test
    void testeConsultarEnderecoPorId() throws Exception {
        UUID id = UUID.randomUUID();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(mapperInfra.paraEntity(enderecoDomain)));

        ResultActions result = mockMvc.perform(get("/enderecos/{id}", id)).andExpect(status().isOk());

        EnderecoValidatorJson.validaEnderecoJson(result, enderecoDtoEntrada);
    }
}