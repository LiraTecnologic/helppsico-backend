package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.horario.HorarioNaoEncontradoException;
import com.liratech.helppsico.application.gateways.HorarioGateway;
import com.liratech.helppsico.domain.Horario;
import com.liratech.helppsico.domain.Psicologo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class HorarioUseCase {

    private final PsicologoUseCase psicologoUseCase;
    private final HorarioGateway gateway;
    public static final String ERRO_HORARIO_NAO_ENCONTRADO = "Horario não encontrado.";

    public Horario cadastrar(Horario horario){
        log.info("Iniciando o processo de cadastrar horário no sistema. Horario: {}", horario);

        Psicologo psicologo = psicologoUseCase.consultarPorId(horario.getPsicologo().getId());
        horario.setPsicologo(psicologo);

        Horario horarioSalvo = gateway.salvar(horario);

        log.info("Horario cadastrado com sucesso. Horario: {}", horarioSalvo);
        return horarioSalvo;
    }

    public Horario alterar(Horario horarioNovo, UUID idHorario){
        log.info("Iniciando alteração de dados do horario. Horario novo: {}", horarioNovo);

        Horario horarioConsultado = consultarPorId(idHorario);
        horarioConsultado.alterarDados(horarioNovo);

        Horario horarioAlterado = gateway.salvar(horarioConsultado);

        log.info("Horario alterado com sucesso. Horario alterado: {}", horarioAlterado);
        return horarioAlterado;
    }

    public List<Horario> listarPorPsicologo(UUID idPsicologo){
        log.info("Buscando todos os horarios disponíveis para o psicologo. Id do psicologo: {}", idPsicologo);

        psicologoUseCase.consultarPorId(idPsicologo);
        List<Horario> listaHorarios = gateway.listarPorPsicologo(idPsicologo);

        log.info("Horarios do psicologo buscado. Lista de horarios: {}", listaHorarios);
        return listaHorarios;
    }

    public Horario consultarPorId(UUID id){
        log.info("Buscando horario por id. Id: {}", id);

        Optional<Horario> horarioPsicologo = gateway.consultarPorId(id);
        if (horarioPsicologo.isEmpty()) {
            throw new HorarioNaoEncontradoException(ERRO_HORARIO_NAO_ENCONTRADO);
        }

        Horario horarioBuscado = horarioPsicologo.get();

        log.info("Horario buscado com sucesso: Horario: {}", horarioPsicologo);
        return horarioBuscado;
    }

    public void deletar(UUID id){
        log.info("Deletando horario por id. Id: {}", id);

        consultarPorId(id);
        gateway.deletar(id);

        log.info("Horario deletado com sucesso.");
    }

}