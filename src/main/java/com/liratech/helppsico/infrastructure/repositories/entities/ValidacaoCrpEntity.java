package com.liratech.helppsico.infrastructure.repositories.entities;

import com.liratech.helppsico.domain.Psicologo;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "ValidacaoCrp")
@Table(name = "validacoes_crp")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ValidacaoCrpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "id_psicologo")
    private PsicologoEntity psicologo;

    private String crp;
    private String motivoReprova;
}
