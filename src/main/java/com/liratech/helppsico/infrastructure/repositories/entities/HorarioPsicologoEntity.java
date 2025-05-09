package com.liratech.helppsico.infrastructure.repositories.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity(name = "HorarioPsicologo")
@Table(name = "horarios_psicologo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HorarioPsicologoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_horario_psicologo")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_psicologo")
    private PsicologoEntity psicologo;

    @OneToMany
    @JoinColumn(name = "id_horario_psicologo")
    private List<HorarioEntity> horarios;
}
