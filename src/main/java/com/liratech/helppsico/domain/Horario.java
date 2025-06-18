package com.liratech.helppsico.domain;

import com.liratech.helppsico.application.gateways.HorarioGateway;
import lombok.*;

import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Horario {
    private UUID id;
    private DiaSemana diaSemana;
    private LocalTime inicio;
    private LocalTime fim;
    private Boolean disponivel;
    private Psicologo psicologo;

    public void alterarDados(Horario horarioNovo){
        this.diaSemana = horarioNovo.getDiaSemana();
        this.inicio = horarioNovo.getInicio();
        this.fim = horarioNovo.getFim();
        this.disponivel = horarioNovo.getDisponivel();
    }
}
