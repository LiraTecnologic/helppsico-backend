package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.gateways.AvaliacaoGateway;
import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AvaliacaoUseCase {

    private final PacienteUseCase pacienteUseCase;
    private final PsicologoUseCase psicologoUseCase;
    private final AvaliacaoGateway gateway;

    public Avaliacao avaliar(Avaliacao avaliacao){
        log.info("Salvando avaliação. Avaliação: {}", avaliacao);

        Psicologo psicologo = psicologoUseCase.consultarPorId(avaliacao.getPsicologo().getId());
        avaliacao.setPsicologo(psicologo);

        Paciente paciente = pacienteUseCase.consultarPorId(avaliacao.getPaciente().getId());
        avaliacao.setPaciente(paciente);

        Avaliacao avaliacaoSalva = gateway.salvar(avaliacao);

        log.info("Avaliação salva com sucesso. Avaliação salva: {}", avaliacaoSalva);

        return avaliacaoSalva;
    }

    public Page<Avaliacao> listarPorPsicologo(UUID id) {
        return ;
    }

    public Avaliacao buscarPorId(UUID id) {
        return ;
    }

    public void deletar(UUID id) {

    }
}
