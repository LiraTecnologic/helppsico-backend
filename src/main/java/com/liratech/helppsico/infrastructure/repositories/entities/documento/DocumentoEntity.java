package com.liratech.helppsico.infrastructure.repositories.entities.documento;

import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@MappedSuperclass
@Table(name = "documentos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
public abstract class DocumentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_documento")
    protected UUID id;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    protected PacienteEntity paciente;

    @ManyToOne
    @JoinColumn(name = "id_psicologo")
    protected PsicologoEntity psicologo;

    @Column(name = "data_emissao")
    protected LocalDate dataEmissao;

    @Column(name = "data_validade")
    protected LocalDate dataValidade;

    @Column(name = "assinatura_psicologo")
    protected String assinaturaPsicologo;
}
