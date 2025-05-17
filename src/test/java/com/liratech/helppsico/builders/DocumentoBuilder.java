package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.HorarioPsicologo;
import com.liratech.helppsico.domain.documento.Atestado;

import com.liratech.helppsico.domain.documento.Documento;
import com.liratech.helppsico.entrypoint.dto.documento.AtestadoDto;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.AtestadoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DocumentoBuilder {
    public static Atestado criarAtestado() {
        return Atestado.builder()
                .id(UUID.randomUUID())
                .dataEmissao(LocalDate.now())
                .dataValidade(LocalDate.now().plusDays(10))
                .assinaturaPsicologo("Dra. Ana")
                .paciente(PacienteBuilder.criarPaciente())
                .psicologo(PsicologoBuilder.criarPsicologo())
                .dataAtendimento(LocalDate.now())
                .local(EnderecoBuilder.criarEndereco())
                .descricao("Apenas para cadastrar")
                .descricaoEstadoPsicologico("Louco")
                .periodoAfastamento("13 dias")
                .finalidade("Recuperação da sanidade")
                .build();
    }

    public static AtestadoEntity criarAtestadoEntity() {
        return AtestadoEntity.builder()
                .id(UUID.randomUUID())
                .dataEmissao(LocalDate.now())
                .dataValidade(LocalDate.now().plusDays(10))
                .assinaturaPsicologo("Dra. Ana")
                .paciente(PacienteBuilder.criarPacienteEntity())
                .psicologo(PsicologoBuilder.criarPsicologoEntity())
                .dataAtendimento(LocalDate.now())
                .local(EnderecoBuilder.criarEnderecoEntity())
                .descricao("Apenas para cadastrar")
                .descricaoEstadoPsicologico("Louco")
                .periodoAfastamento("13 dias")
                .finalidade("Recuperação da sanidade")
                .build();
    }

    public static AtestadoDto criarAtestadoDto() {
        return AtestadoDto.builder()
                .id(UUID.randomUUID())
                .dataEmissao(LocalDate.now())
                .dataValidade(LocalDate.now().plusDays(10))
                .assinaturaPsicologo("Dra. Ana")
                .paciente(PacienteBuilder.criarPacienteDto())
                .psicologo(PsicologoBuilder.criarPsicologoDto())
                .dataAtendimento(LocalDate.now())
                .local(EnderecoBuilder.criarEnderecoDto())
                .descricao("Apenas para cadastrar")
                .descricaoEstadoPsicologico("Louco")
                .periodoAfastamento("13 dias")
                .finalidade("Recuperação da sanidade")
                .build();
    }

    public static Page<Documento> criarPageDeDocumento() {
        List<Documento> documentolist = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            documentolist.add(criarAtestado());
        }

        return transformarListaEmPagina(documentolist, PageRequest.of(0,10));
    }

    private static Page<Documento> transformarListaEmPagina(List<Documento> lista, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), lista.size());

        List<Documento> sublist = lista.subList(start, end);

        return new PageImpl<>(sublist, pageable, lista.size());
    }
}
