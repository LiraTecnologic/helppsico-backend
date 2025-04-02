package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.application.gateways.PsicologoGateway;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.PsicologoMapper;
import com.liratech.helppsico.infrastructure.repositories.PsicologoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class PsicologoDataProvider implements PsicologoGateway {

    private final PsicologoRepository repository;
    private final PsicologoMapper mapper;
    public static final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar psicologo.";
    public static final String MENSAGEM_ERRO_CONSULTAR_POR_ID = "Erro ao consultar psicologo pelo id.";
    public static final String MENSAGEM_ERRO_CONSULTAR_POR_NOME = "Erro ao consultar psicologos pelo nome.";
    public static final String MENSAGEM_ERRO_CONSULTAR_MELHORES_AVALIADOS = "Erro ao consultar psicologos melhores avaliados.";
    public static final String MENSAGEM_ERRO_CONSULTAR_POR_CRP = "Erro ao consultar psicologo pelo crp.";
    public static final String MENSAGEM_ERRO_LISTAR = "Erro ao listar psicologos.";

    @Override
    public Psicologo salvar(Psicologo psicologo) {
        PsicologoEntity psicologoEntity = mapper.paraEntity(psicologo);

        try {
            psicologoEntity = repository.save(psicologoEntity);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_SALVAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, ex.getCause());
        }

        return mapper.paraDomain(psicologoEntity);
    }

    @Override
    public Optional<Psicologo> consultarPorId(UUID id) {
        Optional<PsicologoEntity> psicologoEntity;

        try {
            psicologoEntity = repository.findById(id);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_CONSULTAR_POR_ID, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_POR_ID, ex.getCause());
        }

        return psicologoEntity.map(mapper::paraDomain);
    }

    @Override
    public List<Psicologo> consultarPorNome(String nome) {
        List<PsicologoEntity> psicologosEntities;

        try {
            psicologosEntities = repository.findByNome(nome);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_CONSULTAR_POR_NOME, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_POR_NOME, ex.getCause());
        }

        return psicologosEntities.stream()
                .map(mapper::paraDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Psicologo> consultarMelhoresAvaliados(Pageable pageable) {
        Page<PsicologoEntity> psicologosEntities;

        try {
            psicologosEntities = repository.consultarMelhoresAvaliados(pageable);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_CONSULTAR_MELHORES_AVALIADOS, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_MELHORES_AVALIADOS, ex.getCause());
        }

        return psicologosEntities.map(mapper::paraDomain);
    }

    @Override
    public Optional<Psicologo> consultarPorCrp(String crp) {
        Optional<PsicologoEntity> psicologoEntity;

        try {
            psicologoEntity = repository.findByCrp(crp);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_CONSULTAR_POR_CRP, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_POR_CRP, ex.getCause());
        }

        return psicologoEntity.map(mapper::paraDomain);
    }

    @Override
    public Page<Psicologo> listar(Pageable pageable) {
        Page<PsicologoEntity> psicologosEntities;

        try {
            psicologosEntities = repository.findAll(pageable);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_LISTAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_LISTAR, ex.getCause());
        }

        return psicologosEntities.map(mapper::paraDomain);
    }
}
