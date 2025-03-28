package com.liratech.helppsico.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.net.URI;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(PsicologoController.class)
public class PsicologoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PsicologoMapper mapper;

    @MockBean
    private PsicologoUseCase useCase;

    @Test
    void testeCadastrarPsicologoComSucesso() throws Exception {

        PsicologoDto psicologoDtoEntrada = PsicologoBuilder.criarPsicologoDto();
        Psicologo psicologoDomain = PsicologoBuilder.criarPsicologo();
        PsicologoDto psicologoDtoSalvo = PsicologoBuilder.criarPsicologoDto();
        psicologoDtoSalvo.setId(UUID.randomUUID());

        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(psicologoDomain);
        Mockito.when(useCase.cadastrar(Mockito.any())).thenReturn(psicologoDomain);
        Mockito.when(mapper.paraDto(Mockito.any())).thenReturn(psicologoDtoSalvo);

        String psicologoJson = objectMapper.writeValueAsString(psicologoDtoEntrada);

        mockMvc.perform(post("/psicologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(psicologoJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/psicologos/" + psicologoDtoSalvo.getId().toString()))
                .andExpect(jsonPath("$.dado.id").value(psicologoDtoSalvo.getId().toString()))
                .andExpect(jsonPath("$.dado.nome").value(psicologoDtoSalvo.getNome()))
                .andExpect(jsonPath("$.dado.crp").value(psicologoDtoSalvo.getCrp()))
                .andExpect(jsonPath("$.erro").doesNotExist());
    }
}
