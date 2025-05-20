package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.application.gateways.AvaliacaoGateway;
import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.AvaliacaoMapper;
import com.liratech.helppsico.infrastructure.repositories.AvaliacaoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.AvaliacaoEntity;
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
public class AvaliacaoDataProvider implements AvaliacaoGateway {

    private final AvaliacaoMapper mapper;
    private final AvaliacaoRepository repository;

    public static final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar avaliação.";
    public static final String MENSAGEM_ERRO_BUSCAR_POR_ID = "Erro ao consultar avaliação pelo id.";
    public static final String MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO = "Erro ao listar avaliações por psicólogo.";
    public static final String MENSAGEM_ERRO_DELETAR = "Erro ao deletar avaliação.";
    public static final String MENSAGEM_ERRO_CONSULTAR_POR_PACIENTE = "Erro ao consultar por paciente";

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
    public Page<Avaliacao> listarPorPsicologo(UUID id, Pageable pageable) {
        Page<AvaliacaoEntity> avaliacaoList;

        try {
            avaliacaoList = repository.findAllByPsicologoId(id, pageable);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO, ex);
            throw new DataProviderException(MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO, ex.getCause());
        }

        return avaliacaoList.map(mapper::paraDomain);
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
    public  Optional<Avaliacao> consultarPorPacientePsicologo(UUID idPaciente, UUID idPsicologo){
        Optional<AvaliacaoEntity> avaliacao;

        try {
            avaliacao = repository.findByPacienteIdAndPsicologoId(idPaciente, idPsicologo);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_CONSULTAR_POR_PACIENTE);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_POR_PACIENTE, ex.getCause());
        }

        return avaliacao.map(mapper::paraDomain);
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
