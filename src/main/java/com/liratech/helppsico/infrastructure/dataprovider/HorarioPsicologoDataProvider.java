package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.application.gateways.HorarioPsicologoGateway;
import com.liratech.helppsico.domain.HorarioPsicologo;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.HorarioPsicologoMapper;
import com.liratech.helppsico.infrastructure.repositories.HorarioPsicologoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.HorarioPsicologoEntity;
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
public class HorarioPsicologoDataProvider implements HorarioPsicologoGateway {

    private final HorarioPsicologoRepository repository;
    private final HorarioPsicologoMapper mapper;

    public static final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar horario.";
    public static final String MENSAGEM_ERRO_BUSCAR_POR_PSICOLOGO = "Erro ao buscar horarios por psicologo.";
    public static final String MENSAGEM_ERRO_BUSCAR_POR_ID = "Erro ao buscar horario por id.";
    public static final String MENSAGEM_ERRO_DELETAR_HORARIO = "Erro ao deletar horario.";

    @Override
    public HorarioPsicologo salvar(HorarioPsicologo horario) {
        HorarioPsicologoEntity horarioEntity = mapper.paraEntity(horario);

        try {
            horarioEntity = repository.save(horarioEntity);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_SALVAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, ex.getCause())
        }

        return mapper.paraDomain(horarioEntity);
    }

    @Override
    public Page<HorarioPsicologo> listarPorPsicologo(UUID id, Pageable pageable) {
        Page<HorarioPsicologoEntity> horarioPsicologoEntityPage;

        try {
            horarioPsicologoEntityPage = repository.buscarPorPsicologo(id, pageable);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_BUSCAR_POR_PSICOLOGO, ex);
            throw new DataProviderException(MENSAGEM_ERRO_BUSCAR_POR_PSICOLOGO, ex.getCause());
        }

        return horarioPsicologoEntityPage.map(mapper::paraDomain);
    }

    @Override
    public Optional<HorarioPsicologo> buscarPorId(UUID id) {
        Optional<HorarioPsicologoEntity> horarioEntityOptional;

        try {
            horarioEntityOptional = repository.findById(id);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_BUSCAR_POR_ID, ex);
            throw new DataProviderException(MENSAGEM_ERRO_BUSCAR_POR_ID, ex.getCause());
        }

        return horarioEntityOptional.map(mapper::paraDomain);
    }

    @Override
    public void deletar(UUID id) {
        try {
            repository.deleteById(id);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_DELETAR_HORARIO, ex);
            throw new DataProviderException(MENSAGEM_ERRO_DELETAR_HORARIO)
        }
    }
}
