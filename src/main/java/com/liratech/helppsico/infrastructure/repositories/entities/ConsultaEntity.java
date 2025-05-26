package com.liratech.helppsico.infrastructure.repositories.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Consulta")
@Table(name = "consultas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ConsultaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_consulta")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_psicologo")
    private PsicologoEntity psicologo;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private PacienteEntity paciente;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    private BigDecimal valor;

    @OneToOne
    @JoinColumn(name = "id_endereco")
    private EnderecoEntity endereco;

    private Boolean finalizada;
}
