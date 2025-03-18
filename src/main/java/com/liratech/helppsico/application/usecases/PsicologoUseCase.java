package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.application.exceptions.PsicologoExistenteException;
import com.liratech.helppsico.application.exceptions.PsicologoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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
        log.info("Cadastrando psicólogo. Novo psicólogo: {}", novoPsicologo);

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
        log.info("Consultando psicólogo pelo sei id. Id: {}", id);

        Optional<Psicologo> psicologo = gateway.consultarPorId(id);

        if(psicologo.isEmpty()) {
            throw new PsicologoNaoEncontradoException();
        }

        Psicologo psicologoEncontrado = psicologo.get();

        log.info("Psicólogo consultado com sucesso. Psicólogo consultado: {}", psicologoEncontrado);

        return psicologoEncontrado;
    }

    public Psicologo consultarPorNome(String nome) {

        log.info("Consultando psicólogo pelo nome. Nome a ser buscado: {}", nome);

        Optional<Psicologo> psicologo = gateway.consultarPorNome(nome);

        if(psicologo.isEmpty()) {
            throw new PsicologoNaoEncontradoException();
        }

        Psicologo psicologoEncontrado = psicologo.get();

        log.info("Psicólogo consultado com sucesso. Psicólogo: {}", psicologo);

        return psicologoEncontrado;
    }

    public Page<Psicologo> consultarMelhoresAvaliados(Pageable pageable) {

        log.info("Consultando psicólogos melhores avaliados.");

        Page<Psicologo> psicologos = gateway.consultarMelhoresAvaliados(pageable);

        log.info("Consultado psicólogos melhores avaliados. Psicólogos: {}", psicologos);

        return psicologos;
    }

    public Psicologo consultarPorCrp(String crp) {

        log.info("Consultando psicólogo pelo seu crp. Crp: {}", crp);

        Optional<Psicologo> psicologo = gateway.consultarPorCrp(crp);

        if(psicologo.isEmpty()) {
            throw new PsicologoNaoEncontradoException();
        }

        Psicologo psicologoExistente = psicologo.get();

        log.info("Consultado psicólogo pelo crp. Psicólogo: {}", psicologo);

        return psicologoExistente;
    }

    public Page<Psicologo> listar(Pageable pageable) {

        log.info("Listando todos psicólogos.");

        Page<Psicologo> psicologos = gateway.listar(pageable);

        log.info("Listado todos os psicólogos. Psicólogos: {}", psicologos);

        return psicologos;
    }

    public Psicologo alterar(Psicologo novosDados, UUID id) {

        log.info("Alterando dados do psicólogo. Novos dados: {}", novosDados);
        log.info("\nId: {}", id);

        Psicologo psicologoExistente = this.consultarPorId(id);

        /*
            Salvar novo endereço.
         */

        psicologoExistente.alterarDados(novosDados);

        Psicologo psicologoSalvo = gateway.salvar(psicologoExistente);

        log.info("Alterado psicólogo com sucesso. Psicólogo alterado: {}", psicologoSalvo);

        return psicologoSalvo;
    }

}
