package com.liratech.helppsico.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liratech.helppsico.application.usecases.CriptografiaUseCase;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.entrypoint.dto.LoginDto;
import com.liratech.helppsico.entrypoint.dto.LoginRespostaDto;
import com.liratech.helppsico.infrastructure.repositories.PacienteRepository;
import com.liratech.helppsico.infrastructure.repositories.PsicologoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import com.liratech.helppsico.infrastructure.security.TokenDataProvider;
import com.liratech.helppsico.validators.json.LoginRespostaValidatorJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockitoSpyBean
    private PacienteRepository pacienteRepository;
    @MockitoSpyBean
    private PsicologoRepository psicologoRepository;
    @MockitoSpyBean
    private CriptografiaUseCase criptografiaUseCase;
    @MockitoSpyBean
    private TokenDataProvider tokenDataProvider;

    private PacienteEntity pacienteBuscadoEntity;
    private PsicologoEntity psicologoBuscadoEntity;
    private String senha;
    private String email;
    private String crp;
    private String token;

    @BeforeEach
    void inicializarAtributos(){
        pacienteBuscadoEntity = PacienteBuilder.criarPacienteEntity();
        email = pacienteBuscadoEntity.getEmail();

        psicologoBuscadoEntity = PsicologoBuilder.criarPsicologoEntity();
        crp = psicologoBuscadoEntity.getCrp();

        token = "token-gerado";
    }

    @Test
    void deveLogarPacienteComSucesso() throws Exception{
        senha = pacienteBuscadoEntity.getSenha();

        Mockito.when(pacienteRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(pacienteBuscadoEntity));
        Mockito.when(tokenDataProvider.gerarTokenPaciente(Mockito.any())).thenReturn(token);

        LoginDto loginDtoTeste = LoginDto.builder()
                .email(email)
                .senha(senha)
                .build();

        ResultActions resultActions = mockMvc.perform(post("/login/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDtoTeste)))
                .andExpect(status().isOk());

        LoginRespostaValidatorJson.validaLoginRespostaJson(
                resultActions,
                LoginRespostaDto.builder()
                        .email(email)
                        .token(token)
                        .build()
        );
    }

    @Test
    void deveLogarPsicologoComSucesso() throws Exception{
        senha = psicologoBuscadoEntity.getSenha();

        Mockito.when(psicologoRepository.findByCrp(Mockito.any())).thenReturn(Optional.of(psicologoBuscadoEntity));
        Mockito.when(tokenDataProvider.gerarTokenPsicologo(Mockito.any())).thenReturn(token);

        LoginDto loginDtoTeste = LoginDto.builder()
                .crp(crp)
                .senha(senha)
                .build();

        ResultActions resultActions = mockMvc.perform(post("/login/psicologo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDtoTeste)))
                .andExpect(status().isOk());

        LoginRespostaValidatorJson.validaLoginRespostaJson(
                resultActions,
                LoginRespostaDto.builder()
                        .crp(crp)
                        .token(token)
                        .build()
        );
    }

}
