package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.entrypoint.dto.LoginDto;
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

    public LoginDto logarPaciente(String email, String senha, String token){
        //buscar paciente
        pacienteUseCase.consultarPorId();

        //descriptografar a senha

        //verificar token
    }

    public LoginDto logarPsicologo(String idPsicologo, String token){

    }
}