package com.liratech.helppsico.builders;

import com.liratech.helppsico.entrypoint.dto.consulta.ConsultaDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ConsultaDtoBuilder {
    public static ConsultaDto criarConsultaDto(){
        return ConsultaDto.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoDtoBuilder.criarPsicologoDto())
                .paciente(PacienteDtoBuilder.criarPacienteDto())
                .dataHora(LocalDateTime.now())
                .valor(new BigDecimal(150.5))
                .endereco(EnderecoDtoBuilder.criarEnderecoDto())
                .finalizada(false)
                .build();
    }
}
