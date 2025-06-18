package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.paciente.PacienteExistenteException;
import com.liratech.helppsico.application.exceptions.paciente.PacienteNaoEncontradoException;
import com.liratech.helppsico.application.gateways.PacienteGateway;
import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.domain.Paciente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PacienteUseCase {
    private final PacienteGateway gateway;
    private final CriptografiaUseCase criptografiaUseCase;
    private final EnderecoUseCase enderecoUseCase;
    public static final String MENSAGEM_PACIENTE_JA_EXISTE = "Paciente já está cadastrado";
    public static final String MENSAGEM_PACIENTE_NAO_ENCONTRADO = "Paciente não encontrado";

    public Paciente cadastrar(Paciente novoPaciente) {
        log.info("Cadastro de paciente. Paciente novo: {}", novoPaciente);

        Optional<Paciente> pacientePresente = gateway.consultarPorEmail(novoPaciente.getEmail());
        pacientePresente.ifPresent(paciente -> {
            throw new PacienteExistenteException(MENSAGEM_PACIENTE_JA_EXISTE);
        });

        String senhaCriptografada = criptografiaUseCase.criptografar(novoPaciente.getSenha());
        novoPaciente.setSenha(senhaCriptografada);

        Endereco endereco = enderecoUseCase.cadastrar(novoPaciente.getEndereco());
        novoPaciente.setEndereco(endereco);

        Paciente pacienteSalvo = gateway.salvar(novoPaciente);

        log.info("Paciente salvo. Dados salvos: {}", novoPaciente);

        return pacienteSalvo;
    }

    public Paciente consultarPorId(UUID id) {
        log.info("Consultar o Paciente pelo ID. ID: {}", id);
        Optional<Paciente> paciente = gateway.consultarPorId(id);

        if(paciente.isEmpty()){
            throw new PacienteNaoEncontradoException(MENSAGEM_PACIENTE_NAO_ENCONTRADO);
        }

        Paciente pacienteBuscado = paciente.get();
        log.info("Busca de Paciente realziada com sucesso. Paciente: {}", pacienteBuscado);
        return pacienteBuscado;
    }

    public Paciente consultarPorEmail(String email){
        log.info("Consultar o paciente por email. Email: {}", email);

        Optional<Paciente> pacienteOptional = gateway.consultarPorEmail(email);

        if (pacienteOptional.isEmpty()){
            throw new PacienteNaoEncontradoException(MENSAGEM_PACIENTE_NAO_ENCONTRADO);
        }

        Paciente paciente = pacienteOptional.get();
        log.info("Paciente consultado com sucesso. Paciente: {}", paciente);
        return paciente;
    }
}
