package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.vinculo.VinculoNaoEncontradoException;
import com.liratech.helppsico.application.gateways.VinculoGateway;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
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
    private static final String ERRO_VINCULO_NAO_ENCONRADO = "Vinculo não encontrado";
    private final PsicologoUseCase psicologoUseCase;
    private final PacienteUseCase pacienteUseCase;
    private final VinculoGateway gateway;

    public Vinculo criarSolicitacaoVinculo(Vinculo vinculo){
        log.info("Iniciando criação da solicitação de vinculo: Vinculo: {}", vinculo);

        Paciente paciente = pacienteUseCase.consultarPorId(vinculo.getPaciente().getId());
        vinculo.setPaciente(paciente);

        Psicologo psicologo = psicologoUseCase.consultarPorId(vinculo.getPsicologo().getId());
        vinculo.setPsicologo(psicologo);

        Vinculo vinculoSalvo = gateway.salvar(vinculo);

        log.info("Solicitação de vinculo criada. Vinculo: {}", vinculoSalvo);
        return vinculoSalvo;
    }

    public Vinculo aceitarSolicitacao(UUID id){
        log.info("Iniciando processo para aceitar a solicitação de vinculo: Id do vinculo: {}", id);



        log.info("Solicitação de vinculo aceita. Vinculo: {}");
    }

    public void desvincular(UUID id){
        log.info("Iniciando processo de desvinculação de vinculo: Id do vinculo: {}", id);

        log.info("Solicitação de vinculo criada. Vinculo: {}");
    }

    public Page<Vinculo> listarPorIdPsicologo(UUID id, Pageable pageable){
        log.info("Iniciando listagem da solicitação de vinculo por id psicologo: Id do vinculo: {}", id);

        log.info("Solicitação de vinculo criada. Vinculo: {}");
    }

    public Vinculo consultarPorIdPaciente(UUID id){
        log.info("Iniciando busca da solicitação de vinculo por paciente: Id do vinculo: {}", id);

        log.info("Solicitação de vinculo criada. Vinculo: {}");
    }

    private Vinculo consultarPorId(UUID id){
        Optional<Vinculo> vinculo = gateway.consultarPorId(id);

        if (vinculo.isEmpty()){
            throw new VinculoNaoEncontradoException(ERRO_VINCULO_NAO_ENCONRADO);
        }

        return vinculo.get();
    }
}
