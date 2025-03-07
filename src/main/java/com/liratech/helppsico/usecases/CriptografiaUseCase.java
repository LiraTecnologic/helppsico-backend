package com.liratech.helppsico.usecases;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CriptografiaUseCase {

    private final PasswordEncoder passwordEncoder;

    public String criptografar(String senha){
        return passwordEncoder.encode(senha);
    }

    public Boolean validarSenha(String senha, String senhaHash){
        return passwordEncoder.matches(senha, senhaHash);
    }
}
