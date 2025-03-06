package com.liratech.helppsico.entrypoint.dto.documento;

import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentoDto {
    protected UUID id;
    protected PacienteDto paciente;
    protected PsicologoDto psicologo;
    protected LocalDate dataEmissao;
    protected LocalDate dataValidade;
    protected String assinaturaPsicologo;
}
