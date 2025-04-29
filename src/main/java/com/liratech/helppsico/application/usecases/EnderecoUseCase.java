package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.endereco.EnderecoNaoEncontradoException;
import com.liratech.helppsico.application.gateways.EnderecoGateway;
import com.liratech.helppsico.domain.Endereco;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnderecoUseCase {

    private final EnderecoGateway gateway;

    public static final String MENSAGEM_ENDERECO_NAO_ENCONTRADO = "Endereco não encontrado";

    public Endereco cadastrar(Endereco novoEndereco){
        log.info("Cadastro de Endereço. Endereço novo: {}", novoEndereco);

        Endereco enderecoSalvo = gateway.salvar(novoEndereco);

        log.info("Endereço salvo. Dados salvos: {}", enderecoSalvo);

        return enderecoSalvo;
    }

    public Endereco consultarPorId(UUID id){
        log.info("Consultar o Endereço por ID. ID: {}", id);

        Optional<Endereco> endereco = gateway.consultarPorId(id);

        if(endereco.isEmpty()){
            throw new EnderecoNaoEncontradoException(MENSAGEM_ENDERECO_NAO_ENCONTRADO);
        }

        Endereco enderecoBuscado = endereco.get();

        log.info("Busca de Endereço realizado com sucesso. Endereço: {}", enderecoBuscado);

        return enderecoBuscado;
    }
}
