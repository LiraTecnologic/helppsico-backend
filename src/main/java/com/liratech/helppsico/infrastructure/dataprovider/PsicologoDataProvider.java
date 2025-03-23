package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.application.gateways.PsicologoGateway;
import com.liratech.helppsico.domain.Psicologo;
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

    @Override
    public Psicologo salvar(Psicologo psicologo) {
        PsicologoEntity psicologoEntity = mapper.paraEntity(psicologo);

        return mapper.paraDomain(psicologoEntity);
    }

    @Override
    public Optional<Psicologo> consultarPorId(UUID id) {
        Optional<PsicologoEntity> psicologoEntity;

        return psicologoEntity.map(psicologo -> mapper.paraDomain(psicologo));
    }

    @Override
    public List<Psicologo> consultarPorNome(String nome) {
        List<PsicologoEntity> psicologosEntities;

        return psicologosEntities.stream()
                .map(psicologo -> mapper.paraDomain(psicologo))
                .collect(Collectors.toList());
    }

    @Override
    public Page<Psicologo> consultarMelhoresAvaliados(Pageable pageable) {
        Page<PsicologoEntity> psicologosEntities;

        return psicologosEntities.map(psicologo -> mapper.paraDomain(psicologo));
    }

    @Override
    public Optional<Psicologo> consultarPorCrp(String crp) {
        Optional<PsicologoEntity> psicologoEntity;

        return psicologoEntity.map(psicologo -> mapper.paraDomain(psicologo));
    }

    @Override
    public Page<Psicologo> listar(Pageable pageable) {
        Page<PsicologoEntity> psicologosEntities;

        return psicologosEntities.map(psicologo -> mapper.paraDomain(psicologo));
    }
}
