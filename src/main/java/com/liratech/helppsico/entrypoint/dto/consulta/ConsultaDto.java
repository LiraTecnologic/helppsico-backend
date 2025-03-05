package com.liratech.helppsico.entrypoint.dto.consulta;

import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ConsultaDto {
    private UUID id;
    private PsicologoDto psicologo;
    private PacienteDto paciente;
    private BigDecimal valor;
    private LocalDateTime dataHora;
    private EnderecoDto endereco;
    private Boolean finalizada;
}
