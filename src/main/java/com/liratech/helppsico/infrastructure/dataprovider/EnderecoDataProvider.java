package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.infrastructure.mapper.EnderecoMapper;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.repositories.entities.EnderecoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class EnderecoDataProvider implements EnderecoGateway{

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;

    public static final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar endereço.";
    public static final String MENSAGEM_ERRO_CONSULTAR_POR_ID = "Erro ao consultar endereço pelo id.";

    @Override
    public Endereco salvar(Endereco endereco){
        EnderecoEntity enderecoEntity = enderecoMapper.paraEntity(endereco);

        try{
            enderecoEntity = enderecoRepository.save(enderecoEntity);
        } catch (Exception exception){
            log.error(MENSAGEM_ERRO_SALVAR, exception);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, exception.getCause());
        }

        return enderecoMapper.paraDomain(enderecoEntity);
    }

    @Override
    public Optional<Endereco> consultarPorId(UUID id){
        Optional<EnderecoEntity> enderecoEntity;

        try{
            enderecoEntity.enderecoMapper.findById(id);
        } catch (Exception exception){
            log.error(MENSAGEM_ERRO_CONSULTAR_POR_ID, exception);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_POR_ID, exception.getCause());
        }

        return enderecoEntity.map(endereco -> enderecoMapper.paraDomain(endereco));
    }
}
