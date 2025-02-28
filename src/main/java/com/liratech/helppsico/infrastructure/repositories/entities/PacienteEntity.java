package com.liratech.helppsico.infrastructure.repositories.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PacienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_paciente")
    private UUID id;

    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private String senha;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private EnderecoEntity endereco;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Enumerated(EnumType.STRING)
    private TipoGeneroEntity genero;

    @Column(name = "foto_path")
    private String fotoPath;
}
