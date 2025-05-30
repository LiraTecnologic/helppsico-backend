package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.vinculo.VinculoInvalidoException;
import com.liratech.helppsico.application.exceptions.vinculo.VinculoNaoEncontradoException;
import com.liratech.helppsico.application.gateways.VinculoGateway;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.StatusVinculo;
import com.liratech.helppsico.domain.Vinculo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class VinculoUseCase {
    private final PsicologoUseCase psicologoUseCase;
    private final PacienteUseCase pacienteUseCase;
    private final VinculoGateway gateway;
    public static final String ERRO_VINCULO_NAO_ENCONRADO = "Vinculo não encontrado.";
    public static final String ERRO_VINCULO_INVALIDO = "Status do vinculo inválido.";

    public Vinculo criarSolicitacaoVinculo(Vinculo vinculo){
        log.info("Iniciando criação da solicitação de vinculo: Vinculo: {}", vinculo);

        if (vinculo.getStatus() != StatusVinculo.PENDENTE){
            throw new VinculoInvalidoException(ERRO_VINCULO_INVALIDO);
        }

        consultarPorPacientePsicologo(vinculo.getPaciente().getId(), vinculo.getPsicologo().getId());

        Paciente paciente = pacienteUseCase.consultarPorId(vinculo.getPaciente().getId());
        Psicologo psicologo = psicologoUseCase.consultarPorId(vinculo.getPsicologo().getId());

        vinculo.setPaciente(paciente);
        vinculo.setPsicologo(psicologo);

        Vinculo vinculoSalvo = gateway.salvar(vinculo);

        log.info("Solicitação de vinculo criada. Vinculo: {}", vinculoSalvo);
        return vinculoSalvo;
    }

    public Vinculo aceitarSolicitacao(UUID id){
        log.info("Iniciando processo para aceitar a solicitação de vinculo: Id do vinculo: {}", id);

        Vinculo vinculo = consultarPorId(id);
        vinculo.setStatus(StatusVinculo.ATIVO);

        Vinculo vinculoSalvo = gateway.salvar(vinculo);

        log.info("Solicitação de vinculo aceita. Vinculo: {}", vinculoSalvo);
        return vinculoSalvo;
    }

    public Vinculo recusarSolicitacao(UUID id) {
        log.info("Iniciando processo para recusar a solicitação de vinculo: Id do vinculo: {}", id);

        Vinculo vinculo = consultarPorId(id);
        vinculo.setStatus(StatusVinculo.RECUSADO);

        Vinculo vinculoSalvo = gateway.salvar(vinculo);

        log.info("Solicitação de vinculo recusada. Vinculo: {}", vinculoSalvo);
        return vinculoSalvo;
    }

    public void desvincular(UUID id){
        log.info("Iniciando processo de desvinculação. Id do vinculo: {}", id);

        consultarPorId(id);
        gateway.deletar(id);

        log.info("Desvinculado com sucesso.");
    }

    public Page<Vinculo> listarPorIdPsicologo(UUID id, Pageable pageable){
        log.info("Iniciando listagem da solicitação de vinculo por id psicologo. Id do psicologo: {}", id);

        psicologoUseCase.consultarPorId(id);
        Page<Vinculo> vinculos = gateway.listarPorIdPsicologo(id, pageable);

        log.info("Listagem de solicitações completa. Vinculos: {}", vinculos);
        return vinculos;
    }

    public Page<Vinculo> listarPorIdPaciente(UUID idPaciente, Pageable pageable){
        log.info("Iniciando listagem da solicitação de vinculo por paciente. Id do paciente: {}", idPaciente);

        pacienteUseCase.consultarPorId(idPaciente);
        Page<Vinculo> vinculos = gateway.listarPorIdPaciente(idPaciente, pageable);

        log.info("Listagem de solicitações completa. Listagem: {}", vinculos);
        return vinculos;
    }

    private Vinculo consultarPorPacientePsicologo(UUID idPaciente, UUID idPsicologo){
        Optional<Vinculo> vinculoOptional = gateway.consultarPorPacientePsicologo(idPaciente, idPsicologo);

        if (vinculoOptional.isEmpty()){
            throw new VinculoNaoEncontradoException(ERRO_VINCULO_NAO_ENCONRADO);
        }

        return vinculoOptional.get();
    }

    private Vinculo consultarPorId(UUID id){
        Optional<Vinculo> vinculo = gateway.consultarPorId(id);

        if (vinculo.isEmpty()){
            throw new VinculoNaoEncontradoException(ERRO_VINCULO_NAO_ENCONRADO);
        }

        return vinculo.get();
    }
}
