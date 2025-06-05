package com.liratech.helppsico.builders;

import com.liratech.helppsico.application.usecases.dto.DadosGeraisDocumentoDto;
import com.liratech.helppsico.domain.documento.*;

import com.liratech.helppsico.entrypoint.dto.documento.*;
import com.liratech.helppsico.entrypoint.mapper.EnderecoMapper;
import com.liratech.helppsico.entrypoint.mapper.PacienteMapper;
import com.liratech.helppsico.entrypoint.mapper.PsicologoMapper;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.*;
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

    private PacienteMapper pacienteMapper;
    private PsicologoMapper psicologoMapper;
    private EnderecoMapper enderecoMapper;

    public Atestado criarAtestadoDadosGerais(DadosGeraisDocumentoDto dadosGeraisDocumentoDto) {
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
    public static AtestadoDto criarAtestadoDto() {
        return new AtestadoDto(
                UUID.randomUUID(),
                PacienteBuilder.criarPacienteDto(),
                PsicologoBuilder.criarPsicologoDto(),
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                "Dra. Ana",
                LocalDate.now(),
                EnderecoBuilder.criarEnderecoDto(),
                "Apenas para cadastrar",
                "Louco",
                "13 dias",
                "Recuperação da sanidade"
        );
    }
    public static Atestado criarAtestado() {
        return new Atestado(
                UUID.randomUUID(),
                PacienteBuilder.criarPaciente(),
                PsicologoBuilder.criarPsicologo(),
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                "Dra. Ana",
                LocalDate.now(),
                EnderecoBuilder.criarEndereco(),
                "Apenas para cadastrar",
                "Louco",
                "13 dias",
                "Recuperação da sanidade"
        );
    }
    public static AtestadoEntity criarAtestadoEntity() {
        return new AtestadoEntity(
                UUID.randomUUID(),
                PacienteBuilder.criarPacienteEntity(),
                PsicologoBuilder.criarPsicologoEntity(),
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                "Dra. Ana",
                LocalDate.now(),
                EnderecoBuilder.criarEnderecoEntity(),
                "Apenas para cadastrar",
                "Louco",
                "13 dias",
                "Recuperação da sanidade"
        );
    }

    public Declaracao criarDeclaracaoDadosGerais(DadosGeraisDocumentoDto dadosGeraisDocumentoDto) {
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
    public static DeclaracaoDto criarDeclaracaoDto() {
        return new DeclaracaoDto(
                UUID.randomUUID(),
                PacienteBuilder.criarPacienteDto(),
                PsicologoBuilder.criarPsicologoDto(),
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                "Dra. Ana",
                "Comprovação de consulta",
                "Declaração simples para fins acadêmicos",
                "Justificativa de ausência"
        );
    }
    public static Declaracao criarDeclaracao() {
        return new Declaracao(
                UUID.randomUUID(),
                PacienteBuilder.criarPaciente(),
                PsicologoBuilder.criarPsicologo(),
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                "Dra. Ana",
                "Comprovação de consulta",
                "Declaração simples para fins acadêmicos",
                "Justificativa de ausência"
        );
    }
    public static DeclaracaoEntity criarDeclaracaoEntity() {
        return new DeclaracaoEntity(
                UUID.randomUUID(),
                PacienteBuilder.criarPacienteEntity(),
                PsicologoBuilder.criarPsicologoEntity(),
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                "Dra. Ana",
                "Comprovação de consulta",
                "Declaração simples para fins acadêmicos",
                "Justificativa de ausência"
        );
    }

    public RelatorioPsicologico criarRelatorioPsicologicoDadosGerais(DadosGeraisDocumentoDto dadosGeraisDocumentoDto) {
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
    public static RelatorioPsicologicoDto criarRelatorioPsicologicoDto() {
        return new RelatorioPsicologicoDto(
                UUID.randomUUID(),
                PacienteBuilder.criarPacienteDto(),
                PsicologoBuilder.criarPsicologoDto(),
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                "Dra. Ana",
                "Empresa X",
                "Acompanhar retorno ao trabalho após afastamento",
                "Histórico de burnout e ansiedade generalizada",
                "Sessões de psicoterapia, aplicação de testes e entrevistas",
                "Resultados mostram melhora significativa nos níveis de estresse",
                "Paciente apto ao retorno gradual com acompanhamento",
                "Recomendações: atividades de menor carga nas primeiras semanas",
                "Somente à área de RH e ao próprio paciente"
        );
    }
    public static RelatorioPsicologico criarRelatorioPsicologico() {
        return new RelatorioPsicologico(
                UUID.randomUUID(),
                PacienteBuilder.criarPaciente(),
                PsicologoBuilder.criarPsicologo(),
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                "Dra. Ana",
                "Empresa X",
                "Acompanhar retorno ao trabalho após afastamento",
                "Histórico de burnout e ansiedade generalizada",
                "Sessões de psicoterapia, aplicação de testes e entrevistas",
                "Resultados mostram melhora significativa nos níveis de estresse",
                "Paciente apto ao retorno gradual com acompanhamento",
                "Recomendações: atividades de menor carga nas primeiras semanas",
                "Somente à área de RH e ao próprio paciente"
        );
    }
    public static RelatorioPsicologicoEntity criarRelatorioPsicologicoEntity() {
        return new RelatorioPsicologicoEntity(
                UUID.randomUUID(),
                PacienteBuilder.criarPacienteEntity(),
                PsicologoBuilder.criarPsicologoEntity(),
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                "Dra. Ana",
                "Empresa X",
                "Acompanhar retorno ao trabalho após afastamento",
                "Histórico de burnout e ansiedade generalizada",
                "Sessões de psicoterapia, aplicação de testes e entrevistas",
                "Resultados mostram melhora significativa nos níveis de estresse",
                "Paciente apto ao retorno gradual com acompanhamento",
                "Recomendações: atividades de menor carga nas primeiras semanas",
                "Somente à área de RH e ao próprio paciente"
        );
    }

    public LaudoPsicologico criarLaudoPsicologicoDadosGerais(DadosGeraisDocumentoDto dadosGeraisDocumentoDto) {
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
    public static LaudoPsicologicoDto criarLaudoPsicologicoDto() {
        return new LaudoPsicologicoDto(
                UUID.randomUUID(),
                PacienteBuilder.criarPacienteDto(),
                PsicologoBuilder.criarPsicologoDto(),
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                "Dra. Ana",
                "Escola Municipal São João",
                "Investigar dificuldades de aprendizagem",
                "Histórico de baixo rendimento escolar desde o 1º ano",
                "Entrevistas, testes projetivos e observação em sala",
                "Resultados indicam dificuldades de atenção e memória",
                "Conclusão: Necessita acompanhamento psicopedagógico",
                "Resposta à demanda: recomenda-se reforço escolar individualizado",
                "Realizar acompanhamento trimestral e adaptação pedagógica",
                "Restrito - somente aos responsáveis legais"
        );
    }
    public static LaudoPsicologico criarLaudoPsicologico() {
        return new LaudoPsicologico(
                UUID.randomUUID(),
                PacienteBuilder.criarPaciente(),
                PsicologoBuilder.criarPsicologo(),
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                "Dra. Ana",
                "Escola Municipal São João",
                "Investigar dificuldades de aprendizagem",
                "Histórico de baixo rendimento escolar desde o 1º ano",
                "Entrevistas, testes projetivos e observação em sala",
                "Resultados indicam dificuldades de atenção e memória",
                "Conclusão: Necessita acompanhamento psicopedagógico",
                "Resposta à demanda: recomenda-se reforço escolar individualizado",
                "Realizar acompanhamento trimestral e adaptação pedagógica",
                "Restrito - somente aos responsáveis legais"
        );
    }
    public static LaudoPsicologicoEntity criarLaudoPsicologicoEntiy() {
        return new LaudoPsicologicoEntity(
                UUID.randomUUID(),
                PacienteBuilder.criarPacienteEntity(),
                PsicologoBuilder.criarPsicologoEntity(),
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                "Dra. Ana",
                "Escola Municipal São João",
                "Investigar dificuldades de aprendizagem",
                "Histórico de baixo rendimento escolar desde o 1º ano",
                "Entrevistas, testes projetivos e observação em sala",
                "Resultados indicam dificuldades de atenção e memória",
                "Conclusão: Necessita acompanhamento psicopedagógico",
                "Resposta à demanda: recomenda-se reforço escolar individualizado",
                "Realizar acompanhamento trimestral e adaptação pedagógica",
                "Restrito - somente aos responsáveis legais"
        );
    }

    public ParecerPsicologico criarParecerPsicologicoDadosGerais(DadosGeraisDocumentoDto dadosGeraisDocumentoDto) {
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
    public static ParecerPsicologicoDto criarParecerPsicologicoDto() {
        return new ParecerPsicologicoDto(
                UUID.randomUUID(),
                PacienteBuilder.criarPacienteDto(),
                PsicologoBuilder.criarPsicologoDto(),
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                "Dra. Ana",
                "Tribunal de Justiça",
                "Avaliar capacidade civil do paciente",
                "O paciente apresenta sinais de estabilidade emocional",
                "Parcial - divulgado apenas ao solicitante",
                "Paciente foi acompanhado durante 3 meses em sessões semanais",
                "Baseado na teoria cognitivo-comportamental e DSM-5",
                "Paciente demonstra evolução positiva, com relatos consistentes"
        );
    }
    public static ParecerPsicologico criarParecerPsicologico() {
        return new ParecerPsicologico(
                UUID.randomUUID(),
                PacienteBuilder.criarPaciente(),
                PsicologoBuilder.criarPsicologo(),
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                "Dra. Ana",
                "Tribunal de Justiça",
                "Avaliar capacidade civil do paciente",
                "O paciente apresenta sinais de estabilidade emocional",
                "Parcial - divulgado apenas ao solicitante",
                "Paciente foi acompanhado durante 3 meses em sessões semanais",
                "Baseado na teoria cognitivo-comportamental e DSM-5",
                "Paciente demonstra evolução positiva, com relatos consistentes"
        );
    }
    public static ParecerPsicologicoEntity criarParecerPsicologicoEntity() {
        return new ParecerPsicologicoEntity(
                UUID.randomUUID(),
                PacienteBuilder.criarPacienteEntity(),
                PsicologoBuilder.criarPsicologoEntity(),
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                "Dra. Ana",
                "Tribunal de Justiça",
                "Avaliar capacidade civil do paciente",
                "O paciente apresenta sinais de estabilidade emocional",
                "Parcial - divulgado apenas ao solicitante",
                "Paciente foi acompanhado durante 3 meses em sessões semanais",
                "Baseado na teoria cognitivo-comportamental e DSM-5",
                "Paciente demonstra evolução positiva, com relatos consistentes"
        );
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
