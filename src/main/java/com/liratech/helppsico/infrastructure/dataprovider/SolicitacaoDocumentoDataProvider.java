package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.application.gateways.SolicitacaoDocumentoGateway;
import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.SolicitacaoDocumentoMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.SolicitacaoDocumentoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.SolicitacaoDocumentoEntity;
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
public class SolicitacaoDocumentoDataProvider implements SolicitacaoDocumentoGateway {

    private final SolicitacaoDocumentoRepository repository;
    private final SolicitacaoDocumentoMapperInfra mapper;
    public static final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar Solicitação de documento";
    public static final String MENSAGEM_ERRO_CONSULTAR_POR_ID = "Erro ao consultar Solicitação de documento por id";
    public static final String MENSAGEM_ERRO_DELETAR = "Erro ao deletar Solicitação de documento";
    public static final String MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO = "Erro ao listar Solicitações por psicologo.";

    @Override
    public SolicitacaoDocumento salvar(SolicitacaoDocumento solicitacaoDocumento){
        SolicitacaoDocumentoEntity entity = mapper.paraEntity(solicitacaoDocumento);

        try{
            entity = repository.save(entity);
        } catch (Exception ex){
            log.error(MENSAGEM_ERRO_SALVAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, ex.getCause());
        }

        return mapper.paraDomain(entity);
    }

    @Override
    public Optional<SolicitacaoDocumento> consultarPorId(UUID id){
        Optional<SolicitacaoDocumentoEntity> entity;

        try {
            entity = repository.findById(id);
        } catch (Exception ex){
            log.error(MENSAGEM_ERRO_CONSULTAR_POR_ID, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_POR_ID, ex.getCause());
        }

        return entity.map(mapper::paraDomain);
    }

    @Override
    public Page<SolicitacaoDocumento> listarPorPsicologo(UUID idPsicologo, Pageable pageable){
        Page<SolicitacaoDocumentoEntity> solicitacaoDocumentoEntityPage;

        try {
            solicitacaoDocumentoEntityPage = repository.findAllByPsicologoId(idPsicologo, pageable);
        }catch (Exception ex){
            log.info(MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO, ex);
            throw new DataProviderException(MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO, ex.getCause());
        }

        return solicitacaoDocumentoEntityPage.map(mapper::paraDomain);
    }

    @Override
    public void deletar(UUID id){
        try {
            repository.deleteById(id);
        } catch (Exception ex){
            log.error(MENSAGEM_ERRO_DELETAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_DELETAR, ex.getCause());
        }
    }
}
