package com.liratech.helppsico.entrypoint.dto.consulta;

import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.entrypoint.dto.Paciente;
import com.liratech.helppsico.entrypoint.dto.psicologo.Psicologo;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public class ConsultaDto {
    private UUID id;
    private Psicologo psicologo;
    private Paciente paciente;
    private BigDecimal valor;
    private LocalDateTime dataHora;
    private EnderecoDto endereco;
    private Boolean finalizada;
}
