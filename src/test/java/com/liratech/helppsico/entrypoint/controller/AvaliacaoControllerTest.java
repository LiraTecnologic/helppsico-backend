package com.liratech.helppsico.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liratech.helppsico.application.usecases.VinculoUseCase;
import com.liratech.helppsico.builders.AvaliacaoBuilder;
import com.liratech.helppsico.builders.VinculoBuilder;
import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.entrypoint.dto.psicologo.AvaliacaoDto;
import com.liratech.helppsico.entrypoint.mapper.AvaliacaoMapper;
import com.liratech.helppsico.infrastructure.mapper.AvaliacaoMapperInfra;
import com.liratech.helppsico.infrastructure.mapper.PacienteMapperInfra;
import com.liratech.helppsico.infrastructure.mapper.PsicologoMapperInfra;
import com.liratech.helppsico.infrastructure.mapper.VinculoMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.AvaliacaoRepository;
import com.liratech.helppsico.infrastructure.repositories.PacienteRepository;
import com.liratech.helppsico.infrastructure.repositories.PsicologoRepository;
import com.liratech.helppsico.infrastructure.repositories.VinculoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.AvaliacaoEntity;
import com.liratech.helppsico.validators.json.AvaliacaoValidatorJson;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AllArgsConstructor
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AvaliacaoControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private AvaliacaoMapper mapperEntry;
    private AvaliacaoMapperInfra mapperInfra;
    private PsicologoMapperInfra mapperPsicologo;
    private PacienteMapperInfra mapperPaciente;
    private VinculoMapperInfra mapperVinculo;

    @MockitoSpyBean
    private AvaliacaoRepository repository;

    @MockitoSpyBean
    private PsicologoRepository psicologoRepository;

    @MockitoSpyBean
    private PacienteRepository pacienteRepository;

    @MockitoSpyBean
    private VinculoRepository vinculoRepository;

    private AvaliacaoDto avaliacaoDtoEntrada;
    private Avaliacao avaliacaoDomain;
    private AvaliacaoEntity avaliacaoEntity;
    private Vinculo vinculoTeste;

    @BeforeEach
    void inicializarAtributos(){
        avaliacaoDtoEntrada = AvaliacaoBuilder.criarAvaliacaoDto();
        avaliacaoDomain = mapperEntry.paraDomain(avaliacaoDtoEntrada);
        avaliacaoEntity = mapperInfra.paraEntity(avaliacaoDomain);

        vinculoTeste = VinculoBuilder.criarVinculo();
        vinculoTeste.setPsicologo(avaliacaoDomain.getPsicologo());
        vinculoTeste.setPaciente(avaliacaoDomain.getPaciente());
    }

    @Test
    void testeCadastrarAvaliacao() throws Exception{
        avaliacaoDtoEntrada.setId(null);

        Mockito.when(psicologoRepository.findById(Mockito.any())).thenReturn(Optional.of(mapperPsicologo.paraEntity(avaliacaoDomain.getPsicologo())));
        Mockito.when(pacienteRepository.findById(Mockito.any())).thenReturn(Optional.of(mapperPaciente.paraEntity(avaliacaoDomain.getPaciente())));
        Mockito.when(vinculoRepository.consultarAtivoPorPaciente(Mockito.any())).thenReturn(Optional.of(mapperVinculo.paraEntity(vinculoTeste)));
        Mockito.when(repository.findByPacienteIdAndPsicologoId(Mockito.any(), Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(repository.save(Mockito.any())).thenReturn(avaliacaoEntity);

        String avaliacaoJson = objectMapper.writeValueAsString(avaliacaoDtoEntrada);

        ResultActions resultado = mockMvc.perform(post("/avaliacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(avaliacaoJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/avaliacoes/" + avaliacaoDomain.getId().toString()));

        AvaliacaoValidatorJson.validaAvaliacaoJson(resultado, mapperEntry.paraDto(avaliacaoDomain));
    }

    @Test
    void testeBuscarAvaliacaoPorId() throws Exception{
        UUID id = avaliacaoDtoEntrada.getId();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(avaliacaoEntity));

        ResultActions resultado = mockMvc.perform(get("/avaliacoes/{id}", id))
                .andExpect(status().isOk());

        AvaliacaoValidatorJson.validaAvaliacaoJson(resultado, avaliacaoDtoEntrada);
    }

    @Test
    void testeListarPorPsicologo() throws Exception{
        UUID idPsicologo = avaliacaoDtoEntrada.getPsicologo().getId();
        Page<AvaliacaoEntity> avaliacaoPageTeste = AvaliacaoBuilder.criarPageDeAvaliacoesEntity();
        avaliacaoPageTeste.forEach(avaliacao -> avaliacao.setPsicologo(avaliacaoEntity.getPsicologo()));

        Mockito.when(psicologoRepository.findById(Mockito.any())).thenReturn(Optional.of(avaliacaoEntity.getPsicologo()));
        Mockito.when(repository.findAllByPsicologoId(Mockito.any(), Mockito.any())).thenReturn(avaliacaoPageTeste);

        ResultActions resultado = mockMvc.perform(get("/avaliacoes/psicologo/{id}", idPsicologo))
                .andExpect(status().isOk());

        AvaliacaoValidatorJson.validaPageResponse(resultado);
    }

    @Test
    void testeDeletarAvaliacao() throws Exception{
        UUID idAvaliacao = avaliacaoDomain.getId();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(avaliacaoEntity));
        Mockito.doNothing().when(repository).deleteById(Mockito.any());

        ResultActions resultado = mockMvc.perform(delete("/avaliacoes/{id}", idAvaliacao))
                .andExpect(status().isNoContent());

        Mockito.verify(repository, Mockito.times(1)).deleteById(idAvaliacao);
    }
}
