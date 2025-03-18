package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.application.exceptions.PsicologoExistenteException;
import com.liratech.helppsico.application.exceptions.PsicologoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PsicologoUseCase {

    private final AvaliacaoUseCase avaliacaoUseCase;
    private final PsicologoGateway gateway;
    private final CriptografiaUseCase criptografiaUseCase;
    private final FotoUseCase fotoUseCase;

    public Psicologo cadastrar(Psicologo novoPsicologo) {
        log.info("Cadastrar psicólogo. Novo psicólogo: {}", novoPsicologo);

        Optional<Psicologo> psicologoExistente = gateway.consultarPorCrp(novoPsicologo.getCrp());
        psicologoExistente.ifPresent(psicologo -> {throw new PsicologoExistenteException();});

        /*
            * Criar e valida crp
        */

        String urlFoto = fotoUseCase.salvarImagem(novoPsicologo.getFoto());
        novoPsicologo.setFotoUrl(urlFoto);

        String senhaCriptografa = criptografiaUseCase.criptografar(novoPsicologo.getSenha());
        novoPsicologo.setSenha(senhaCriptografa);

        /*
            Salvar endereço
         */

        Psicologo psicologoSalvo = gateway.salvar(novoPsicologo);

        log.info("Psicólogo cadastrado com sucesso. Psicólogo salvo: {}", psicologoSalvo);

        return psicologoSalvo;
    }

    public Psicologo consultarPorId(UUID id) {
        log.info("Consultar psicólogo pelo sei id. Id: {}", id);

        Optional<Psicologo> psicologo = gateway.consultarPorId(id);

        if(psicologo.isEmpty()) {
            throw new PsicologoNaoEncontradoException();
        }

        Psicologo psicologoEncontrado = psicologo.get();

        log.info("Psicólogo consultado com sucesso. Psicólogo consultado: {}", psicologoEncontrado);

        return psicologoEncontrado;
    }

}
