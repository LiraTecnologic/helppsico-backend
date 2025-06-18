package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.application.gateways.ConsultaGateway;
import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.ConsultaMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.ConsultaRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.ConsultaEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConsultaDataProvider implements ConsultaGateway {

    private final ConsultaMapperInfra mapper;
    private final ConsultaRepository repository;

    public static final String MENSAGEM_ERRO_DELETAR_CONSULTA = "Erro ao deletar uma consulta pelo id.";
    public static final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar consulta.";
    public static final String MENSAGEM_ERRO_CONSULTAR_HISTORICO = "Erro ao consultar histórico de sessões.";
    public static final String MENSAGEM_ERRO_CONSULTAR_POR_ID = "Erro ao consultar sessão pelo id.";
    public static final String MENSAGEM_ERRO_CONSULTAR_SESSOES_FUTURAS = "Erro ao consultar sessões futuras.";
    public static final String MENSAGEM_ERRO_CONSULTAR_SESSOES_MESMO_DIA = "Erro ao consultar sessões do mesmo dia específicado.";

    @Override
    public Consulta salvar(Consulta consulta) {
        ConsultaEntity consultaEntity = mapper.paraEntity(consulta);

        try {
            consultaEntity = repository.save(consultaEntity);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_SALVAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, ex.getCause());
        }

        return mapper.paraDomain(consultaEntity);
    }

    @Override
    public Optional<Consulta> consultarPorId(UUID id) {
        Optional<ConsultaEntity> consultaEntity;

        try {
            consultaEntity = repository.findById(id);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTAR_POR_ID, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_POR_ID, ex.getCause());
        }

        return consultaEntity.map(mapper::paraDomain);
    }

    @Override
    public Page<Consulta> consultarConsultasFuturasPaciente(UUID idPaciente, UUID idPsicologo, Pageable pageable) {
        Page<ConsultaEntity> consultaEntities;

        try {
            consultaEntities = repository.consultarConsultasFuturasPaciente(idPaciente, idPsicologo, LocalDate.now(), pageable);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTAR_SESSOES_FUTURAS, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_SESSOES_FUTURAS, ex.getCause());
        }

        return consultaEntities.map(mapper::paraDomain);
    }

    @Override
    public Page<Consulta> consultarHistoricoPaciente(UUID idPaciente, UUID idPsicologo, Pageable pageable) {
        Page<ConsultaEntity> consultaEntities;
        
        try {
            consultaEntities = repository.consultarHistoricoPaciente(idPaciente, idPsicologo, pageable);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTAR_HISTORICO, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_HISTORICO, ex.getCause());
        }
        
        return consultaEntities.map(mapper::paraDomain);
    }

    @Override
    public Page<Consulta> consultarConsultasFuturasPsicologo(UUID idPsicologo, Pageable pageable) {
        Page<ConsultaEntity> consultaEntities;

        try {
            consultaEntities = repository.consultarConsultasFuturasPsicologo(
                    idPsicologo,
                    LocalDate.now(),
                    pageable);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTAR_SESSOES_FUTURAS, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_SESSOES_FUTURAS, ex.getCause());
        }

        return consultaEntities.map(mapper::paraDomain);
    }

    @Override
    public Page<Consulta> consultarHistoricoPsicologo(UUID idPsicologo, Pageable pageable) {
        Page<ConsultaEntity> consultaEntities;

        try {
            consultaEntities = repository.consultarHistoricoPsicologo(idPsicologo, pageable);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTAR_HISTORICO, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_HISTORICO, ex.getCause());
        }

        return consultaEntities.map(mapper::paraDomain);
    }

    @Override
    public List<Consulta> consultarConsultasMesmoDia(int diaDoMes, UUID idPsicologo) {
        List<ConsultaEntity> consultaEntities;

        try {
            consultaEntities = repository.consultarConsultasMesmoDia(diaDoMes, idPsicologo);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTAR_SESSOES_MESMO_DIA, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_SESSOES_MESMO_DIA, ex.getCause());
        }
        return consultaEntities.stream().map(mapper::paraDomain).toList();
    }

    @Override
    public void deletar(UUID id) {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_DELETAR_CONSULTA, ex);
            throw new DataProviderException(MENSAGEM_ERRO_DELETAR_CONSULTA, ex.getCause());
        }
    }
}
