package com.liratech.helppsico.infrastructure.repositories.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "Foto")
@Table(name = "fotos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_foto")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "id_psicologo")
    private PsicologoEntity psicologo;

    @OneToOne
    @JoinColumn(name = "id_paciente")
    private PacienteEntity paciente;

    private String fotoUrl;
}
