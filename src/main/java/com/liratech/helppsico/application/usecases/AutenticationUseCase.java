package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.gateways.TokenGateway;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AutenticationUseCase {
    private final TokenGateway gateway;

    public static final String MENSAGEM_GERACAO_TOKEN_INVALIDA = "Geração de token inválida.";
    public static final String MENSAGEM_TOKEN_NULO = "Token recebido é nulo ou vazio.";

    public String gerarTokenPaciente(Paciente paciente){
        log.info("Gerando token do paciente: {}", paciente);

        String tokenPaciente = gateway.gerarTokenPaciente(paciente);

        if (tokenPaciente == null || tokenPaciente.isBlank()){
            log.error("Falha na geração do token para paciente: {}", paciente);
            throw new TokenInvalidoException(MENSAGEM_GERACAO_TOKEN_INVALIDA);
        }

        return tokenPaciente;
    }

    public String gerarTokenPsicologo(Psicologo psicologo){
        log.info("Gerando token do psicologo: {}", psicologo);

        String tokenPsicologo = gateway.gerarTokenPsicologo(psicologo);

        if (tokenPsicologo == null || tokenPsicologo.isBlank()){
            log.error("Falha na geração do token para psicologo: {}", psicologo);
            throw new TokenInvalidoException(MENSAGEM_GERACAO_TOKEN_INVALIDA);
        }

        return tokenPsicologo;
    }

    public String validarToken(String token){
        log.info("Iniciando validação de token de usuario");

        if (token == null || token.isBlank()) {
            log.warn("Token nulo ou vazio recebido para validação");
            throw new TokenInvalidoException(MENSAGEM_TOKEN_NULO);
        }

        String tipoUsuario = gateway.validarToken(token);

        log.info("Token válido para o tipo de usuario: {}", tipoUsuario);
        return tipoUsuario;
    }
}
