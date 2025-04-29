package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidacaoCrpDataProvider implements ValidacaoCrpGateway{

    private final ValiacaoCrpRepository repository;
    private final ValidacaoCrpMapper mapper;

    public static final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar Validação de Crp";
    public static final String MENSAGEM_ERRO_CONSULTAR_POR_ID = "Erro ao consultar Validação de Crp por ID";
    public static final String MENSAGEM_ERRO_LISTAR = "Erro ao listar Validações de Cpr";
    public static final String MENSAGEM_ERRO_DELETAR = "Erro ao deletar Validação de Crp";

    @Override
    public ValidacaoCrp salvar(ValidacaoCrp validacaoCrp){
        ValidacaoCrpEntity entity = mapper.paraEntity(validacaoCrp);

        try{
            entity = repository.save(validacaoCrp);
        } catch (Exception ex){
            log.error(MENSAGEM_ERRO_SALVAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, ex.getCause());
        }

        return mapper.paraDomain(entity);
    }

    @Override
    public Optional<ValidacaoCrp> consultarPorId(UUID id){
        Optional<ValidacaoCrp> validacaoCrp;

        try{
            validacaoCrp = repository.findById(id);
        } catch (Exception ex){
            log.error(MENSAGEM_ERRO_CONSULTAR_POR_ID, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_POR_ID, ex.getCause());
        }

        return validacaoCrp.map(mapper::paraDomain);
    }

    @Override
    public Page<ValidacaoCrp> listar(Pageable pageable){
        Page<ValidacaoCrp> validacaoCrps;

        try{
            validacaoCrps = repository.findAll(pageable);
        } catch (Exception ex){
            log.error(MENSAGEM_ERRO_LISTAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_LISTAR, ex.getCause());
        }

        return validacaoCrps.map(mapper::paraDomain);
    }

    @Override
    public void deletar(UUID id){
        try{
            repository.deleteById(id);
        } catch (Exception ex){
            log.error(MENSAGEM_ERRO_DELETAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_DELETAR, ex.getCause());
        }
    }
}
