package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.paciente.PacienteNaoEncontradoException;
import com.liratech.helppsico.application.exceptions.psicologo.PsicologoNaoEncontradoException;
import com.liratech.helppsico.application.exceptions.solicitacaoDocumento.SolicitacaoDocumentoNaoEncontradoException;
import com.liratech.helppsico.application.gateways.SolicitacaoDocumentoGateway;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SolicitacaoDocumentoUseCase {
    private final PsicologoUseCase psicologoUseCase;
    private final PacienteUseCase pacienteUseCase;
    private final SolicitacaoDocumentoGateway gateway;

    public static final String MENSAGEM_SOLICITACAO_DOCUMENTO_NAO_ENCONTRADO = "Solicitação de Docuemnto não encontrado";

    public SolicitacaoDocumento criarSolicitacao(SolicitacaoDocumento solicitacao){
        log.info("Cadastro da Solicitação. Solicitação nova: {}", solicitacao);

        Paciente paciente = pacienteUseCase.consultarPorId(solicitacao.getPaciente().getId());
        solicitacao.setPaciente(paciente);

        Psicologo psicologo = psicologoUseCase.consultarPorId(solicitacao.getPsicologo().getId());
        solicitacao.setPsicologo(psicologo);

        SolicitacaoDocumento solicitacaoDocumento = gateway.salvar(solicitacao);

        log.info("Solicitação cadastrada com sucesso. Solicitação: {}", solicitacaoDocumento);
        return solicitacaoDocumento;
    }

    public SolicitacaoDocumento buscarPorId(UUID id){
        log.info("Buscando Solicitação de Documento pelo ID. ID requisitado: {}", id);

        Optional<SolicitacaoDocumento> solicitacaoOptional = gateway.consultarPorId(id);
        if(solicitacaoOptional.isEmpty()){
            throw new SolicitacaoDocumentoNaoEncontradoException(MENSAGEM_SOLICITACAO_DOCUMENTO_NAO_ENCONTRADO);
        }

        SolicitacaoDocumento solicitacaoEncontrada = solicitacaoOptional.get();
        log.info("Solicitação de Documento encontrada com sucesso. Retorno gerado: {}", solicitacaoEncontrada);

        return solicitacaoEncontrada;
    }

    public void deletar(UUID id){
        log.info("ID enviado pra deletar Solicitação. ID: {}", id);
        buscarPorId(id);

        log.info("Solicitação deletada com sucesso.");
        gateway.deletar(id);
    }
}
