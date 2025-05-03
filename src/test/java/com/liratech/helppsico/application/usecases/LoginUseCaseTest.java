package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.login.SenhaInvalidaException;
import com.liratech.helppsico.builders.LoginRespostaBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.entrypoint.dto.LoginRespostaDto;
import com.liratech.helppsico.validators.LoginRespostaValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@AllArgsConstructor
@ExtendWith(MockitoExtension.class)
public class LoginUseCaseTest {
    @Mock
    private PacienteUseCase pacienteUseCase;

    @Mock
    private PsicologoUseCase psicologoUseCase;

    @Mock
    private AutenticacaoUseCase autenticacaoUseCase;

    @Mock
    private CriptografiaUseCase criptografiaUseCase;

    @InjectMocks
    private LoginUseCase useCase;

    private String email;
    private String senha;
    private String crp;
    private String token;
    private Paciente pacienteBuscado;
    private Psicologo psicologoBuscado;

    @BeforeEach
    void inicializacao(){
        email = "email.teste@email.com";
        senha = "123Senha@";
        crp = "03/02345";
        token = "token-seguro";

        pacienteBuscado = PacienteBuilder.criarPaciente();
        pacienteBuscado.setEmail(email);
        pacienteBuscado.setSenha(senha);

        psicologoBuscado = PsicologoBuilder.criarPsicologo();
        psicologoBuscado.setCrp(crp);
        psicologoBuscado.setSenha(senha);
    }

    @Test
    void testeLogarPaciente(){

        Mockito.when(pacienteUseCase.consultarPorEmail(Mockito.any())).thenReturn(pacienteBuscado);
        Mockito.when(criptografiaUseCase.validarSenha(Mockito.any(), Mockito.any())).thenReturn(true);
        Mockito.when(autenticacaoUseCase.gerarTokenPaciente(Mockito.any())).thenReturn(token);

        LoginRespostaDto loginTeste = useCase.logarPaciente(email, senha);

        LoginRespostaValidator.validaLoginRespostaDto(
                LoginRespostaBuilder.criarLoginRespostaPaciente(pacienteBuscado, token),
                loginTeste
        );

        Mockito.verify(pacienteUseCase).consultarPorEmail(Mockito.any());
        Mockito.verify(criptografiaUseCase).validarSenha(Mockito.any(), Mockito.any());
        Mockito.verify(autenticacaoUseCase).gerarTokenPaciente(Mockito.any());
    }

    @Test
    void testePacienteSenhaInvalidaException(){

        Mockito.when(pacienteUseCase.consultarPorEmail(Mockito.any())).thenReturn(pacienteBuscado);
        Mockito.when(criptografiaUseCase.validarSenha(Mockito.any(), Mockito.any())).thenReturn(false);
        Mockito.when(autenticacaoUseCase.gerarTokenPaciente(Mockito.any())).thenReturn(null);

        SenhaInvalidaException exception = Assertions.assertThrows(
                SenhaInvalidaException.class, () -> useCase.logarPaciente(email, senha)
        );

        Assertions.assertEquals(LoginUseCase.MENSAGEM_SENHA_INVALIDA, exception.getMessage());

        Mockito.verify(pacienteUseCase).consultarPorEmail(Mockito.any());
        Mockito.verify(criptografiaUseCase).validarSenha(Mockito.any(), Mockito.any());
        Mockito.verify(autenticacaoUseCase).gerarTokenPaciente(Mockito.any());

    }

    @Test
    void testeLogarPsicologo(){

        Mockito.when(psicologoUseCase.consultarPorCrp(Mockito.any())).thenReturn(psicologoBuscado);
        Mockito.when(criptografiaUseCase.validarSenha(Mockito.any(), Mockito.any())).thenReturn(true);
        Mockito.when(autenticacaoUseCase.gerarTokenPsicologo(Mockito.any())).thenReturn(token);

        LoginRespostaDto loginTeste = useCase.logarPsicologo(crp, senha);

        LoginRespostaValidator.validaLoginRespostaDto(
                LoginRespostaBuilder.criarLoginRespostaPsicologo(psicologoBuscado, token),
                loginTeste
        );

        Mockito.verify(psicologoUseCase).consultarPorCrp(Mockito.any());
        Mockito.verify(criptografiaUseCase).validarSenha(Mockito.any(), Mockito.any());
        Mockito.verify(autenticacaoUseCase).gerarTokenPsicologo(Mockito.any());
    }

    @Test
    void testePsicologoSenhaInvalidaException(){

        Mockito.when(psicologoUseCase.consultarPorCrp(Mockito.any())).thenReturn(psicologoBuscado);
        Mockito.when(criptografiaUseCase.validarSenha(Mockito.any(), Mockito.any())).thenReturn(false);
        Mockito.when(autenticacaoUseCase.gerarTokenPsicologo(Mockito.any())).thenReturn(null);

        SenhaInvalidaException exception = Assertions.assertThrows(
                SenhaInvalidaException.class, () -> useCase.logarPsicologo(crp, senha)
        );

        Assertions.assertEquals(LoginUseCase.MENSAGEM_SENHA_INVALIDA, exception.getMessage());

        Mockito.verify(psicologoUseCase).consultarPorCrp(Mockito.any());
        Mockito.verify(criptografiaUseCase).validarSenha(Mockito.any(), Mockito.any());
        Mockito.verify(autenticacaoUseCase).gerarTokenPsicologo(Mockito.any());
    }
}
