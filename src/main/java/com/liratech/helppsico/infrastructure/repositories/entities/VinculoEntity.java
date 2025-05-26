package com.liratech.helppsico.infrastructure.repositories.entities;

import com.liratech.helppsico.domain.StatusVinculo;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity(name = "Vinculo")
@Table(name = "vinculos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class VinculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_vinculo")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "id_paciente")
    private PacienteEntity paciente;

    @ManyToOne
    @JoinColumn(name = "id_psicologo")
    private PsicologoEntity psicologo;

    @Enumerated(EnumType.ORDINAL)
    private StatusVinculo status;
}
