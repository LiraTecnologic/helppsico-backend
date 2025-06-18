package com.liratech.helppsico.infrastructure.repositories.entities.documento;

import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "Declaracao")
@Table(name = "declaracoes")
@NoArgsConstructor
@Getter
@ToString
public class DeclaracaoEntity extends DocumentoEntity {
    private String motivo;
    private String descricao;
    private String finalidade;

    public DeclaracaoEntity (UUID id, PacienteEntity paciente, PsicologoEntity psicologo, LocalDate dataEmissao, LocalDate dataValidade, String assinaturaPsicologo, String motivo, String descricao, String finalidade){
        super(id, paciente, psicologo, dataEmissao, dataValidade, assinaturaPsicologo);
        this.motivo = motivo;
        this.descricao = descricao;
        this.finalidade = finalidade;
    }
}
