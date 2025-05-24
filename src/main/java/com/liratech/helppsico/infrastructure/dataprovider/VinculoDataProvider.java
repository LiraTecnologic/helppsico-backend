package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.application.gateways.VinculoGateway;
import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.VinculoMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.VinculoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.VinculoEntity;
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
public class VinculoDataProvider implements VinculoGateway {

    private final VinculoRepository repository;
    private final VinculoMapperInfra mapper;
    public static final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar o vinculo.";
    public static final String MENSAGEM_ERRO_CONSULTAR_ID = "Erro ao consultar vinculo por id.";
    public static final String MENSAGEM_ERRO_DELETAR = "Erro ao deletar o vinculo.";
    public static final String MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO = "Erro ao listar vinculo por psicologo.";
    public static final String MENSAGEM_ERRO_CONSULTAR_POR_PACIENTE = "Erro ao consultar vinculo por paciente.";

    @Override
    public Vinculo salvar(Vinculo vinculo) {
        VinculoEntity vinculoEntity = mapper.paraEntity(vinculo);

        try {
            vinculoEntity = repository.save(vinculoEntity);
        }catch (Exception exception){
            log.error(MENSAGEM_ERRO_SALVAR, exception);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, exception.getCause());
        }

        return mapper.paraDomain(vinculoEntity);
    }

    @Override
    public Optional<Vinculo> consultarPorId(UUID id) {
        Optional<VinculoEntity> vinculoEntity;

        try {
            vinculoEntity = repository.findById(id);
        }catch (Exception exception){
            log.error(MENSAGEM_ERRO_CONSULTAR_ID, exception);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_ID, exception.getCause());
        }

        return vinculoEntity.map(mapper::paraDomain);
    }

    @Override
    public void deletar(UUID id) {
        try {
            repository.deleteById(id);
        }catch (Exception exception){
            log.error(MENSAGEM_ERRO_DELETAR, exception);
            throw new DataProviderException(MENSAGEM_ERRO_DELETAR, exception.getCause());
        }
    }

    @Override
    public Page<Vinculo> listarPorIdPsicologo(UUID idPsicologo, Pageable pageable) {
        Page<VinculoEntity> vinculoEntityPage;

        try{
            vinculoEntityPage = repository.findAllByPsicologo_Id(idPsicologo, pageable);
        }catch (Exception exception){
            log.error(MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO, exception);
            throw new DataProviderException(MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO, exception.getCause());
        }

        return vinculoEntityPage.map(mapper::paraDomain);
    }

    @Override
    public Optional<Vinculo> consultarPorIdPaciente(UUID idPaciente) {
        Optional<VinculoEntity> vinculoEntity;

        try {
            vinculoEntity = repository.findByPaciente_Id(idPaciente);
        }catch (DataProviderException exception){
            log.error(MENSAGEM_ERRO_CONSULTAR_POR_PACIENTE, exception);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_POR_PACIENTE, exception.getCause());
        }

        return vinculoEntity.map(mapper::paraDomain);
    }
}
