package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.application.exceptions.PsicologoExistenteException;
import com.liratech.helppsico.application.exceptions.PsicologoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
    private final EnderecoUseCase enderecoUseCase;
    public static final String MENSAGEM_PSICOLOGO_JA_EXISTE = "Psicologo já está cadastrado";
    public static final String MENSAGEM_PSICOLOGO_NAO_ENCONTRADO = "Psicologo não encontrado";

    public Psicologo cadastrar(Psicologo novoPsicologo) {
        log.info("Cadastrando psicólogo. Novo psicólogo: {}", novoPsicologo);

        Optional<Psicologo> psicologoExistente = gateway.consultarPorCrp(novoPsicologo.getCrp());
        psicologoExistente.ifPresent(psicologo -> {throw new PsicologoExistenteException(MENSAGEM_PSICOLOGO_JA_EXISTE);});

        /*
            * Criar e valida crp
        */

        String urlFoto = fotoUseCase.salvarImagem(novoPsicologo.getFoto());
        novoPsicologo.setFotoUrl(urlFoto);

        String senhaCriptografada = criptografiaUseCase.criptografar(novoPsicologo.getSenha());
        novoPsicologo.setSenha(senhaCriptografada);

        Endereco endereco = enderecoUseCase.cadastrar(novoPsicologo.getEnderecoAtendimento());

        novoPsicologo.setEnderecoAtendimento(endereco);

        Psicologo psicologoSalvo = gateway.salvar(novoPsicologo);

        log.info("Psicólogo cadastrado com sucesso. Psicólogo salvo: {}", psicologoSalvo);

        return psicologoSalvo;
    }

    public Psicologo consultarPorId(UUID id) {
        log.info("Consultando psicólogo pelo seu id. Id: {}", id);

        Optional<Psicologo> psicologo = gateway.consultarPorId(id);

        if(psicologo.isEmpty()) {
            throw new PsicologoNaoEncontradoException(MENSAGEM_PSICOLOGO_NAO_ENCONTRADO);
        }

        Psicologo psicologoEncontrado = psicologo.get();

        log.info("Psicólogo consultado com sucesso. Psicólogo consultado: {}", psicologoEncontrado);

        return psicologoEncontrado;
    }

    public List<Psicologo> consultarPorNome(String nome) {

        log.info("Consultando psicólogos pelo nome. Nome a ser buscado: {}", nome);

        List<Psicologo> psicologoList = gateway.consultarPorNome(nome);


        log.info("Psicólogo consultados com sucesso. Psicólogos: {}", psicologoList);

        return psicologoList;
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
            throw new PsicologoNaoEncontradoException(MENSAGEM_PSICOLOGO_NAO_ENCONTRADO);
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
