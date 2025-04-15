package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.application.gateways.ConsultaGateway;
import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.ConsultaMapper;
import com.liratech.helppsico.infrastructure.repositories.ConsultaRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.ConsultaEntity;
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
public class ConsultaDataProvider implements ConsultaGateway {

    private final ConsultaMapper mapper;
    private final ConsultaRepository repository;

    public static final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar consulta.";
    public static final String MENSAGEM_ERRO_CONSULTAR_HISTORICO = "Erro ao consultar histórica de sessões.";
    public static final String MENSAGEM_ERRO_CONSULTAR_POR_ID = "Erro ao consultar sessão pelo id.";
    public static final String MENSAGEM_ERRO_CONSULTAR_SESSOES_FUTURAS = "Erro ao consultar sessões futuras.";

    @Override
    public Consulta salvar(Consulta consulta) {
        ConsultaEntity consultaEntity;

        try {
            consultaEntity = repository.save(mapper.paraEntity(consulta));
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
    public Page<Consulta> consultarConsultasFuturas(UUID idPsicologo, UUID idPaciente, Pageable pageable) {
        Page<ConsultaEntity> consultaEntities;

        try {
            consultaEntities = repository.consultarConsultasFuturas(idPsicologo, idPaciente, pageable);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTAR_SESSOES_FUTURAS, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_SESSOES_FUTURAS, ex.getCause());
        }

        return consultaEntities.map(mapper::paraDomain);
    }

    @Override
    public Page<Consulta> consultarHistorico(UUID idPsicologo, UUID idPaciente, Pageable pageable) {
        Page<ConsultaEntity> consultaEntities;
        
        try {
            consultaEntities = repository.consultarHistorico(idPsicologo, idPaciente, pageable);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTAR_HISTORICO, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_HISTORICO, ex.getCause());
        }
        
        return consultaEntities.map(mapper::paraDomain);
    }
}
