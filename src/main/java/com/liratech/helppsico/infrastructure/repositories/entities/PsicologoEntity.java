package com.liratech.helppsico.infrastructure.repositories.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "Psicologo")
@Table(name = "psicologos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PsicologoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_psicologo")
    private UUID id;

    private String nome;
    private String crp;
    private String cpf;
    private String email;
    private String telefone;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private String senha;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private EnderecoEntity enderecoAtendimento;

    @Enumerated(EnumType.STRING)
    private TipoGeneroEntity genero;

    @Column(name = "foto_url")
    private String fotoUrl;

    private String biografia;
}
