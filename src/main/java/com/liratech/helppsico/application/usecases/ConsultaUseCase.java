package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.consulta.ConsultaInvalidaException;
import com.liratech.helppsico.application.exceptions.consulta.ConsultaJaExistenteNaDataException;
import com.liratech.helppsico.application.exceptions.consulta.ConsultaNaoEncontradaException;
import com.liratech.helppsico.application.gateways.ConsultaGateway;
import com.liratech.helppsico.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsultaUseCase {

    private final ConsultaGateway gateway;
    private final PacienteUseCase pacienteUseCase;
    private final PsicologoUseCase psicologoUseCase;
    private final HorarioUseCase horarioUseCase;
    private final VinculoUseCase vinculoUseCase;
    public final String MENSAGEM_CONSULTA_JA_EXISTENTE_NA_DATA = "Consulta já existe na data epecíficada para agendar.";
    public final String MENSAGEM_CONSULTA_NAO_ENCONTRADA = "Consulta não encontrada com o id específicado.";
    public final String MENSAGEM_PSICOLOGO_PACIENTE_NAO_VINCULADOS = "Pacientes e psicologos nao são vinculados";

    public Consulta agendar(Consulta novaConsulta) {
        log.info("Agendando nova consulta. Nova consulta: {}", novaConsulta);

        this.validarHorarioConsulta(novaConsulta);

        Paciente paciente = pacienteUseCase.consultarPorId(novaConsulta.getPaciente().getId());
        Psicologo psicologo = psicologoUseCase.consultarPorId(novaConsulta.getPsicologo().getId());
        Horario horario = horarioUseCase.consultarPorId(novaConsulta.getHorario().getId());

        Vinculo vinculo = vinculoUseCase.consultarAtivoPorPaciente(paciente.getId());
        if (vinculo.getPsicologo().getId() != psicologo.getId()){
            throw new ConsultaInvalidaException(MENSAGEM_PSICOLOGO_PACIENTE_NAO_VINCULADOS);
        }

        novaConsulta.setPaciente(paciente);
        novaConsulta.setPsicologo(psicologo);
        novaConsulta.setHorario(horario);
        novaConsulta.setEndereco(psicologo.getEnderecoAtendimento());
        novaConsulta.setValor(psicologo.getValorSessao());
        novaConsulta.setFinalizada(false);

        horario.setDisponivel(false);

        Consulta consultaSalva = gateway.salvar(novaConsulta);
        horarioUseCase.cadastrar(horario);

        log.info("Consulta agendada com sucesso. Consulta: {}", consultaSalva);
        return consultaSalva;
    }

    public void cancelar(UUID id) {
        log.info("Cancelando consulta pelo seu id. Id: {}", id);

        Consulta consulta = this.consultarPorId(id);
        Horario horario = consulta.getHorario();

        horario.setDisponivel(true);
        horarioUseCase.cadastrar(horario);

        gateway.deletar(id);

        log.info("Consulta cancelada com sucesso.");
    }

    public Page<Consulta> consultarConsultasFuturasPaciente(UUID idPaciente, Pageable pageable) {
        log.info("Consultando consultas futuras por paciente. Id paciente: {}", idPaciente);

        pacienteUseCase.consultarPorId(idPaciente);
        Vinculo vinculo = vinculoUseCase.consultarAtivoPorPaciente(idPaciente);

        Page<Consulta> consultasFuturas = gateway.consultarConsultasFuturasPaciente(idPaciente, vinculo.getPsicologo().getId(), pageable);

        log.info("Consulta de cosultas futuras feita com sucesso. Consultas: {}", consultasFuturas);
        return consultasFuturas;
    }

    public Page<Consulta> consultarHistoricoPaciente(UUID idPaciente, Pageable pageable) {
        log.info("Consultando histórico de consultas do paciente. Id paciente: {}", idPaciente);

        pacienteUseCase.consultarPorId(idPaciente);
        Vinculo vinculo = vinculoUseCase.consultarAtivoPorPaciente(idPaciente);

        Page<Consulta> historico = gateway.consultarHistoricoPaciente(idPaciente, vinculo.getPsicologo().getId(), pageable);
        
        log.info("Histórico de consultas por paciente buscadas. Histórico: {}", historico);
        return historico;
    }

    public Page<Consulta> consultarConsultasFuturasPsicologo(UUID idPsicologo, Pageable pageable) {
        log.info("Consultando consultas futuras por psicologo. Id psicologo: {}", idPsicologo);

        psicologoUseCase.consultarPorId(idPsicologo);

        Page<Consulta> consultasFuturas = gateway.consultarConsultasFuturasPsicologo(idPsicologo, pageable);

        log.info("Consulta de sessões futuras feita com sucesso. Consultas: {}", consultasFuturas);
        return consultasFuturas;
    }

    public Page<Consulta> consultarHistoricoPsicologo(UUID idPsicologo, Pageable pageable) {
        log.info("Consultando histórico de consultas do psicologo. Id psicologo: {}", idPsicologo);

        psicologoUseCase.consultarPorId(idPsicologo);

        Page<Consulta> historico = gateway.consultarHistoricoPsicologo(idPsicologo, pageable);

        log.info("Histórico de consultas por psicologo buscadas. Histórico: {}", historico);
        return historico;
    }

    public Consulta alterarData(UUID idConsulta, Consulta novaData) {
        log.info("Alterando data da consulta. Id da consulta: {}, Nova data: {}", idConsulta, novaData);

        Consulta consulta = this.consultarPorId(idConsulta);
        consulta.setHorario(novaData.getHorario());
        consulta.setData(novaData.getData());

        this.validarHorarioConsulta(consulta);

        Consulta consultaSalva = gateway.salvar(consulta);

        log.info("Alteração de data feita com sucesso. Consulta: {}", consultaSalva);
        return consultaSalva;
    }

    public Consulta finalizar(UUID idConsulta) {
        log.info("Finalizando consulta. Id consulta: {}", idConsulta);

        Consulta consulta = this.consultarPorId(idConsulta);

        consulta.setFinalizada(true);

        Consulta consultaSalva = gateway.salvar(consulta);

        log.info("Consulta finalizada com sucesso. Consulta: {}", consultaSalva);

        return consultaSalva;
    }

    public Consulta consultarPorId(UUID id) {
        Optional<Consulta> consulta = gateway.consultarPorId(id);

        if(consulta.isEmpty()) {
            throw new ConsultaNaoEncontradaException(MENSAGEM_CONSULTA_NAO_ENCONTRADA);
        }

        return consulta.get();
    }

    private void validarHorarioConsulta(Consulta novaConsulta) {
        List<Consulta> consultasMesmoDia = gateway.consultarConsultasMesmoDia(novaConsulta.getData().getDayOfMonth(), novaConsulta.getPsicologo().getId());

        if(!consultasMesmoDia.isEmpty()) {
            Optional<Consulta> consultaRepetida = consultasMesmoDia.stream()
                    .filter(consulta
                            -> consulta.getHorario().getInicio() == novaConsulta.getHorario().getInicio()
                    ).findFirst();

            if(consultaRepetida.isPresent()) {
                throw new ConsultaJaExistenteNaDataException(MENSAGEM_CONSULTA_JA_EXISTENTE_NA_DATA);
            }
        }
    }

}
