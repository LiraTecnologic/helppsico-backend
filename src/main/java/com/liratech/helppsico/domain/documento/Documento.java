package com.liratech.helppsico.domain.documento;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class Documento {
    private UUID id;
    private Paciente paciente;
    private Psicologo psicologo;
    private LocalDate dataEmissao;
    private LocalDate dataValidade;
    private String assinaturaPsicologo;
}
