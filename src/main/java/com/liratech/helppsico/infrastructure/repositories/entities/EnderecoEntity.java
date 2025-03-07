package com.liratech.helppsico.infrastructure.repositories.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity(name = "Endereco")
@Table(name = "enderecos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnderecoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_endereco")
    private UUID id;

    private String rua;
    private Integer numero;
    private String cep;
    private String cidade;
    private String estado;

}
