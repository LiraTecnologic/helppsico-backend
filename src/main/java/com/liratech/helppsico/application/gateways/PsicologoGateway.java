package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.Psicologo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PsicologoGateway {
        Psicologo salvar(Psicologo psicologo);

        Optional<Psicologo> consultarPorId(UUID id);

        Page<Psicologo> consultarPorNome(String nome, Pageable pageable);

        Page<Psicologo> consultarMelhoresAvaliados(Pageable pageable);

        Optional<Psicologo> consultarPorCrp(String crp);

        Page<Psicologo> listar(Pageable pageable);
}
