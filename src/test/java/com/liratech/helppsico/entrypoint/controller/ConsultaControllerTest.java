package com.liratech.helppsico.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liratech.helppsico.builders.ConsultaBuilder;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.consulta.ConsultaDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import com.liratech.helppsico.entrypoint.mapper.ConsultaMapper;
import com.liratech.helppsico.infrastructure.repositories.ConsultaRepository;
import com.liratech.helppsico.infrastructure.repositories.EnderecoRepository;
import com.liratech.helppsico.infrastructure.repositories.PacienteRepository;
import com.liratech.helppsico.infrastructure.repositories.PsicologoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.ConsultaEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.EnderecoEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import com.liratech.helppsico.validators.json.ConsultaValidatorJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ConsultaControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @MockitoSpyBean
    private final ConsultaRepository repository;

    @MockitoSpyBean
    private final PsicologoRepository psicologoRepository;

    @MockitoSpyBean
    private final PacienteRepository pacienteRepository;

    @MockitoSpyBean
    private final EnderecoRepository enderecoRepository;

    private ConsultaDto consultaDtoEntrada;
    private ConsultaEntity consultaRetorno;
    private PacienteEntity pacienteEntity;
    private PsicologoEntity psicologoEntity;
    private EnderecoEntity enderecoEntity;
    private Page<ConsultaEntity> pageConsulta;

    public ConsultaControllerTest(MockMvc mockMvc, ObjectMapper objectMapper, ConsultaMapper mapper,
                                  ConsultaRepository repository, PsicologoRepository psicologoRepository,
                                  PacienteRepository pacienteRepository, EnderecoRepository enderecoRepository) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.mapper = mapper;
        this.repository = repository;
        this.psicologoRepository = psicologoRepository;
        this.pacienteRepository = pacienteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @BeforeEach
    void setUp() {
        consultaRetorno = ConsultaBuilder.criarConsultaEntity();

        pacienteEntity = consultaRetorno.getPaciente();
        psicologoEntity = consultaRetorno.getPsicologo();
        enderecoEntity = consultaRetorno.getEndereco();

        PacienteDto pacienteTeste = new PacienteDto();
        pacienteTeste.setId(pacienteEntity.getId());

        PsicologoDto psicologoTeste = new PsicologoDto();
        psicologoTeste.setId(psicologoEntity.getId());

        EnderecoDto enderecoTeste = new EnderecoDto();
        enderecoTeste.setId(enderecoEntity.getId());

        consultaDtoEntrada = new ConsultaDto();
        consultaDtoEntrada.setDataHora(consultaRetorno.getDataHora());
        consultaDtoEntrada.setPaciente(pacienteTeste);
        consultaDtoEntrada.setPsicologo(psicologoTeste);
        consultaDtoEntrada.setValor(consultaRetorno.getValor());
        consultaDtoEntrada.setEndereco(enderecoTeste);

        pageConsulta = ConsultaBuilder.criarPageConsultaEntity();
    }

    @Test
    void deveAgendarConsultaComSucesso() throws Exception {
        Mockito.when(repository.save(Mockito.any())).thenReturn(consultaRetorno);
        Mockito.when(pacienteRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(pacienteEntity));
        Mockito.when(psicologoRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(psicologoEntity));
        Mockito.when(enderecoRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(enderecoEntity));

        ResultActions resultActions = mockMvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(consultaDtoEntrada)))
                .andExpect(status().isCreated());

        ConsultaValidatorJson.validaConsultaJson(resultActions, consultaDtoEntrada);
    }

    @Test
    void deveCancelarConsultaComSucesso() throws Exception {
        UUID idConsulta = UUID.randomUUID();

        Mockito.doNothing().when(repository).deleteById(idConsulta);

        mockMvc.perform(delete("/consultas/" + idConsulta))
                .andExpect(status().isNoContent());

        Mockito.verify(repository, Mockito.times(1)).deleteById(idConsulta);
    }

    @Test
    void deveConsultarConsultasFuturasComSucesso() throws Exception {

        Mockito.when(repository.consultarConsultasFuturas(Mockito.any(), Mockito.any(), Mockito.any(Pageable.class)))
                .thenReturn(pageConsulta);

        ResultActions resultActions = mockMvc.perform(get("/consultas/futuras/" + pacienteEntity.getId() + "/" + psicologoEntity.getId())
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "dataConsulta,asc"))
                .andExpect(status().isOk());

        ConsultaValidatorJson.validaPageConsultas(resultActions, pageConsulta);
    }

    @Test
    void deveConsultarHistoricoComSucesso() throws Exception {
        Mockito.when(repository.consultarHistorico(Mockito.any(), Mockito.any(), Mockito.any(Pageable.class)))
                .thenReturn(pageConsulta);

        ResultActions resultActions = mockMvc.perform(get("/consultas/historico/" + pacienteEntity.getId() + "/" + psicologoEntity.getId())
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "dataConsulta,asc"))
                .andExpect(status().isOk());

        ConsultaValidatorJson.validaPageConsultas(resultActions, pageConsulta);
    }

    @Test
    void deveAlterarDataDaConsultaComSucesso() throws Exception {
        LocalDateTime novaData = LocalDateTime.now().plusDays(5);

        consultaRetorno.setDataHora(novaData);

        Mockito.when(repository.findById(Mockito.any(UUID.class)))
                .thenReturn(Optional.of(consultaRetorno));

        Mockito.when(repository.save(Mockito.any(ConsultaEntity.class)))
                .thenReturn(consultaRetorno);

        mockMvc.perform(patch("/consultas/" + consultaRetorno.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novaData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.dataConsulta").value(novaData.toString()));
    }

    @Test
    void deveFinalizarConsultaComSucesso() throws Exception {
        consultaRetorno.setFinalizada(true);

        Mockito.when(repository.findById(Mockito.any(UUID.class)))
                .thenReturn(Optional.of(consultaRetorno));

        Mockito.when(repository.save(Mockito.any(ConsultaEntity.class)))
                .thenReturn(consultaRetorno);

        mockMvc.perform(patch("/consultas/finalizar/" + consultaRetorno.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.finalizada").value(true));
    }
}
