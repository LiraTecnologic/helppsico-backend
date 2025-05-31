package com.liratech.helppsico.infrastructure.repositories.entities;

import com.liratech.helppsico.domain.DiaSemana;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.UUID;

@Entity(name = "Horario")
@Table(name = "horarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class HorarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_horario")
    private UUID id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "dia_semana")
    private DiaSemana diaSemana;

    private LocalTime inicio;
    private LocalTime fim;
    private Boolean disponivel;

    @ManyToOne
    @JoinColumn(name = "id_psicologo")
    private PsicologoEntity psicologo;
}
