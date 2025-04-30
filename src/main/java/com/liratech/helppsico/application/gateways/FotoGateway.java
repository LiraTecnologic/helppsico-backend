package com.liratech.helppsico.application.gateways;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FotoGateway {
    String salvarLocal(MultipartFile foto);
    Foto salvarEntidade(Foto foto);
    Foto buscarPorPsicologo(UUID id);
    Foto buscarPorPaciente(UUID id);
}
