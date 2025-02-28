package com.liratech.helppsico.infrastructure.repositories.entities.documento;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Declaracao")
@Table(name = "declaracoes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class DeclaracaoEntity {
    private String motivo;
    private String descricao;
    private String finalidade;
}
