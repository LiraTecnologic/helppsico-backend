package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.SolicitacaoDocumentoEntity;

import java.util.UUID;

public class SolicitacaoDocumentoBuilder {
    public static SolicitacaoDocumento criarSolicitacaoDocumento(){
        return SolicitacaoDocumento.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologo())
                .paciente(PacienteBuilder.criarPaciente())
                .tipoDocumento()
                .build();
    }

    public static SolicitacaoDocumentoEntity criarSolicitacaoDocumentoEntity(){
        return SolicitacaoDocumentoEntity.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologoEntity())
                .paciente(PacienteBuilder.criarPacienteEntity())
                .tipoDocumento()
                .build();
    }
}
