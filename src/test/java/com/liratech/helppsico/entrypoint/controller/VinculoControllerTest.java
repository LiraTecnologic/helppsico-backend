package com.liratech.helppsico.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liratech.helppsico.builders.VinculoBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.entrypoint.dto.VinculoDto;
import com.liratech.helppsico.entrypoint.mapper.VinculoMapper;
import com.liratech.helppsico.infrastructure.mapper.PacienteMapper;
import com.liratech.helppsico.infrastructure.mapper.PsicologoMapper;
import com.liratech.helppsico.infrastructure.repositories.PacienteRepository;
import com.liratech.helppsico.infrastructure.repositories.PsicologoRepository;
import com.liratech.helppsico.infrastructure.repositories.VinculoRepository;
import com.liratech.helppsico.validators.VinculoValidator;
import com.liratech.helppsico.validators.json.VinculoValidatorJson;
import lombok.AllArgsConstructor;
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

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AllArgsConstructor
public class VinculoControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private VinculoMapper mapperEntry;
    private com.liratech.helppsico.infrastructure.mapper.VinculoMapper mapperInfra;
    private PacienteMapper pacienteMapper;
    private PsicologoMapper psicologoMapper;

    @MockitoSpyBean
    private PacienteRepository pacienteRepository;

    @MockitoSpyBean
    private PsicologoRepository psicologoRepository;

    @MockitoSpyBean
    private VinculoRepository repository;

    private Vinculo vinculoTeste;
    private VinculoDto vinculoDtoTeste;
    private Page<Vinculo> vinculoPage;
    private Paciente pacienteTeste;
    private Psicologo psicologoTeste;
    private UUID idVinculo;

    @BeforeEach
    void inicializarAtributos(){
        vinculoTeste = VinculoBuilder.criarVinculo();
        vinculoDtoTeste = mapperEntry.paraDto(vinculoTeste);
        psicologoTeste = vinculoTeste.getPsicologo();
        pacienteTeste = vinculoTeste.getPaciente();

        idVinculo = vinculoTeste.getId();

        vinculoPage = VinculoBuilder.criarPageDeVinculos();
    }

    @Test
    void testeCriarSolicitacaoVinculo() throws Exception{
        Mockito.when(pacienteRepository.findById(Mockito.any())).thenReturn(Optional.of(pacienteMapper.paraEntity(pacienteTeste)));
        Mockito.when(psicologoRepository.findById(Mockito.any())).thenReturn(Optional.of(psicologoMapper.paraEntity(psicologoTeste)));
        Mockito.when(repository.save(Mockito.any())).thenReturn(mapperInfra.paraEntity(vinculoTeste));

        vinculoTeste.setId(null);

        String vinculoJson = objectMapper.writeValueAsString(vinculoTeste);

        ResultActions resultActions = mockMvc.perform(post("/vinculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vinculoJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/vinculos/" + idVinculo));

        VinculoValidatorJson.validaVinculoJson(vinculoDtoTeste, resultActions);
    }

    @Test
    void testeAceitarSolicitacao() throws Exception{
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(mapperInfra.paraEntity(vinculoTeste)));
        Mockito.when(repository.save(Mockito.any())).thenReturn(mapperInfra.paraEntity(vinculoTeste));

        ResultActions resultActions = mockMvc.perform(put("/vinculos/{id}", idVinculo))
                .andExpect(status().isOk());

        VinculoValidatorJson.validaVinculoJson(vinculoDtoTeste, resultActions);
    }

    @Test
    void testeDesvinculacao() throws Exception{
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(mapperInfra.paraEntity(vinculoTeste)));
        Mockito.doNothing().when(repository).delete(Mockito.any());

        ResultActions resultActions = mockMvc.perform(delete("/vinculos/{id}", idVinculo))
                .andExpect(status().isNoContent());

        Mockito.verify(repository).deleteById(idVinculo);
    }

    @Test
    void testeListarVinculosPorIdPsicologo() throws Exception{
        int page = 0;
        int size = 0;
        String sort = "paciente.nome,asc";

        Mockito.when(repository.findAllByPsicologo_Id(Mockito.any(), Mockito.any())).thenReturn(vinculoPage.map(mapperInfra::paraEntity));

        ResultActions resultActions = mockMvc.perform(get("/vinculos/{id}", psicologoTeste.getId())
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("sort", sort))
                .andExpect(status().isOk());

        VinculoValidatorJson.validaPageResponse(vinculoDtoTeste, resultActions);
    }

    @Test
    void testeConsultarVinculoPorIdPaciente() throws Exception{
        Mockito.when(repository.findByPaciente_Id(Mockito.any())).thenReturn(Optional.of(mapperInfra.paraEntity(vinculoTeste)));

        ResultActions resultActions = mockMvc.perform(get("/vinculos/{id}", pacienteTeste.getId()))
                .andExpect(status().isOk());

        VinculoValidatorJson.validaVinculoJson(vinculoDtoTeste, resultActions);
    }
}
