package com.liratech.helppsico.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liratech.helppsico.builders.ConsultaBuilder;
import com.liratech.helppsico.builders.HorarioBuilder;
import com.liratech.helppsico.builders.VinculoBuilder;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.consulta.ConsultaDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import com.liratech.helppsico.entrypoint.mapper.ConsultaMapper;
import com.liratech.helppsico.infrastructure.repositories.*;
import com.liratech.helppsico.infrastructure.repositories.entities.*;
import com.liratech.helppsico.validators.json.ConsultaValidatorJson;
import lombok.AllArgsConstructor;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AllArgsConstructor
public class ConsultaControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockitoSpyBean
    private ConsultaRepository repository;

    @MockitoSpyBean
    private PsicologoRepository psicologoRepository;

    @MockitoSpyBean
    private HorarioRepository horarioRepository;

    @MockitoSpyBean
    private PacienteRepository pacienteRepository;

    @MockitoSpyBean
    private VinculoRepository vinculoRepository;

    private ConsultaDto consultaDtoEntrada;
    private ConsultaEntity consultaRetorno;
    private PacienteEntity pacienteEntity;
    private PsicologoEntity psicologoEntity;
    private EnderecoEntity enderecoEntity;
    private HorarioEntity horarioEntity;
    private VinculoEntity vinculoRetorno;
    private Page<ConsultaEntity> pageConsulta;

    @BeforeEach
    void setUp() {
        consultaRetorno = ConsultaBuilder.criarConsultaEntity();
        vinculoRetorno = VinculoBuilder.criarVinculoEntity();

        pacienteEntity = consultaRetorno.getPaciente();
        psicologoEntity = consultaRetorno.getPsicologo();
        horarioEntity = consultaRetorno.getHorario();
        enderecoEntity = consultaRetorno.getEndereco();

        PacienteDto pacienteTeste = new PacienteDto();
        pacienteTeste.setId(pacienteEntity.getId());

        PsicologoDto psicologoTeste = new PsicologoDto();
        psicologoTeste.setId(psicologoEntity.getId());

        EnderecoDto enderecoTeste = new EnderecoDto();
        enderecoTeste.setId(enderecoEntity.getId());

        HorarioDto horarioTeste = new HorarioDto();
        horarioTeste.setId(horarioEntity.getId());

        consultaDtoEntrada = new ConsultaDto();
        consultaDtoEntrada.setData(consultaRetorno.getData());
        consultaDtoEntrada.setHorario(horarioTeste);
        consultaDtoEntrada.setPaciente(pacienteTeste);
        consultaDtoEntrada.setPsicologo(psicologoTeste);
        consultaDtoEntrada.setValor(consultaRetorno.getValor());
        consultaDtoEntrada.setEndereco(enderecoTeste);

        vinculoRetorno.setPsicologo(psicologoEntity);
        vinculoRetorno.setPaciente(pacienteEntity);

        pageConsulta = ConsultaBuilder.criarPageConsultaEntity();
    }

    @Test
    void deveAgendarConsultaComSucesso() throws Exception {
        Mockito.when(repository.save(Mockito.any())).thenReturn(consultaRetorno);
        Mockito.when(pacienteRepository.findById(Mockito.any())).thenReturn(Optional.of(pacienteEntity));
        Mockito.when(psicologoRepository.findById(Mockito.any())).thenReturn(Optional.of(psicologoEntity));
        Mockito.when(horarioRepository.findById(Mockito.any())).thenReturn(Optional.of(horarioEntity));
        Mockito.when(vinculoRepository.consultarAtivoPorPaciente(Mockito.any())).thenReturn(Optional.of(vinculoRetorno));
        Mockito.when(repository.consultarConsultasMesmoDia(Mockito.any(), Mockito.any())).thenReturn(Collections.emptyList());
        Mockito.doNothing().when(horarioRepository).save(Mockito.any());

        ResultActions resultActions = mockMvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(consultaDtoEntrada)))
                .andExpect(status().isCreated());

        ConsultaValidatorJson.validaConsultaJson(resultActions, consultaDtoEntrada);
    }

    @Test
    void deveCancelarConsultaComSucesso() throws Exception {
        UUID idConsulta = UUID.randomUUID();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(consultaRetorno));
        Mockito.doNothing().when(horarioRepository).save(Mockito.any());
        Mockito.doNothing().when(repository).deleteById(Mockito.any());

        mockMvc.perform(delete("/consultas/" + idConsulta))
                .andExpect(status().isNoContent());

        Mockito.verify(repository, Mockito.times(1)).deleteById(idConsulta);
    }

    @Test
    void deveConsultarConsultasFuturasComSucessoPorPaciente() throws Exception {
        Mockito.when(pacienteRepository.findById(Mockito.any())).thenReturn(Optional.of(pacienteEntity));
        Mockito.when(vinculoRepository.consultarAtivoPorPaciente(Mockito.any())).thenReturn(Optional.of(vinculoRetorno));
        Mockito.when(repository.consultarConsultasFuturasPaciente(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(pageConsulta);

        ResultActions resultActions = mockMvc.perform(get("/consultas/paciente/futuras/" + pacienteEntity.getId())
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk());

        ConsultaValidatorJson.validaPageConsultas(resultActions, pageConsulta);
    }

    @Test
    void deveConsultarHistoricoPacienteComSucesso() throws Exception {
        Mockito.when(pacienteRepository.findById(Mockito.any())).thenReturn(Optional.of(pacienteEntity));
        Mockito.when(vinculoRepository.consultarAtivoPorPaciente(Mockito.any())).thenReturn(Optional.of(vinculoRetorno));
        Mockito.when(repository.consultarHistoricoPaciente(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(pageConsulta);

        ResultActions resultActions = mockMvc.perform(get("/consultas/paciente/historico/" + pacienteEntity.getId())
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk());

        ConsultaValidatorJson.validaPageConsultas(resultActions, pageConsulta);
    }

    @Test
    void deveConsultarConsultasFuturasComSucessoPorPsicologo() throws Exception {
        Mockito.when(psicologoRepository.findById(Mockito.any())).thenReturn(Optional.of(psicologoEntity));
        Mockito.when(repository.consultarConsultasFuturasPsicologo(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(pageConsulta);

        ResultActions resultActions = mockMvc.perform(get("/consultas/psicologo/futuras/" + psicologoEntity.getId())
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk());

        ConsultaValidatorJson.validaPageConsultas(resultActions, pageConsulta);
    }

    @Test
    void deveConsultarHistoricoPsicologoComSucesso() throws Exception {
        Mockito.when(pacienteRepository.findById(Mockito.any())).thenReturn(Optional.of(pacienteEntity));
        Mockito.when(vinculoRepository.consultarAtivoPorPaciente(Mockito.any())).thenReturn(Optional.of(vinculoRetorno));
        Mockito.when(repository.consultarHistoricoPsicologo(Mockito.any(), Mockito.any())).thenReturn(pageConsulta);

        ResultActions resultActions = mockMvc.perform(get("/consultas/psicologo/historico/" + psicologoEntity.getId())
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk());

        ConsultaValidatorJson.validaPageConsultas(resultActions, pageConsulta);
    }

    @Test
    void deveAlterarDataDaConsultaPsicologoComSucesso() throws Exception {
        ConsultaDto novaData = ConsultaDto.builder()
                .horario(HorarioBuilder.criarHorarioDto())
                .data(LocalDate.now())
                .build();

        Mockito.when(repository.findById(Mockito.any(UUID.class)))
                .thenReturn(Optional.of(consultaRetorno));
        Mockito.when(repository.consultarConsultasMesmoDia(Mockito.any(), Mockito.any())).thenReturn(Collections.emptyList());
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
