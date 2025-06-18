package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.foto.CaminhoNaoSalvoException;
import com.liratech.helppsico.application.exceptions.foto.FotoNaoEncontradaException;
import com.liratech.helppsico.application.gateways.FotoGateway;
import com.liratech.helppsico.domain.Foto;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class FotoUseCase {
    private final FotoGateway gateway;
    private final PacienteUseCase pacienteUseCase;
    private final PsicologoUseCase psicologoUseCase;
    public static final String ERRO_CAMINHO_NAO_SALVO = "Caminho do arquivo da foto nulo.";

    public Foto salvar(MultipartFile arquivoFoto, Foto foto){
        log.info("Iniciando processo de salvar imagem localmente e no banco de dados. Foto: {}", arquivoFoto);
        Paciente paciente;
        Psicologo psicologo;

        String urlFoto = gateway.salvarLocal(arquivoFoto);
        if (urlFoto == null){
            throw new CaminhoNaoSalvoException(ERRO_CAMINHO_NAO_SALVO);
        }
        foto.setFotoUrl(urlFoto);

        if (foto.getPaciente().getId() != null){
            paciente = pacienteUseCase.consultarPorId(foto.getPaciente().getId());
            paciente.setFotoUrl(urlFoto);
            foto.setPaciente(paciente);
            pacienteUseCase.cadastrar(paciente);
        }else{
            psicologo = psicologoUseCase.consultarPorId(foto.getPsicologo().getId());
            psicologo.setFotoUrl(urlFoto);
            foto.setPsicologo(psicologo);
            psicologoUseCase.cadastrar(psicologo);
        }

        log.info("Finalizando processo de salvar a foto. Foto: {}", foto);
        return foto;
    }
}
