package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.CaminhoNaoSalvoException;
import com.liratech.helppsico.application.gateways.FotoGateway;
import com.liratech.helppsico.domain.Foto;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class FotoUseCase {
    private final FotoGateway gateway;
    private final PacienteUseCase pacienteUseCase;
    private final PsicologoUseCase psicologoUseCase;
    public static final String ERRO_CAMINHO_NAO_SALVO = "Caminho do arquivo da foto nulo.";

    public Foto salvar(MultipartFile arquivoFoto, String emailCrp, String tipo){
        log.info("Iniciando processo de salvar imagem localmente e no banco de dados. Foto: {}", arquivoFoto);

        Foto fotoDomain = new Foto();

        String urlFoto = gateway.salvarLocal(arquivoFoto);
        if (urlFoto == null){
            throw new CaminhoNaoSalvoException(ERRO_CAMINHO_NAO_SALVO);
        }
        fotoDomain.setFotoUrl(urlFoto);

        if (tipo.equals("PACIENTE")){
            fotoDomain.setPaciente(pacienteUseCase.consultarPorEmail(emailCrp));
        }else {
            fotoDomain.setPsicologo(psicologoUseCase.consultarPorCrp(emailCrp));
        }

        fotoDomain = gateway.salvarEntidade(fotoDomain);

        log.info("Finalizando processo de salvar a foto. Foto: {}", fotoDomain);
        return fotoDomain;
    }

    public MultipartFile buscarPorIdUsuario(UUID id){

    }
}
