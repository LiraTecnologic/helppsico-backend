package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.application.gateways.FotoGateway;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class FotoDataProvider implements FotoGateway {
    public static final String DIRETORIO_LOCAL = "C:/" ;
    public static final String MENSAGEM_ERRO_SALVAR_FOTO_LOCAL = "Erro ao salvar foto localmente";


    @Override
    public String salvarLocal(MultipartFile foto) {
        try {
            Path diretorio = Paths.get(DIRETORIO_LOCAL);
            if (Files.notExists(diretorio)) {
                Files.createDirectories(diretorio);
            }

            Path caminhoArquivo = diretorio.resolve(UUID.randomUUID() + "_" + foto.getOriginalFilename());

            foto.transferTo(caminhoArquivo.toFile());

            return caminhoArquivo.toString();
        }catch (IOException ex){
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR_FOTO_LOCAL, ex.getCause());
        }
    }
}
