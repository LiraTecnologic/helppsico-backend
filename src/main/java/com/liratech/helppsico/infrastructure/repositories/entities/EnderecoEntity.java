package com.liratech.helppsico.infrastructure.repositories.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity(name = "Endereco")
@Table(name = "enderecos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_endereco", columnDefinition = "BINARY(16)")
    private UUID id;

    private String rua;
    private Integer numero;
    private String cep;
    private String cidade;
    private String estado;

}
