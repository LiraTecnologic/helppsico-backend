package com.liratech.helppsico.domain.documento;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
public abstract class Documento {
    protected UUID id;
    protected Paciente paciente;
    protected Psicologo psicologo;
    protected LocalDate dataEmissao;
    protected LocalDate dataValidade;
    protected String assinaturaPsicologo;
}
