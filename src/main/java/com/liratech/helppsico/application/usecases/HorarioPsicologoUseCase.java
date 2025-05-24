package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.horario.HorarioNaoEncontradoException;
import com.liratech.helppsico.application.gateways.HorarioPsicologoGateway;
import com.liratech.helppsico.domain.HorarioPsicologo;
import com.liratech.helppsico.domain.Psicologo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class HorarioPsicologoUseCase {

    private final PsicologoUseCase psicologoUseCase;
    private final HorarioPsicologoGateway gateway;
    public static final String ERRO_HORARIO_NAO_ENCONTRADO = "Horario não encontrado.";

    public HorarioPsicologo cadastrar(HorarioPsicologo horarioPsicologo){
        log.info("Iniciando o processo de cadastrar h" +
                "orário no sistema. Horario: {}", horarioPsicologo);

        Psicologo psicologo = psicologoUseCase.consultarPorId(horarioPsicologo.getId());
        horarioPsicologo.setPsicologo(psicologo);

        HorarioPsicologo horarioSalvo = gateway.salvar(horarioPsicologo);

        log.info("Horario cadastrado com sucesso. Horario: {}", horarioSalvo);
        return horarioSalvo;
    }

    public HorarioPsicologo alterar(HorarioPsicologo horarioPsicologoNovo, UUID idHorario){
        log.info("Iniciando alteração de dados do horarioPsicologo. Horario novo: {}", horarioPsicologoNovo);

        HorarioPsicologo horarioPsicologoAlterado = consultarPorId(idHorario);
        horarioPsicologoAlterado.setHorarios(horarioPsicologoNovo.getHorarios());

        horarioPsicologoAlterado = gateway.salvar(horarioPsicologoAlterado);

        log.info("Horario alterado com sucesso. Horario alterado: {}", horarioPsicologoAlterado);
        return horarioPsicologoAlterado;
    }

    public Page<HorarioPsicologo> listarPorPsicologo(UUID idPsicologo, Pageable pageable){
        log.info("Buscando todos os horarios disponíveis para o psicologo. Id do psicologo: {}", idPsicologo);

        Page<HorarioPsicologo> listaHorarios = gateway.listarPorPsicologo(idPsicologo, pageable);

        log.info("Horarios do psicologo buscado. Lista de horarios: {}", listaHorarios);
        return listaHorarios;
    }

    public HorarioPsicologo consultarPorId(UUID id){
        log.info("Buscando horario por id. Id: {}", id);

        Optional<HorarioPsicologo> horarioPsicologo = gateway.buscarPorId(id);
        if (horarioPsicologo.isEmpty()) {
            throw new HorarioNaoEncontradoException(ERRO_HORARIO_NAO_ENCONTRADO);
        }

        HorarioPsicologo horarioBuscado = horarioPsicologo.get();

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