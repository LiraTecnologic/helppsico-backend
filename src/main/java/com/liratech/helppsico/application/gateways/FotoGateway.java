package com.liratech.helppsico.application.gateways;

import com.liratech.helppsico.domain.Foto;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

public interface FotoGateway {
    String salvarLocal(MultipartFile foto);
    Foto salvarEntidade(Foto foto);
    Optional<Foto> buscarPorPsicologo(Psicologo psicologo);
    Optional<Foto> buscarPorPaciente(Paciente paciente);
}
