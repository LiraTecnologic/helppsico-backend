package com.liratech.helppsico.infrastructure.repositories.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "Avaliacao")
@Table(name = "avaliacoes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AvaliacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_avaliacao")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_psicologo")
    private PsicologoEntity psicologo;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private PacienteEntity paciente;

    public Double nota;
    public String comentario;
}
