package com.liratech.helppsico.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liratech.helppsico.builders.VinculoBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.entrypoint.mapper.VinculoMapper;
import com.liratech.helppsico.infrastructure.mapper.PacienteMapper;
import com.liratech.helppsico.infrastructure.mapper.PsicologoMapper;
import com.liratech.helppsico.infrastructure.repositories.PacienteRepository;
import com.liratech.helppsico.infrastructure.repositories.PsicologoRepository;
import com.liratech.helppsico.infrastructure.repositories.VinculoRepository;
import com.liratech.helppsico.validators.VinculoValidator;
import lombok.AllArgsConstructor;
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

    private Paciente pacienteTeste;
    private Psicologo psicologoTeste;
    private UUID idVinculo;

    @BeforeEach
    void inicializarAtributos(){
        vinculoTeste = VinculoBuilder.criarVinculo();
        psicologoTeste = vinculoTeste.getPsicologo();
        pacienteTeste = vinculoTeste.getPaciente();

        idVinculo = vinculoTeste.getId();
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

        VinculoValidatorJson.validaVinculoJson(mapperEntry.paraDto(vinculoTeste), resultActions);
    }
}
