package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.TipoDocumentoInvalidoException;
import com.liratech.helppsico.application.gateways.DocumentoGateway;
import com.liratech.helppsico.application.usecases.dto.DadosGeraisDocumentoDto;
import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.documento.*;
import com.liratech.helppsico.entrypoint.mapper.EnderecoMapper;
import com.liratech.helppsico.entrypoint.mapper.PacienteMapper;
import com.liratech.helppsico.entrypoint.mapper.PsicologoMapper;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentoUseCase {
    private final SolicitacaoDocumentoUseCase solicitacaoDocumentoUseCase;
    private final PacienteUseCase pacienteUseCase;
    private final PacienteMapper pacienteMapper;
    private final PsicologoUseCase psicologoUseCase;
    private final PsicologoMapper psicologoMapper;
    private final EnderecoUseCase enderecoUseCase;
    private final EnderecoMapper enderecoMapper;
    private final DocumentoGateway gateway;
    private final DocumentoFactory factory;

    public Documento salvar(UUID idSolicitacao, DadosGeraisDocumentoDto dadosGeraisDocumentoDto) {
        log.info("Iniciando salvamento do documento. ID da solicitação: {}, Dados gerais: {}", idSolicitacao, dadosGeraisDocumentoDto);

        SolicitacaoDocumento solicitacaoDocumento = solicitacaoDocumentoUseCase.buscarPorId(idSolicitacao);
        TipoDocumento tipoDocumento = solicitacaoDocumento.getTipoDocumento();

        Paciente paciente = pacienteUseCase.consultarPorId(dadosGeraisDocumentoDto.getPaciente().getId());
        Psicologo psicologo = psicologoUseCase.consultarPorId(dadosGeraisDocumentoDto.getPsicologo().getId());
        Endereco endereco = enderecoUseCase.consultarPorId(dadosGeraisDocumentoDto.getLocal().getId());

        dadosGeraisDocumentoDto.setLocal(enderecoMapper.paraDto(endereco));
        dadosGeraisDocumentoDto.setPaciente(pacienteMapper.paraDto(paciente));
        dadosGeraisDocumentoDto.setPsicologo(psicologoMapper.paraDto(psicologo));

        Documento documento = factory.criar(dadosGeraisDocumentoDto, tipoDocumento);

        Documento documentoSalvo = gateway.salvar(documento);

        solicitacaoDocumentoUseCase.deletar(idSolicitacao);

        log.info("Documento criado e salvo com sucesso. Documento: {}", documentoSalvo);
        return documentoSalvo;
    }

    public Page<Documento> listarPorPaciente (UUID idPaciente, Pageable pageable) {
        log.info("Iniciando processo para listar os documentos.");

        pacienteUseCase.consultarPorId(idPaciente);

        Page<Documento> listagemDoc = gateway.listarPorPaciente(idPaciente, pageable);

        log.info("Listagem de documentos completa. Listagem: {}", listagemDoc);
        return listagemDoc;
    }
}
