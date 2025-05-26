package com.liratech.helppsico.infrastructure.repositories.entities.documento;

import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "Documento")
@Table(name = "documentos")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
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
