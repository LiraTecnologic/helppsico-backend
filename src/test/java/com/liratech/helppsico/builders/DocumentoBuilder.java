package com.liratech.helppsico.builders;

import com.liratech.helppsico.application.usecases.dto.DadosGeraisDocumentoDto;
import com.liratech.helppsico.domain.documento.*;

import com.liratech.helppsico.entrypoint.dto.documento.AtestadoDto;
import com.liratech.helppsico.entrypoint.mapper.EnderecoMapper;
import com.liratech.helppsico.entrypoint.mapper.PacienteMapper;
import com.liratech.helppsico.entrypoint.mapper.PsicologoMapper;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.AtestadoEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class DocumentoBuilder {

    private static PacienteMapper pacienteMapper;
    private static PsicologoMapper psicologoMapper;
    private static EnderecoMapper enderecoMapper;

    public static Atestado criarAtestadoDadosGerais(DadosGeraisDocumentoDto dadosGeraisDocumentoDto) {
        return new Atestado(
                UUID.randomUUID(),
                pacienteMapper.paraDomain(dadosGeraisDocumentoDto.getPaciente()),
                psicologoMapper.paraDomain(dadosGeraisDocumentoDto.getPsicologo()),
                dadosGeraisDocumentoDto.getDataEmissao(),
                dadosGeraisDocumentoDto.getDataValidade(),
                dadosGeraisDocumentoDto.getAssinaturaPsicologo(),
                dadosGeraisDocumentoDto.getDataAtendimento(),
                enderecoMapper.paraDomain(dadosGeraisDocumentoDto.getLocal()),
                dadosGeraisDocumentoDto.getDescricao(),
                dadosGeraisDocumentoDto.getDescricaoEstadoPsicologico(),
                dadosGeraisDocumentoDto.getPeriodoAfastamento(),
                dadosGeraisDocumentoDto.getFinalidade()
        );
    }

    public static Declaracao criarDeclaracaoDadosGerais(DadosGeraisDocumentoDto dadosGeraisDocumentoDto) {
        return new Declaracao(
                UUID.randomUUID(),
                pacienteMapper.paraDomain(dadosGeraisDocumentoDto.getPaciente()),
                psicologoMapper.paraDomain(dadosGeraisDocumentoDto.getPsicologo()),
                dadosGeraisDocumentoDto.getDataEmissao(),
                dadosGeraisDocumentoDto.getDataValidade(),
                dadosGeraisDocumentoDto.getAssinaturaPsicologo(),
                dadosGeraisDocumentoDto.getMotivo(),
                dadosGeraisDocumentoDto.getDescricao(),
                dadosGeraisDocumentoDto.getFinalidade()
        );
    }

    public static RelatorioPsicologico criarRelatorioPsicologicoDadosGerais(DadosGeraisDocumentoDto dadosGeraisDocumentoDto) {
        return new RelatorioPsicologico(
                UUID.randomUUID(),
                pacienteMapper.paraDomain(dadosGeraisDocumentoDto.getPaciente()),
                psicologoMapper.paraDomain(dadosGeraisDocumentoDto.getPsicologo()),
                dadosGeraisDocumentoDto.getDataEmissao(),
                dadosGeraisDocumentoDto.getDataValidade(),
                dadosGeraisDocumentoDto.getAssinaturaPsicologo(),
                dadosGeraisDocumentoDto.getSolicitante(),
                dadosGeraisDocumentoDto.getObjetivo(),
                dadosGeraisDocumentoDto.getHistorico(),
                dadosGeraisDocumentoDto.getProcedimentosUtilizados(),
                dadosGeraisDocumentoDto.getDescricaoResultados(),
                dadosGeraisDocumentoDto.getConclusao(),
                dadosGeraisDocumentoDto.getRecomendacoes(),
                dadosGeraisDocumentoDto.getSigilo()
        );
    }

    public static LaudoPsicologico criarLaudoPsicologicoDadosGerais(DadosGeraisDocumentoDto dadosGeraisDocumentoDto) {
        return new LaudoPsicologico(
                UUID.randomUUID(),
                pacienteMapper.paraDomain(dadosGeraisDocumentoDto.getPaciente()),
                psicologoMapper.paraDomain(dadosGeraisDocumentoDto.getPsicologo()),
                dadosGeraisDocumentoDto.getDataEmissao(),
                dadosGeraisDocumentoDto.getDataValidade(),
                dadosGeraisDocumentoDto.getAssinaturaPsicologo(),
                dadosGeraisDocumentoDto.getSolicitante(),
                dadosGeraisDocumentoDto.getObjetivo(),
                dadosGeraisDocumentoDto.getHistorico(),
                dadosGeraisDocumentoDto.getProcedimentosUtilizados(),
                dadosGeraisDocumentoDto.getDescricaoResultados(),
                dadosGeraisDocumentoDto.getConclusao(),
                dadosGeraisDocumentoDto.getRespostaDemanda(),
                dadosGeraisDocumentoDto.getRecomendacoes(),
                dadosGeraisDocumentoDto.getSigilo()
        );
    }

    public static ParecerPsicologico criarParecerPsicologicoDadosGerais(DadosGeraisDocumentoDto dadosGeraisDocumentoDto) {
        return new ParecerPsicologico(
                UUID.randomUUID(),
                pacienteMapper.paraDomain(dadosGeraisDocumentoDto.getPaciente()),
                psicologoMapper.paraDomain(dadosGeraisDocumentoDto.getPsicologo()),
                dadosGeraisDocumentoDto.getDataEmissao(),
                dadosGeraisDocumentoDto.getDataValidade(),
                dadosGeraisDocumentoDto.getAssinaturaPsicologo(),
                dadosGeraisDocumentoDto.getSolicitante(),
                dadosGeraisDocumentoDto.getObjetivo(),
                dadosGeraisDocumentoDto.getConclusao(),
                dadosGeraisDocumentoDto.getSigilo(),
                dadosGeraisDocumentoDto.getContextualizacao(),
                dadosGeraisDocumentoDto.getFundamentacao(),
                dadosGeraisDocumentoDto.getAnaliseDoCaso()
        );
    }

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
