package com.liratech.helppsico.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.liratech.helppsico.application.usecases.PsicologoUseCase;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import com.liratech.helppsico.entrypoint.mapper.EnderecoMapper;
import com.liratech.helppsico.entrypoint.mapper.PsicologoMapper;
import com.liratech.helppsico.infrastructure.repositories.PsicologoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.EnderecoEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import com.liratech.helppsico.validators.json.PsicologoValidatorJson;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RequiredArgsConstructor
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PsicologoControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final PsicologoMapper mapperEntry;
    private final com.liratech.helppsico.infrastructure.mappers.PsicologoMapper mapperInfra;
    private final EnderecoMapper

    @MockitoSpyBean
    private final PsicologoRepository repository;

    @MockitoSpyBean
    private final EnderecoRepository repositoryEndereco;

    private PsicologoDto psicologoDtoEntrada;
    private Psicologo psicologoDomain;

    @BeforeEach
    void inicializacaoAtributos(){
        this.psicologoDtoEntrada = PsicologoBuilder.criarPsicologoDto();
        this.psicologoDomain = mapperEntry.paraDomain(psicologoDtoEntrada);
    }

    @Test
    void testeCadastrarPsicologo() throws Exception {
        psicologoDtoEntrada.setId(null);
        EnderecoEntity enderecoEntity = mapp

        Mockito.when(repository.findByCrp(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(repositoryEndereco.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(repositoryEndereco.save(Mockito.any())).thenReturn();
        Mockito.when(repository.save(Mockito.any())).thenReturn(mapperInfra.paraEntity(psicologoDomain));


        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String psicologoJson = objectMapper.writeValueAsString(psicologoDtoEntrada);

        ResultActions resultado = mockMvc.perform(post("/psicologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(psicologoJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/psicologos/"
                        + mapperEntry.paraDto(psicologoDomain).getId().toString()));

        PsicologoValidatorJson.validaPsicologoJson(resultado, mapperEntry.paraDto(psicologoDomain));
    }

    @Test
    void testeListarPsicologos() throws Exception {
        int page = 0;
        int size = 10;
        String sort = "nome,asc";

        Pageable pageable = PageRequest.of(page,size);

        Page<Psicologo> paginaDomain = PsicologoBuilder.criarPageDePsicologos();
        Page<PsicologoEntity> paginaEntity = paginaDomain.map(mapperInfra::paraEntity);

        Mockito.when(repository.findAll(pageable)).thenReturn(paginaEntity);

        ResultActions resultado = mockMvc.perform(get("/psicologos")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("sort", sort))
                .andExpect(status().isOk());

        PsicologoValidatorJson.validaPageResponse(resultado);
    }

    @Test
    void testeConsultarPorId() throws Exception{
        UUID idRequest = psicologoDtoEntrada.getId();

        Mockito.when()
    }
}
