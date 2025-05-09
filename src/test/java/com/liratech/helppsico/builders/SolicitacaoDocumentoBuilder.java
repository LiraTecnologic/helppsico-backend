package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import com.liratech.helppsico.domain.documento.TipoDocumento;
import com.liratech.helppsico.entrypoint.dto.documento.SolicitacaoDocumentoDto;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.SolicitacaoDocumentoEntity;

import java.util.UUID;

public class SolicitacaoDocumentoBuilder {
    public static SolicitacaoDocumento criarSolicitacaoDocumento(){
        return SolicitacaoDocumento.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologo())
                .paciente(PacienteBuilder.criarPaciente())
                .tipoDocumento(TipoDocumento.ATESTADO)
                .build();
    }

    public static SolicitacaoDocumentoEntity criarSolicitacaoDocumentoEntity(){
        return SolicitacaoDocumentoEntity.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologoEntity())
                .paciente(PacienteBuilder.criarPacienteEntity())
                .tipoDocumento(TipoDocumento.ATESTADO)
                .build();
    }

    public static SolicitacaoDocumentoDto criarSolicitacaoDocumentoDto(){
        return SolicitacaoDocumentoDto.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologoDto())
                .paciente(PacienteBuilder.criarPacienteDto())
                .tipoDocumento(TipoDocumento.ATESTADO)
                .build();
    }
}
