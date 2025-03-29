package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.gateways.PacienteGateway;
import com.liratech.helppsico.domain.Paciente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PacienteUseCase {
    private final CpfUseCase cpfUseCase;
    private final PacienteGateway gateway;
    private final CriptografiaUseCase criptografiaUseCase;

    public Paciente cadastrar(Paciente novoPaciente) {
        log.info("Cadastro de paciente. Paciente novo: {}", novoPaciente);

        //se for ser verificado com o CPF, deve adicioanr um ".consultarPorCpf"
        Optional<Paciente> pacientePresente = gateway.consultarPorEmail(novoPaciente.getEmail());
        pacientePresente.ifPresent(paciente -> {//rodar exeção
             });

        String fotoUrl = fotoUseCase.salvarImagem(novoPaciente.getFotoUrl());
        novoPaciente.setFotoUrl(fotoUrl);

        String senhaCriptografada = criptografiaUseCase.criptografar(novoPaciente.getSenha());
        novoPaciente.setSenha(senhaCriptografada);

        Paciente pacienteSalvo = gateway.salvar(novoPaciente);

        log.info("Paciente salvo. Dados salvos: {}", novoPaciente);

        return pacienteSalvo;
    }

    public Paciente consultarPorId(UUID id) {
        log.info("Consultar o Paciente pelo ID. ID: {}", id);
        Optional<Paciente> paciente = gateway.consultarPorId(id);

        if(paciente.isEmpty()){
            //rodar exeção;
        }

        Paciente pacienteBuscado = paciente.get();
        log.info("Busca de Paciente realziada com sucesso. Paciente: {}", pacienteBuscado);
        return pacienteBuscado;
    }
}
