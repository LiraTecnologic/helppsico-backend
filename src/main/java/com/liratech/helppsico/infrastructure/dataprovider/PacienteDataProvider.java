package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class PacienteDataProvider {

    private final PacienteRepository repository;
    private final PacienteMapper pacienteMapper;
    public static final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar paciente.";
    public static final String MENSAGEM_ERRO_CONSULTAR_POR_ID = "Erro ao consultar paciente pelo id.";
    public static final String MENSAGEM_ERRO_CONSULTAR_POR_EMAIL = "Erro ao consultar paciente pelo email.";


    public Paciente salvar(Paciente paciente) {
        PacienteEntity pacienteEntity = pacienteMapper.paraEntity(paciente);

        try {
            pacienteEntity = repository.save(pacienteEntity);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_SALVAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, ex.getCause());
        }

        return pacienteMapper.paraDomain(pacienteEntity);
    }

    public Optional<Paciente> consultarPorId(UUID id) {
        Optional<PacienteEntity> pacienteEntity;

        try {
            pacienteEntity = repository.findById(id);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTAR_POR_ID, ex);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, ex.getCause());
        }

        return pacienteEntity.map(paciente -> pacienteMapper.paraDomain(pacienteEntity));
    }

    public  Optional<Paciente> consultarPorEmail(String email) {
        Optional<PacienteEntity> pacienteEntity;

        try {
            pacienteEntity = repository.findByEmail(email);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTAR_POR_EMAIL, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_POR_EMAIL, ex.getCause());
        }

        return pacienteEntity.map(paciente -> pacienteMapper.paraDomain(paciente));
    }

}
