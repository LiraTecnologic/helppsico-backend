package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.application.gateways.ProntuarioGateway;
import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.PacienteMapperInfra;
import com.liratech.helppsico.infrastructure.mapper.ProntuarioMapperInfra;
import com.liratech.helppsico.infrastructure.mapper.PsicologoMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.ProntuarioRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.ProntuarioEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProntuarioDataProvider implements ProntuarioGateway {

    private final ProntuarioRepository repository;
    private final ProntuarioMapperInfra mapper;
    public final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar prontuário.";
    public final String MENSAGEM_ERRO_CONSULTAR_POR_ID = "Erro ao consultar prontuário pelo id.";
    public final String MENSAGEM_ERRO_LISTAR_PACIENTE = "Erro ao listar prontuários pelo paciente.";
    public final String MENSAGEM_ERRO_LISTAR_PSICOLOGO = "Erro ao listar prontuários pelo psicologo.";
    public final String MENSAGEM_ERRO_DELETAR = "Erro ao deletar prontuário.";

    @Override
    public Prontuario salvar(Prontuario prontuario) {
        ProntuarioEntity prontuarioEntity = mapper.paraEntity(prontuario);

        try {
            prontuarioEntity = repository.save(prontuarioEntity);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_SALVAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, ex.getCause());
        }

        return mapper.paraDomain(prontuarioEntity);
    }

    @Override
    public Optional<Prontuario> consultarPorId(UUID id) {
        Optional<ProntuarioEntity> prontuarioEntity;

        try {
            prontuarioEntity = repository.findById(id);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTAR_POR_ID, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_POR_ID, ex.getCause());
        }

        return prontuarioEntity.map(mapper::paraDomain);
    }

    @Override
    public Page<Prontuario> listarPorPaciente(UUID idPaciente, Pageable pageable) {
        Page<ProntuarioEntity> prontuarioEntities;

        try {
            prontuarioEntities = repository.findAllByPacienteId(idPaciente, pageable);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_LISTAR_PACIENTE, ex);
            throw new DataProviderException(MENSAGEM_ERRO_LISTAR_PACIENTE, ex.getCause());
        }

        return prontuarioEntities.map(mapper::paraDomain);
    }

    @Override
    public Page<Prontuario> listarPorPsicologo(UUID idPsicologo, Pageable pageable) {
        Page<ProntuarioEntity> prontuarioEntities;

        try {
            prontuarioEntities = repository.findAllByPsicologoId(idPsicologo, pageable);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_LISTAR_PSICOLOGO, ex);
            throw new DataProviderException(MENSAGEM_ERRO_LISTAR_PSICOLOGO, ex.getCause());
        }

        return prontuarioEntities.map(mapper::paraDomain);
    }

    @Override
    public void deletar(UUID id) {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_DELETAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_DELETAR, ex.getCause());
        }
    }
}
