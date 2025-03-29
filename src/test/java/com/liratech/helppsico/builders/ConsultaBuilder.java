package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.entrypoint.dto.consulta.ConsultaDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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

    public static ConsultaDto criarConsultaDto(){
        return ConsultaDto.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologoDto())
                .paciente(PacienteBuilder.criarPacienteDto())
                .dataHora(LocalDateTime.now())
                .valor(new BigDecimal(150.5))
                .endereco(EnderecoBuilder.criarEnderecoDto())
                .finalizada(false)
                .build();
    }

    public static Consulta criarConsultaParaLista(){
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

    public static List<Consulta> criarListaConslta(){
        return List.of(criarConsulta(),criarConsultaParaLista());
    }
}
