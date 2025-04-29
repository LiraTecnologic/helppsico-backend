package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.login.SenhaInvalidaException;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
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

    public LoginRespostaDto logarPaciente(String email, String senha){
        log.info("Iniciando processo de login do paciente. Email:{}", email);

        Paciente pacienteBuscado = pacienteUseCase.consultarPorEmail(email);

        if (!criptografiaUseCase.validarSenha(senha, pacienteBuscado.getSenha())){
            throw new SenhaInvalidaException(MENSAGEM_SENHA_INVALIDA);
        }

        String token = autenticacaoUseCase.gerarTokenPaciente(pacienteBuscado);

        log.info("Processo de login finalizado.");

        return LoginRespostaDto.builder()
                .idUsuario(pacienteBuscado.getId())
                .email(email)
                .token(token)
                .build();
    }

    public LoginRespostaDto logarPsicologo(String crp, String senha){
        log.info("Iniciando processo de login do psicologo. Crp:{}", crp);

        Psicologo psicologoBuscado = psicologoUseCase.consultarPorCrp(crp);

        if (!criptografiaUseCase.validarSenha(senha, psicologoBuscado.getSenha())){
            throw new SenhaInvalidaException(MENSAGEM_SENHA_INVALIDA);
        }

        String token = autenticacaoUseCase.gerarTokenPsicologo(psicologoBuscado);

        log.info("Processo de login finalizado.");

        return LoginRespostaDto.builder()
                .idUsuario(psicologoBuscado.getId())
                .crp(crp)
                .token(token)
                .build();
    }
}