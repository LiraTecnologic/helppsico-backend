package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.login.SenhaInvalidaException;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.entrypoint.dto.LoginDto;
import com.liratech.helppsico.entrypoint.dto.LoginRespostaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginUseCase {
    private final PacienteUseCase pacienteUseCase;
    private final PsicologoUseCase psicologoUseCase;
    private final AutenticacaoUseCase autenticacaoUseCase;
    private final CriptografiaUseCase criptografiaUseCase;

    public static final String MENSAGEM_SENHA_INVALIDA = "Senha do usuario inválida.";

    public LoginRespostaDto logarPaciente(String email, String senha, String token){
        log.info("Iniciando processo de login do paciente. Email:{}, Senha:{}", email, senha);

        Paciente pacienteBuscado = pacienteUseCase.consultarPorEmail(email);

        if (!criptografiaUseCase.validarSenha(senha, pacienteBuscado.getSenha())){
            throw new SenhaInvalidaException(MENSAGEM_SENHA_INVALIDA);
        }

        autenticacaoUseCase.validarToken(token);

        return
    }

    public LoginDto logarPsicologo(String idPsicologo, String token){

    }
}