package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.avaliacao.AvaliacaoJaCadastradaException;
import com.liratech.helppsico.application.exceptions.avaliacao.AvaliacaoNaoEncontradaException;
import com.liratech.helppsico.application.gateways.AvaliacaoGateway;
import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AvaliacaoUseCase {

    private final PacienteUseCase pacienteUseCase;
    private final PsicologoUseCase psicologoUseCase;
    private final AvaliacaoGateway gateway;
    public static final String MENSAGEM_AVALIACAO_NAO_ENCONTRADA = "Avaliação não encontrada";
    public static final String MENSAGEM_AVALIACAO_JA_CADASTRADA = "Avaliação já está cadastrada";

    public Avaliacao avaliar(Avaliacao avaliacao){
        log.info("Salvando avaliação. Avaliação: {}", avaliacao);

        Psicologo psicologo = psicologoUseCase.consultarPorId(avaliacao.getPsicologo().getId());
        avaliacao.setPsicologo(psicologo);

        Paciente paciente = pacienteUseCase.consultarPorId(avaliacao.getPaciente().getId());
        avaliacao.setPaciente(paciente);

        Optional<Avaliacao> avaliacaoConsultada = consultarPorPacientePsicologo(paciente.getId(), psicologo.getId());

        if (avaliacaoConsultada.isPresent()){
            throw new AvaliacaoJaCadastradaException(MENSAGEM_AVALIACAO_JA_CADASTRADA);
        }

        Avaliacao avaliacaoSalva = gateway.salvar(avaliacao);

        log.info("Avaliação salva com sucesso. Avaliação salva: {}", avaliacaoSalva);

        return avaliacaoSalva;
    }

    public Optional<Avaliacao> consultarPorPacientePsicologo(UUID idPaciente, UUID idPsicologo){
        return gateway.consultarPorPacientePsicologo(idPaciente, idPsicologo);
    }

    public Page<Avaliacao> listarPorPsicologo(UUID id) {
        log.info("Buscando avaliações do psicologo pelo id. Id: {}", id);

        psicologoUseCase.consultarPorId(id);

        Page<Avaliacao> avaliacoes = gateway.listarPorPsicologo(id);

        log.info("Avaliações buscadas com sucesso. Avaliações: {}", avaliacoes);

        return avaliacoes;
    }

    public Avaliacao buscarPorId(UUID id) {
        log.info("Buscando avaliação por id. Id: {}", id);

        Optional<Avaliacao> avaliacaoOptional = gateway.buscarPorId(id);

        if (avaliacaoOptional.isEmpty()){
            throw new AvaliacaoNaoEncontradaException(MENSAGEM_AVALIACAO_NAO_ENCONTRADA);
        }

        Avaliacao avaliacaoSalva = avaliacaoOptional.get();

        log.info("Avaliação encontrada com sucesso. Avaliação encontrada: {}", avaliacaoSalva);

        return avaliacaoSalva;
    }

    public void deletar(UUID id) {
        buscarPorId(id);
        gateway.deletar(id);
    }
}
