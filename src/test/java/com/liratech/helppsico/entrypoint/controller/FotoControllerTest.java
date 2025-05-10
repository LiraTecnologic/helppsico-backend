package com.liratech.helppsico.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.liratech.helppsico.application.gateways.FotoGateway;
import com.liratech.helppsico.application.usecases.FotoUseCase;
import com.liratech.helppsico.application.usecases.PacienteUseCase;
import com.liratech.helppsico.application.usecases.PsicologoUseCase;
import com.liratech.helppsico.domain.Foto;
import com.liratech.helppsico.builders.FotoBuilder;
import com.liratech.helppsico.entrypoint.dto.FotoDto;
import com.liratech.helppsico.entrypoint.mapper.FotoMapper;
import com.liratech.helppsico.validators.json.FotoValidatorJson;
import lombok.RequiredArgsConstructor;
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

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RequiredArgsConstructor
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FotoControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final FotoMapper mapper;

    @MockitoSpyBean
    private final FotoUseCase fotoUseCase;

    @MockitoSpyBean
    private final FotoGateway fotoGateway;

    @MockitoSpyBean
    private final PacienteUseCase pacienteUseCase;

    @MockitoSpyBean
    private final PsicologoUseCase psicologoUseCase;

    private FotoDto fotoDtoEntrada;
    private Foto fotoDomain;
    private MockMultipartFile arquivoFoto;

    public FotoControllerTest(FotoMapper mapper, MockMvc mockMvc, ObjectMapper objectMapper, FotoDto fotoDtoEntrada, Foto fotoDomain, MockMultipartFile arquivoFoto) {
        this.mapper = mapper;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.fotoDtoEntrada = fotoDtoEntrada;
        this.fotoDomain = fotoDomain;
        this.arquivoFoto = arquivoFoto;
    }

    public FotoControllerTest(MockMultipartFile arquivoFoto, Foto fotoDomain, FotoDto fotoDtoEntrada) {
        this.arquivoFoto = arquivoFoto;
        this.fotoDomain = fotoDomain;
        this.fotoDtoEntrada = fotoDtoEntrada;
    }

    @BeforeEach
    void inicializarAtributos() throws Exception {
        this.fotoDtoEntrada = FotoBuilder.criarFotoDto();
        this.fotoDomain = mapper.paraDomain(fotoDtoEntrada);

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
        Mockito.when(fotoGateway.salvarLocal(any())).thenReturn("C:/12345_teste.jpg");
        Mockito.when(pacienteUseCase.consultarPorId(any())).thenReturn(fotoDomain.getPaciente());
        Mockito.when(fotoUseCase.salvar(any(), any())).thenReturn(fotoDomain);

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String fotoJson = objectMapper.writeValueAsString(fotoDtoEntrada);

        MockMultipartFile fotoJsonPart = new MockMultipartFile(
                "fotoDto",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                fotoJson.getBytes()
        );

        ResultActions resultado = mockMvc.perform(multipart("/pacientes")
                        .file(arquivoFoto)
                        .file(fotoJsonPart))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/pacientes/"));

        FotoValidatorJson.validaFotoJson(resultado, mapper.paraDto(fotoDomain));
    }


}