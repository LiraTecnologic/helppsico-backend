package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.ConsultaJaExistenteNaDataException;
import com.liratech.helppsico.application.exceptions.ConsultaNaoEncontradaException;
import com.liratech.helppsico.application.gateways.ConsultaGateway;
import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
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
    private final EnderecoUseCase enderecoUseCase;
    private final String MENSAGEM_CONSULTA_JA_EXISTENTE_NA_DATA = "Consulta já existe na data epecíficada para agendar.";
    private final String MENSAGEM_CONSULTA_NAO_ENCONTRADA = "Consulta não encontrada com o id específicado.";

    public Consulta agendar(Consulta novaConsulta) {
        log.info("Agendando nova consulta. Nova consulta: {}", novaConsulta);

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
        Optional<Consulta> consulta = gateway.consultarPorId(id);

        if(consulta.isEmpty()) {
            throw new ConsultaNaoEncontradaException(MENSAGEM_CONSULTA_NAO_ENCONTRADA);
        }

        gateway.deletar(id);

        log.info("Consulta cancelada com sucesso.");
    }

    public Page<Consulta> consultarConsultasFuturas(UUID idPaciente, UUID idPsicologo, Pageable pageable) {
        log.info("Consultando consultas futuras. Id paciente: {}, Id psicologo: {}", idPaciente, idPsicologo);
        Page<Consulta> consultasFuturas = gateway.consultarConsultasFuturas(idPaciente, idPsicologo, pageable);
        log.info("Consulta de cosultas futuras feita com sucesso. Consultas: {}", consultasFuturas);
        
        return consultasFuturas;
    }

}
