package com.liratech.helppsico.entrypoint.controller.documento;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liratech.helppsico.builders.SolicitacaoDocumentoBuilder;
import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import com.liratech.helppsico.entrypoint.dto.documento.SolicitacaoDocumentoDto;
import com.liratech.helppsico.infrastructure.mapper.PacienteMapper;
import com.liratech.helppsico.infrastructure.mapper.PsicologoMapper;
import com.liratech.helppsico.infrastructure.repositories.PacienteRepository;
import com.liratech.helppsico.infrastructure.repositories.PsicologoRepository;
import com.liratech.helppsico.infrastructure.repositories.SolicitacaoDocumentoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.SolicitacaoDocumentoEntity;
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

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private PsicologoMapper mapperPsicologo;
    private PacienteMapper mapperPaciente;
    private final SolicitacaoDocumentoMapper mapperEntry;
    private final com.liratech.helppsico.infrastructure.mapper.SolicitacaoDocumentoMapper mapperInfra;

    @MockitoSpyBean
    private SolicitacaoDocumentoRepository repository;

    @MockitoSpyBean
    private PsicologoRepository psicologoRepository;

    @MockitoSpyBean
    private PacienteRepository pacienteRepository;

    private SolicitacaoDocumentoEntity solicitacaoRetorno;

    private SolicitacaoDocumentoDto solicitacaoDtoEntrada;
    private SolicitacaoDocumento solicitacaoDomain;
    private SolicitacaoDocumentoEntity solicitacaoEntity;

    private PacienteEntity pacienteEntity;
    private PsicologoEntity psicologoEntity;

    public SolicitacaoDocumentoControllerTest(SolicitacaoDocumentoDto solicitacaoDtoEntrada, SolicitacaoDocumento solicitacaoDomain, SolicitacaoDocumentoEntity solicitacaoEntity) {
        this.solicitacaoDtoEntrada = solicitacaoDtoEntrada;
        this.solicitacaoDomain = solicitacaoDomain;
        this.solicitacaoEntity = solicitacaoEntity;
    }

    @BeforeEach
    void inicializarAtributos(){
        solicitacaoRetorno = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumentoEntity();
        pacienteEntity = solicitacaoRetorno.getPaciente();
        psicologoEntity = solicitacaoRetorno.getPsicologo();

        this.solicitacaoDtoEntrada = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumentoDto();
        this.solicitacaoDomain = mapperEntry.paraDomain(solicitacaoDtoEntrada);
        this.solicitacaoEntity = mapperInfra.paraEntity(solicitacaoDomain);
    }

    @Test
    void testeSolicitarDocumentos() throws Exception {
        solicitacaoDtoEntrada.setId(null);

        Mockito.when(pacienteRepository.findById(Mockito.any())).thenReturn(Optional.of(pacienteEntity));
        Mockito.when(psicologoRepository.findById(Mockito.any())).thenReturn(Optional.of(psicologoEntity));
        Mockito.when(repository.save(Mockito.any())).thenReturn(solicitacaoEntity);

        String solicitacaoJson = objectMapper.writeValueAsString(solicitacaoDtoEntrada);

        ResultActions resultado = mockMvc.perform(post("/solicitacoes-documentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(solicitacaoJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/solicitacoes-documentos/" + solicitacaoDomain.getId().toString()));

        SolicitacaoDocumentoValidatorJson.validaSolicitacaoJson(resultado, mapperEntry.paraDto(solicitacaoDomain));
    }
}