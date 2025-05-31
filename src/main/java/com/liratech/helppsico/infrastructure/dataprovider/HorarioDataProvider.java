package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.application.gateways.HorarioGateway;
import com.liratech.helppsico.domain.Horario;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.HorarioMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.HorarioRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.HorarioEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class HorarioDataProvider implements HorarioGateway {
    private final HorarioRepository repository;
    private final HorarioMapperInfra mapperInfra;
    public static final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar o horario.";
    public static final String MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO = "Erro ao listar os horarios por psicologo.";
    public static final String MENSAGEM_ERRO_CONSULTAR_POR_ID = "Erro ao consultar horarios por id.";
    public static final String MENSAGEM_ERRO_DELETAR = "Erro ao deletar os horarios";

    public Horario salvar(Horario horario){
        HorarioEntity entity = mapperInfra.paraEntity(horario);

        try {
            entity = repository.save(entity);
        }catch (Exception ex){
            log.info(MENSAGEM_ERRO_SALVAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, ex.getCause());
        }

        return mapperInfra.paraDomain(entity);
    }

    @Override
    public List<Horario> listarPorPsicologo(UUID idPsicologo) {
        List<HorarioEntity> horarioList;

        try {
            horarioList = repository.findAllByPsicologoId(idPsicologo);
        }catch (Exception ex){
            log.info(MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO, ex);
            throw new DataProviderException(MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO, ex.getCause());
        }

        return horarioList.stream().map(mapperInfra::paraDomain).toList();
    }

    @Override
    public Optional<Horario> consultarPorId(UUID idHorario) {
        Optional<HorarioEntity> entity;

        try {
            entity = repository.findById(idHorario);
        }catch (Exception ex){
            log.info(MENSAGEM_ERRO_CONSULTAR_POR_ID, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_POR_ID, ex.getCause());
        }

        return entity.map(mapperInfra::paraDomain);
    }

    @Override
    public void deletar(UUID idHorario) {
        try {
            repository.deleteById(idHorario);
        }catch (Exception ex){
            log.info(MENSAGEM_ERRO_DELETAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_DELETAR, ex.getCause());
        }
    }
}
