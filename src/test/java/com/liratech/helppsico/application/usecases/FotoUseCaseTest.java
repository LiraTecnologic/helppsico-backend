package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.foto.CaminhoNaoSalvoException;
import com.liratech.helppsico.application.gateways.FotoGateway;
import com.liratech.helppsico.builders.FotoBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Foto;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.validators.FotoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@ExtendWith(MockitoExtension.class)
public class FotoUseCaseTest {
    @Mock
    private FotoGateway gateway;

    @Mock
    private PacienteUseCase pacienteUseCase;

    @Mock
    private PsicologoUseCase psicologoUseCase;

    @InjectMocks
    private FotoUseCase useCase;

    private MockMultipartFile multipartFile;
    private Paciente paciente;
    private Paciente pacienteSemUrl;
    private Psicologo psicologo;
    private Psicologo psicologoSemUrl;
    private Foto fotoPaciente;
    private Foto fotoPsicologo;

    @BeforeEach
    void inicializarAtributos(){
        paciente = PacienteBuilder.criarPaciente();

        pacienteSemUrl = paciente;
        pacienteSemUrl.setFotoUrl(null);

        psicologo = PsicologoBuilder.criarPsicologo();

        psicologoSemUrl = psicologo;
        psicologoSemUrl.setFotoUrl(null);

        fotoPaciente = FotoBuilder.criarFotoDomainPaciente();
        fotoPaciente.setPaciente(paciente);
        fotoPsicologo = FotoBuilder.criarFotoDomainPsicologo();
        fotoPsicologo.setPsicologo(psicologo);

        multipartFile = new MockMultipartFile(
                "arquivo",
                "foto.jpg",
                "image/jpeg",
                "conteudo da imagem".getBytes()
        );
    }

    @Test
    void testeSalvarFotoPaciente(){
        Mockito.when(gateway.salvarLocal(Mockito.any())).thenReturn(paciente.getFotoUrl());
        Mockito.when(pacienteUseCase.consultarPorId(Mockito.any())).thenReturn(pacienteSemUrl);
        Mockito.when(pacienteUseCase.cadastrar(Mockito.any())).thenReturn(paciente);

        Foto fotoTeste = useCase.salvar(multipartFile, fotoPaciente);

        FotoValidator.validaFotoDomain(fotoPaciente, fotoTeste);
    }

    @Test
    void testeSalvarFotoPsicologo(){
        Mockito.when(gateway.salvarLocal(Mockito.any())).thenReturn(paciente.getFotoUrl());
        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(psicologo);
        Mockito.when(psicologoUseCase.cadastrar(Mockito.any())).thenReturn(psicologo);

        Foto fotoTeste = useCase.salvar(multipartFile, fotoPsicologo);

        FotoValidator.validaFotoDomain(fotoPaciente, fotoTeste);
    }

    @Test
    void testeExceptionCaminhoNaoSalvo(){
        Mockito.when(gateway.salvarLocal(Mockito.any())).thenThrow(RuntimeException.class);

        CaminhoNaoSalvoException exception = Assertions.assertThrows(
                CaminhoNaoSalvoException.class,
                () -> useCase.salvar(multipartFile, fotoPsicologo)
        );

        Assertions.assertEquals(FotoUseCase.ERRO_CAMINHO_NAO_SALVO, exception.getMessage());
    }
}
