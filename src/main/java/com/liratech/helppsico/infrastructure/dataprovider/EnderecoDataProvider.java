package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.application.gateways.EnderecoGateway;
import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.infrastructure.mapper.EnderecoMapper;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.repositories.EnderecoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.EnderecoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class EnderecoDataProvider implements EnderecoGateway {

    private final EnderecoRepository repository;
    private final EnderecoMapper mapper;

    public static final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar endereço.";
    public static final String MENSAGEM_ERRO_CONSULTAR_POR_ID = "Erro ao consultar endereço pelo id.";

    @Override
    public Endereco salvar(Endereco endereco){
        EnderecoEntity enderecoEntity = mapper.paraEntity(endereco);

        try{
            enderecoEntity = repository.save(enderecoEntity);
        } catch (Exception exception){
            log.error(MENSAGEM_ERRO_SALVAR, exception);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, exception.getCause());
        }

        return mapper.paraDomain(enderecoEntity);
    }

    @Override
    public Optional<Endereco> consultarPorId(UUID id){
        Optional<EnderecoEntity> enderecoEntity;

        try{
            enderecoEntity = repository.findById(id);
        } catch (Exception exception){
            log.error(MENSAGEM_ERRO_CONSULTAR_POR_ID, exception);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_POR_ID, exception.getCause());
        }

        return enderecoEntity.map(mapper::paraDomain);
    }
}
