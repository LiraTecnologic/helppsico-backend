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

@Component
@RequiredArgsConstructor
@Slf4j
public class HorarioDataProvider implements HorarioGateway {
    private final HorarioRepository repository;
    private final HorarioMapperInfra mapperInfra;
    public static final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar o horario.";

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
}
