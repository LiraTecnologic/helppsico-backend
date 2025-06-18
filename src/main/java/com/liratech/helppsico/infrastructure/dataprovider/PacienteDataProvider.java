package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.application.gateways.PacienteGateway;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.PacienteMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.PacienteRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class PacienteDataProvider implements PacienteGateway {

    private final PacienteRepository repository;
    private final PacienteMapperInfra mapper;
    public static final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar paciente.";
    public static final String MENSAGEM_ERRO_CONSULTAR_POR_ID = "Erro ao consultar paciente pelo id.";
    public static final String MENSAGEM_ERRO_CONSULTAR_POR_EMAIL = "Erro ao consultar paciente pelo email.";

    @Override
    public Paciente salvar(Paciente paciente) {
        PacienteEntity pacienteEntity = mapper.paraEntity(paciente);

        try {
            pacienteEntity = repository.save(pacienteEntity);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_SALVAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, ex.getCause());
        }

        return mapper.paraDomain(pacienteEntity);
    }

    @Override
    public Optional<Paciente> consultarPorId(UUID id) {
        Optional<PacienteEntity> pacienteEntity;

        try {
            pacienteEntity = repository.findById(id);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTAR_POR_ID, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_POR_ID, ex.getCause());
        }

        return pacienteEntity.map(mapper::paraDomain);
    }

    @Override
    public Optional<Paciente> consultarPorEmail(String email) {
        Optional<PacienteEntity> pacienteEntity;

        try {
            pacienteEntity = repository.findByEmail(email);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTAR_POR_EMAIL, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_POR_EMAIL, ex.getCause());
        }

        return pacienteEntity.map(mapper::paraDomain);
    }

}
