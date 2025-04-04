package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.application.gateways.AvaliacaoGateway;
import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.AvaliacaoMapper;
import com.liratech.helppsico.infrastructure.repositories.AvaliacaoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.AvaliacaoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class AvaliacaoDataProvider implements AvaliacaoGateway {

    private final AvaliacaoMapper mapper;
    private final AvaliacaoRepository repository;

    public static final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar avaliação.";

    @Override
    public Avaliacao salvar(Avaliacao avaliacao) {
        AvaliacaoEntity avaliacaoEntity = mapper.paraEntity(avaliacao);

        try{
            avaliacaoEntity = repository.save(avaliacaoEntity);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_SALVAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, ex.getCause());
        }

        return mapper.paraDomain(avaliacaoEntity);
    }

    @Override
    public List<Avaliacao> listarPorPsicologo(UUID id) {
        return null;
    }

    @Override
    public Optional<Avaliacao> buscarPorId(UUID id) {
        return Optional.empty();
    }

    @Override
    public void deletar(UUID id) {

    }
}
