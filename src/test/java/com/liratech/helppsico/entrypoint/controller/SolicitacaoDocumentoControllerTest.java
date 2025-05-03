package com.liratech.helppsico.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liratech.helppsico.builders.SolicitacaoDocumentoBuilder;
import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import com.liratech.helppsico.entrypoint.dto.documento.SolicitacaoDocumentoDto;
import com.liratech.helppsico.infrastructure.mapper.PacienteMapper;
import com.liratech.helppsico.infrastructure.mapper.PsicologoMapper;
import com.liratech.helppsico.infrastructure.repositories.PacienteRepository;
import com.liratech.helppsico.infrastructure.repositories.PsicologoRepository;
import com.liratech.helppsico.infrastructure.repositories.SolicitacaoDocumentoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.SolicitacaoDocumentoEntity;
import com.liratech.helppsico.validators.SolicitacaoDocumentoValidator;
import com.liratech.helppsico.validators.json.SolicitacaoDocumentoValidatorJson;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SolicitacaoDocumentoControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    private final PsicologoMapper mapperPsicologo;
    private final PacienteMapper mapperPaciente;
    private final SolicitacaoDocumentoMapper mapperEntry;
    private final com.liratech.helppsico.infrastructure.mapper.SolicitacaoDocumentoMapper mapperInfra;

    @MockitoSpyBean
    private final SolicitacaoDocumentoRepository repository;

    @MockitoSpyBean
    private final PsicologoRepository psicologoRepository;

    @MockitoSpyBean
    private final PacienteRepository pacienteRepository;

    private SolicitacaoDocumentoDto solicitacaoDtoEntrada;
    private SolicitacaoDocumento solicitacaoDomain;
    private SolicitacaoDocumentoEntity solicitacaoEntity;

    @BeforeEach
    void inicializarAtributos(){
        this.solicitacaoDtoEntrada = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumentoDto();
        this.solicitacaoDomain = mapperEntry.paraDomain(solicitacaoDtoEntrada);
        this.solicitacaoEntity = mapperInfra.paraEntity(solicitacaoDomain);
    }

    @Test
    void testeSolicitarDocumentos() {
        solicitacaoDtoEntrada.setId(null);

        Mockito.when(psicologoRepository.findById(Mockito.any())).thenReturn(Optional.of(mapperPsicologo.paraEntity(solicitacaoDomain.getPsicologo())));
        Mockito.when(pacienteRepository.findById(Mockito.any())).thenReturn(Optional.of(mapperPaciente.paraEntity(solicitacaoDomain.getPaciente())));
        Mockito.when(repository.save(Mockito.any())).thenReturn(solicitacaoEntity);

        String solicitacaoJson = objectMapper.writeValueAsString(solicitacaoDtoEntrada);

        ResultActions resultado = mockMvc.perform(post("/solicitacoesDocumentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(solicitacaoJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/solicitacoesDocumentos/" + solicitacaoDomain.getId().toString()));

        SolicitacaoDocumentoValidatorJson.validaSolicitacaoJson(resultado, mapperEntry.paraDto(solicitacaoDomain));
    }
}