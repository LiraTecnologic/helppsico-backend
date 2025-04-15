package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;

public interface TokenGateway {
    String gerarTokenPaciente(Paciente paciente);

    String gerarTokenPsicologo(Psicologo psicologo);

    String validarToken(String token);
}
