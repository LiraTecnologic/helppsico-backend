package com.liratech.helppsico.infrastructure.repositories.entities.documento;

import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "Declaracao")
@Table(name = "declaracoes")
@NoArgsConstructor
@Getter
public class DeclaracaoEntity extends DocumentoEntity {
    private String motivo;
    private String descricao;
    private String finalidade;

    public DeclaracaoEntity (UUID id, PacienteEntity paciente, PsicologoEntity psicologo, LocalDate dataEmissao, LocalDate dataValidade, String assinaturaPsicologo, String motivo, String descricao, String finalidade){
        this.id = id;
        this.paciente = paciente;
        this.psicologo = psicologo;
        this.dataEmissao = dataEmissao;
        this.dataValidade = dataValidade;
        this.assinaturaPsicologo = assinaturaPsicologo;
        this.motivo = motivo;
        this.descricao = descricao;
        this.finalidade = finalidade;
    }
}
