package com.liratech.helppsico.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.liratech.helppsico.domain.Foto;
import com.liratech.helppsico.builders.FotoBuilder;
import com.liratech.helppsico.entrypoint.dto.FotoDto;
import com.liratech.helppsico.entrypoint.mapper.FotoMapper;
import com.liratech.helppsico.infrastructure.dataprovider.FotoDataProvider;
import com.liratech.helppsico.infrastructure.mapper.PacienteMapper;
import com.liratech.helppsico.infrastructure.mapper.PsicologoMapper;
import com.liratech.helppsico.infrastructure.repositories.FotoRepository;
import com.liratech.helppsico.infrastructure.repositories.PacienteRepository;
import com.liratech.helppsico.infrastructure.repositories.PsicologoRepository;
import com.liratech.helppsico.validators.json.FotoValidatorJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FotoControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final FotoMapper mapperEntry;
    private final com.liratech.helppsico.infrastructure.mapper.FotoMapper mapperInfra;
    private final PacienteMapper pacienteMapper;
    private final PsicologoMapper psicologoMapper;

    @MockitoSpyBean
    private FotoRepository fotoRepository;

    @MockitoSpyBean
    private FotoDataProvider fotoDataProvider;

    @MockitoSpyBean
    private PacienteRepository pacienteRepository;

    @MockitoSpyBean
    private PsicologoRepository psicologoRepository;

    private FotoDto fotoDtoPaciente;
    private FotoDto fotoDtoPsicologo;
    private Foto fotoDomainPaciente;
    private Foto fotoDomainPsicologo;
    private MockMultipartFile arquivoFoto;

    public FotoControllerTest(MockMvc mockMvc, ObjectMapper objectMapper, FotoMapper mapper, FotoMapper mapperEntry, com.liratech.helppsico.infrastructure.mapper.FotoMapper mapperInfra, PacienteMapper pacienteMapper, PsicologoMapper psicologoMapper, MockMultipartFile arquivoFoto, Foto fotoDomainPaciente, FotoDto fotoDtoPaciente, FotoDto fotoDtoPsicologo, Foto fotoDomainPsicologo) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.mapperEntry = mapperEntry;
        this.mapperInfra = mapperInfra;
        this.pacienteMapper = pacienteMapper;
        this.psicologoMapper = psicologoMapper;
        this.arquivoFoto = arquivoFoto;
        this.fotoDomainPaciente = fotoDomainPaciente;
        this.fotoDtoPaciente = fotoDtoPaciente;
        this.fotoDtoPsicologo = fotoDtoPsicologo;
        this.fotoDomainPsicologo = fotoDomainPsicologo;
    }

    @BeforeEach
    void inicializarAtributos() throws Exception {
        this.fotoDtoPaciente = FotoBuilder.criarFotoDtoPaciente();
        this.fotoDomainPaciente = mapperEntry.paraDomain(fotoDtoPaciente);

        this.fotoDtoPsicologo = FotoBuilder.criarFotoDtoPsicologo();
        this.fotoDomainPsicologo = mapperEntry.paraDomain(fotoDtoPsicologo);

        Path tempFile = Files.createTempFile("test-image", ".jpg");
        Files.write(tempFile, "conteúdo de teste".getBytes());

        this.arquivoFoto = new MockMultipartFile(
                "foto",
                "test-image.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                Files.readAllBytes(tempFile)
        );
    }

    @Test
    void testeSalvarFotoPaciente() throws Exception {
        Mockito.when(fotoDataProvider.salvarLocal(Mockito.any())).thenReturn("url-local");
        Mockito.when(pacienteRepository.findById(Mockito.any())).thenReturn(Optional.of(pacienteMapper.paraEntity(fotoDomainPaciente.getPaciente())));
        Mockito.when(pacienteRepository.save(Mockito.any())).thenReturn(pacienteMapper.paraEntity(fotoDomainPaciente.getPaciente()));
        Mockito.when(fotoRepository.save(Mockito.any())).thenReturn(mapperInfra.paraEntity(fotoDomainPaciente));

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String fotoJson = objectMapper.writeValueAsString(fotoDtoPaciente);

        MockMultipartFile fotoJsonPart = new MockMultipartFile(
                "fotoDto",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                fotoJson.getBytes()
        );

        ResultActions resultado = mockMvc.perform(multipart("/fotos")
                        .file(arquivoFoto)
                        .file(fotoJsonPart))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/fotos"));

        FotoValidatorJson.validaFotoPacienteJson(resultado, mapperEntry.paraDto(fotoDomainPaciente));
    }

    @Test
    void testeSalvarFotoPsicologo() throws Exception {
        Mockito.when(fotoDataProvider.salvarLocal(Mockito.any())).thenReturn("url-local");
        Mockito.when(psicologoRepository.findById(Mockito.any())).thenReturn(Optional.of(psicologoMapper.paraEntity(fotoDomainPsicologo.getPsicologo())));
        Mockito.when(psicologoRepository.save(Mockito.any())).thenReturn(psicologoMapper.paraEntity(fotoDomainPsicologo.getPsicologo()));
        Mockito.when(fotoRepository.save(Mockito.any())).thenReturn(mapperInfra.paraEntity(fotoDomainPsicologo));

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String fotoJson = objectMapper.writeValueAsString(fotoDtoPsicologo);

        MockMultipartFile fotoJsonPart = new MockMultipartFile(
                "fotoDto",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                fotoJson.getBytes()
        );

        ResultActions resultado = mockMvc.perform(multipart("/fotos")
                        .file(arquivoFoto)
                        .file(fotoJsonPart))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/fotos"));

        FotoValidatorJson.validaFotoPsicologoJson(resultado, mapperEntry.paraDto(fotoDomainPsicologo));
    }

}