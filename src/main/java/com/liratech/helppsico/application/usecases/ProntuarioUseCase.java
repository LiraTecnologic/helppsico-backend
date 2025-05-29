package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.prontuarios.ErroAtualizarCamposEspecificosExcpetion;
import com.liratech.helppsico.application.exceptions.prontuarios.ProntuarioNaoEncontradoException;
import com.liratech.helppsico.application.gateways.ProntuarioGateway;
import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.domain.Psicologo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProntuarioUseCase {

    private final ProntuarioGateway gateway;
    private final PsicologoUseCase psicologoUseCase;
    private final PacienteUseCase pacienteUseCase;
    private final ConsultaUseCase consultaUseCase;

    public Prontuario registrar(Prontuario novoProntuario) {
        log.info("Registrando novo prontuário. Prontuario: {}", novoProntuario);

        Psicologo psicologo = psicologoUseCase.consultarPorId(novoProntuario.getPsicologo().getId());
        Paciente paciente = pacienteUseCase.consultarPorId(novoProntuario.getPaciente().getId());
        Consulta consulta = consultaUseCase.consultarPorId(novoProntuario.getConsulta().getId());

        novoProntuario.setPsicologo(psicologo);
        novoProntuario.setPaciente(paciente);
        novoProntuario.setConsulta(consulta);

        Prontuario prontuarioSalvo = gateway.salvar(novoProntuario);

        log.info("Registro de prontuário realizado com sucesso. Prontuario salvo: {}", prontuarioSalvo);

        return prontuarioSalvo;
    }

    public Page<Prontuario> listarPorPaciente(UUID id, Pageable pageable) {
        log.info("Listando prontuários pelo paciente. Id do paciente: {}", id);

        Page<Prontuario> prontuarios = gateway.listarPorPaciente(id, pageable);

        log.info("Listagem de prontuários realizada com sucesso. Prontuarios: {}", prontuarios);

        return prontuarios;
    }

    public Page<Prontuario> listarPorPsicologo(UUID id, Pageable pageable) {
        log.info("Listando prontuários pelo psicólogo. Id do psicologo: {}", id);

        Page<Prontuario> prontuarios = gateway.listarPorPsicologo(id, pageable);

        log.info("Listagem de prontuários pelo psicólogo realizada com sucesso. Prontuarios: {}", prontuarios);

        return prontuarios;
    }

    public Prontuario alterar(Prontuario prontuarioAlterado, UUID idProntuario) {
        log.info("Alterando prontuário com novos dados. Novos dados: {}, Id: {}", prontuarioAlterado, idProntuario);
        Prontuario prontuario = this.consultarPorId(idProntuario);

        prontuario.alterarDado(prontuarioAlterado);

        Prontuario prontuarioSalvo = gateway.salvar(prontuario);

        log.info("Alteração de prontuário realizada com sucesso. Prontuario alterado: {}", prontuarioAlterado);

        return prontuarioSalvo;
    }

    public Prontuario alterarParcial(Map<String, Object> campos, UUID idProntuario) {
        log.info("Alterando de forma parcial o prontuário. Campos: {}, Id: {}", campos, idProntuario);

        Prontuario prontuario = this.consultarPorId(idProntuario);

        campos.forEach((nomeCampo, valorCampo) -> {
            try {
                Field field = Prontuario.class.getDeclaredField(nomeCampo);
                field.setAccessible(true);
                field.set(prontuario, valorCampo);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new ErroAtualizarCamposEspecificosExcpetion("Erro ao atualizar campo: " + nomeCampo, e);
            }
        });

        Prontuario prontuarioSalvo = gateway.salvar(prontuario);

        log.info("Alteração parcial de prontuário realizada com sucesso. Prontuario: {}", prontuario);

        return prontuarioSalvo;
    }

    public void deletar(UUID idProntuario) {
        log.info("Deletando prontuario. Id: {}", idProntuario);

        this.consultarPorId(idProntuario);
        gateway.deletar(idProntuario);

        log.info("Deleção de prontuário realizada com sucesso.");
    }

    private Prontuario consultarPorId(UUID idProntuario) {
        log.info("Consultando prontuário pelo id. Id: {}", idProntuario);

        Optional<Prontuario> prontuario = gateway.consultarPorId(idProntuario);

        if(prontuario.isEmpty()) {
            throw new ProntuarioNaoEncontradoException("Prontuario não encontrado pelo seu id.");
        }

        log.info("Prontuario consultado com sucesso. Prontuario: {}", prontuario.get());
        return prontuario.get();
    }
}
