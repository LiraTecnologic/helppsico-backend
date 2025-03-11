package com.liratech.helppsico.usecases;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CriptografiaUseCase {

    private final PasswordEncoder passwordEncoder;

    public String criptografar(String senha){
        try {
            log.info("Iniciando a criptografia da senha.");
            String senhaCriptografada = passwordEncoder.encode(senha);
            log.debug("Senha criptografada: {}", senhaCriptografada);
            return senhaCriptografada;
        } catch (Exception e){
            log.error("Erro ao criptografar a senha: {}", e.getMessage(), e);
            throw new IllegalArgumentException("Erro ao criptografar a senha.", e);
        }
    }

    public Boolean validarSenha(String senha, String senhaCriptografada) {
        log.info("Iniciando validação da senha.");
        return passwordEncoder.matches(senha, senhaCriptografada);
    }
}
