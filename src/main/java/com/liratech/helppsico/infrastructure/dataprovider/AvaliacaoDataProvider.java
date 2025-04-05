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
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class AvaliacaoDataProvider implements AvaliacaoGateway {

    private final AvaliacaoMapper mapper;
    private final AvaliacaoRepository repository;

    public static final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar avaliação.";
    public static final String MENSAGEM_ERRO_BUSCAR_POR_ID = "Erro ao consultar avaliação pelo id.";
    public static final String MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO = "Erro ao listar avaliações por psicólogo.";
    public static final String MENSAGEM_ERRO_DELETAR = "Erro ao deletar avaliação.";

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
        List<AvaliacaoEntity> avaliacaoList;

        try {
            avaliacaoList = repository.listarPorPsicologo(id);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO, ex);
            throw new DataProviderException(MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO, ex.getCause());
        }

        return avaliacaoList.stream()
                .map(mapper::paraDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Avaliacao> buscarPorId(UUID id) {
        Optional<AvaliacaoEntity> avaliacaoEntity;

        try {
            avaliacaoEntity = repository.findById(id);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_BUSCAR_POR_ID, ex);
            throw new DataProviderException(MENSAGEM_ERRO_BUSCAR_POR_ID, ex.getCause());
        }

        return avaliacaoEntity.map(mapper::paraDomain);
    }

    @Override
    public void deletar(UUID id) {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_DELETAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_DELETAR, ex.getCause());
        }
    }
}
