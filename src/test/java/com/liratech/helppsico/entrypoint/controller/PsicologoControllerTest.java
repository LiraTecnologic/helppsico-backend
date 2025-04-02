package com.liratech.helppsico.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import com.liratech.helppsico.entrypoint.mapper.PsicologoMapper;
import com.liratech.helppsico.infrastructure.mapper.EnderecoMapper;
import com.liratech.helppsico.infrastructure.repositories.EnderecoRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PsicologoControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final PsicologoMapper mapperEntry;
    private final com.liratech.helppsico.infrastructure.mapper.PsicologoMapper mapperInfra;
    private final EnderecoMapper enderecoMapper;

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
        EnderecoEntity enderecoEntity = enderecoMapper.paraEntity(psicologoDomain.getEnderecoAtendimento());

        Mockito.when(repository.findByCrp(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(repositoryEndereco.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(repositoryEndereco.save(Mockito.any())).thenReturn(enderecoEntity);
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
    void testeConsultarPorId() throws Exception {
        UUID idRequest = psicologoDtoEntrada.getId();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(mapperInfra.paraEntity(psicologoDomain)));

        ResultActions resultado = mockMvc.perform(get("/psicologos/{id}", idRequest))
                .andExpect(status().isOk());

        PsicologoValidatorJson.validaPsicologoJson(resultado, psicologoDtoEntrada);
    }

    @Test
    void testeConsultarPorNome() throws Exception {
        int page = 0;
        int size = 10;
        String sort = "nome,asc";
        Pageable pageable = PageRequest.of(page,size);

        Page<Psicologo> paginaDomain = PsicologoBuilder.criarPageDePsicologos();
        Page<PsicologoEntity> paginaEntity = paginaDomain.map(mapperInfra::paraEntity);

        Mockito.when(repository.findByNome(Mockito.any())).thenReturn(paginaEntity);

        ResultActions resultado = mockMvc.perform(get("/psicologos/nome")
                        .param("nome", "joão-silva"))
                .andExpect(status().isOk());

        PsicologoValidatorJson.validaPageResponse(resultado);
    }

    @Test
    void testeConsultarMelhoresAvaliados() throws Exception {
        int page = 0;
        int size = 10;
        String sort = "nome,asc";
        Pageable pageable = PageRequest.of(page,size);

        Page<Psicologo> paginaDomain = PsicologoBuilder.criarPageDePsicologos();
        Page<PsicologoEntity> paginaEntity = paginaDomain.map(mapperInfra::paraEntity);

        Mockito.when(repository.consultarMelhoresAvaliados(Mockito.any())).thenReturn(paginaEntity);

        ResultActions resultado = mockMvc.perform(get("/psicologos/melhores-avaliados")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("sort", sort))
                .andExpect(status().isOk());

        PsicologoValidatorJson.validaPageResponse(resultado);
    }

    @Test
    void testeConsultarPorCrp() throws Exception {
        String crpParam = psicologoDtoEntrada.getCrp();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(mapperInfra.paraEntity(psicologoDomain)));

        ResultActions resultado = mockMvc.perform(get("/psicologos/crp")
                        .param("crp", crpParam))
                .andExpect(status().isOk());

        PsicologoValidatorJson.validaPsicologoJson(resultado, psicologoDtoEntrada);
    }
}
