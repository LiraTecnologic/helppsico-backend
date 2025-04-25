package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.consulta.ConsultaJaExistenteNaDataException;
import com.liratech.helppsico.application.exceptions.consulta.ConsultaNaoEncontradaException;
import com.liratech.helppsico.application.gateways.ConsultaGateway;
import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public final String MENSAGEM_CONSULTA_JA_EXISTENTE_NA_DATA = "Consulta já existe na data epecíficada para agendar.";
    public final String MENSAGEM_CONSULTA_NAO_ENCONTRADA = "Consulta não encontrada com o id específicado.";

    public Consulta agendar(Consulta novaConsulta) {
        log.info("Agendando nova consulta. Nova consulta: {}", novaConsulta);

        this.validaHorarioConsulta(novaConsulta);

        Paciente paciente = pacienteUseCase.consultarPorId(novaConsulta.getPaciente().getId());
        Psicologo psicologo = psicologoUseCase.consultarPorId(novaConsulta.getPsicologo().getId());

        novaConsulta.setPaciente(paciente);
        novaConsulta.setPsicologo(psicologo);
        novaConsulta.setEndereco(psicologo.getEnderecoAtendimento());
        novaConsulta.setFinalizada(false);


        Consulta consultaSalva = gateway.salvar(novaConsulta);

        log.info("Consulta agendada com sucesso. Consulta: {}", consultaSalva);

        return consultaSalva;
    }

    public void cancelar(UUID id) {
        log.info("Cancelando consulta pelo seu id. Id: {}", id);

        this.consultarPorId(id);
        gateway.deletar(id);

        log.info("Consulta cancelada com sucesso.");
    }

    public Page<Consulta> consultarConsultasFuturas(UUID idPaciente, UUID idPsicologo, Pageable pageable) {
        log.info("Consultando consultas futuras. Id paciente: {}, Id psicologo: {}", idPaciente, idPsicologo);
        Page<Consulta> consultasFuturas = gateway.consultarConsultasFuturas(idPaciente, idPsicologo, pageable);
        log.info("Consulta de cosultas futuras feita com sucesso. Consultas: {}", consultasFuturas);
        return consultasFuturas;
    }

    public Page<Consulta> consultaHistorico(UUID idPaciente, UUID idPsicologo, Pageable pageable) {
        log.info("Consultando histórico de consultas. Id paciente: {}, Id psicólogo: {}", idPaciente, idPsicologo);
        Page<Consulta> historico = gateway.consultarHistorico(idPaciente, idPsicologo, pageable);
        log.info("Histórico de consultas consultados com sucesso. Histórico: {}", historico);
        return historico;
    }

    public Consulta alterarData(UUID idConsulta, LocalDateTime novaData) {
        log.info("Alterando data da consulta. Id da consulta: {}, Nova data: {}", idConsulta, novaData);

        Consulta consulta = this.consultarPorId(idConsulta);
        consulta.setDataHora(novaData);
        this.validaHorarioConsulta(consulta);
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

    private void validaHorarioConsulta(Consulta novaConsulta) {
        List<Consulta> consultasMesmoDia = gateway.consultarConsultasMesmoDia(novaConsulta.getDataHora().getDayOfMonth());

        if(!consultasMesmoDia.isEmpty()) {
            Optional<Consulta> consultaRepetida = consultasMesmoDia.stream()
                    .filter(consulta
                            -> consulta.getDataHora().getHour() == novaConsulta.getDataHora().getHour()
                    ).findFirst();

            if(consultaRepetida.isPresent()) {
                throw new ConsultaJaExistenteNaDataException(MENSAGEM_CONSULTA_JA_EXISTENTE_NA_DATA);
            }
        }
    }

    private Consulta consultarPorId(UUID id) {
        Optional<Consulta> consulta = gateway.consultarPorId(id);

        if(consulta.isEmpty()) {
            throw new ConsultaNaoEncontradaException(MENSAGEM_CONSULTA_NAO_ENCONTRADA);
        }

        return consulta.get();
    }

}
