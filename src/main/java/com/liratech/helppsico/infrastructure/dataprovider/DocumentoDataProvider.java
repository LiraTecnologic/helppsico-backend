package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.application.gateways.DocumentoGateway;
import com.liratech.helppsico.domain.documento.Documento;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.DocumentoMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.DocumentoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.DocumentoEntity;
import org.springframework.data.domain.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DocumentoDataProvider implements DocumentoGateway {

    private final DocumentoRepository repository;
    private final DocumentoMapperInfra mapper;
    public static final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar documento.";
    public static final String MENSAGEM_ERRO_LISTAR = "Erro ao listar documento.";

    @Override
    public Documento salvar(Documento documento){
        DocumentoEntity documentoEntity = mapper.paraEntity(documento);

        try{
            documentoEntity = repository.save(documentoEntity);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_SALVAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, ex.getCause());
        }

        return mapper.paraDomain(documentoEntity);
    }

    @Override
    public Page<Documento> listar(Pageable pageable){
        Page<DocumentoEntity> pageEntity;

        try{
            pageEntity = repository.findAll(pageable);
        } catch (Exception ex){
            log.error(MENSAGEM_ERRO_LISTAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_LISTAR, ex.getCause());
        }

        return pageEntity.map(mapper::paraDomain);
    }
}
