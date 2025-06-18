package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import com.liratech.helppsico.domain.documento.TipoDocumento;
import com.liratech.helppsico.entrypoint.dto.documento.SolicitacaoDocumentoDto;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.SolicitacaoDocumentoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
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

    public static Page<SolicitacaoDocumento> criarPageDeSolicitacaoDocumento() {
        List<SolicitacaoDocumento> solicitacaoDocumentoList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            solicitacaoDocumentoList.add(criarSolicitacaoDocumento());
        }

        return transformarListaEmPagina(solicitacaoDocumentoList, PageRequest.of(0,10));
    }

    public static Page<SolicitacaoDocumentoEntity> criarPageDeSolicitacaoDocumentoEntity() {
        List<SolicitacaoDocumentoEntity> solicitacaoDocumentoList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            solicitacaoDocumentoList.add(criarSolicitacaoDocumentoEntity());
        }

        return transformarListaEmPaginaEntity(solicitacaoDocumentoList, PageRequest.of(0,10));
    }

    private static Page<SolicitacaoDocumento> transformarListaEmPagina(List<SolicitacaoDocumento> lista, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), lista.size());

        List<SolicitacaoDocumento> sublist = lista.subList(start, end);

        return new PageImpl<>(sublist, pageable, lista.size());
    }

    private static Page<SolicitacaoDocumentoEntity> transformarListaEmPaginaEntity(List<SolicitacaoDocumentoEntity> lista, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), lista.size());

        List<SolicitacaoDocumentoEntity > sublist = lista.subList(start, end);

        return new PageImpl<>(sublist, pageable, lista.size());
    }
}
