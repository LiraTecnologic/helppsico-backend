package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.entrypoint.dto.LoginRespostaDto;

public class LoginRespostaBuilder {
    public static LoginRespostaDto criarLoginRespostaPaciente(Paciente paciente, String token){
        return LoginRespostaDto.builder()
                .idUsuario(paciente.getId())
                .email(paciente.getEmail())
                .token(token)
                .build();
    }

    public static LoginRespostaDto criarLoginRespostaPsicologo(Psicologo psicologo, String token){
        return LoginRespostaDto.builder()
                .idUsuario(psicologo.getId())
                .crp(psicologo.getCpf())
                .token(token)
                .build();
    }
}
