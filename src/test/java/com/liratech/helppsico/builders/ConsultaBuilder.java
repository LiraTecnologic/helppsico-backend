package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Consulta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ConsultaBuilder {
    public static Consulta criarConsulta(){
        return Consulta.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologo())
                .paciente(PacienteBuilder.criarPaciente())
                .dataHora(LocalDateTime.now())
                .valor(new BigDecimal(150.5))
                .endereco(EnderecoBuilder.criarEndereco())
                .finalizada(false)
                .build();
    }
}
