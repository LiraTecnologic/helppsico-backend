package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.application.gateways.ProntuarioGateway;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.PacienteMapper;
import com.liratech.helppsico.infrastructure.mapper.ProntuarioMapper;
import com.liratech.helppsico.infrastructure.mapper.PsicologoMapper;
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
    private final ProntuarioMapper mapper;
    private final PacienteMapper pacienteMapper;
    private final PsicologoMapper psicologoMapper;

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
    public Page<Prontuario> listarPorPaciente(Paciente paciente, Pageable pageable) {
        Page<ProntuarioEntity> prontuarioEntities;

        try {
            prontuarioEntities = repository.findByPaciente(pacienteMapper.paraEntity(paciente), pageable);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_LISTAR_PACIENTE, ex);
            throw new DataProviderException(MENSAGEM_ERRO_LISTAR_PACIENTE, ex.getCause());
        }

        return prontuarioEntities.map(mapper::paraDomain);
    }

    @Override
    public Page<Prontuario> listarPorPsicologo(Psicologo psicologo, Pageable pageable) {
        Page<ProntuarioEntity> prontuarioEntities;

        try {
            prontuarioEntities = repository.findByPsicologo(psicologoMapper.paraEntity(psicologo), pageable);
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
