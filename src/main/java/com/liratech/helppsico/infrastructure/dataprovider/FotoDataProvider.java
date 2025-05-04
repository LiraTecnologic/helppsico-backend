package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.application.gateways.FotoGateway;
import com.liratech.helppsico.domain.Foto;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.FotoMapper;
import com.liratech.helppsico.infrastructure.mapper.PacienteMapper;
import com.liratech.helppsico.infrastructure.mapper.PsicologoMapper;
import com.liratech.helppsico.infrastructure.repositories.FotoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.FotoEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class FotoDataProvider implements FotoGateway {

    private FotoRepository repository;
    private FotoMapper mapper;
    private PsicologoMapper psicologoMapper;
    private PacienteMapper pacienteMapper;
    public static final String DIRETORIO_LOCAL = "C:/fotos" ;
    public static final String MENSAGEM_ERRO_SALVAR_FOTO = "Erro ao salvar foto no banco de dados.";
    public static final String MENSAGEM_ERRO_SALVAR_FOTO_LOCAL = "Erro ao salvar foto localmente";
    public static final String MENSAGEM_ERRO_BUSCAR_POR_PSICOLOGO = "Erro ao buscar foto por psicologo.";
    public static final String MENSAGEM_ERRO_BUSCAR_POR_PACIENTE = "Erro ao buscar foto por paciente.";


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

    @Override
    public Foto salvarEntidade(Foto foto) {
        FotoEntity fotoEntity = mapper.paraEntity(foto);

        try {
            fotoEntity = repository.save(fotoEntity);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_SALVAR_FOTO, ex);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR_FOTO, ex.getCause());
        }

        return mapper.paraDomain(fotoEntity);
    }

    @Override
    public Optional<Foto> buscarPorPsicologo(Psicologo psicologo) {
        Optional<FotoEntity> fotoEntity;
        PsicologoEntity psicologoEntity = psicologoMapper.paraEntity(psicologo);

        try{
            fotoEntity = repository.findByPsicologo(psicologoEntity);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_BUSCAR_POR_PSICOLOGO, ex);
            throw new DataProviderException(MENSAGEM_ERRO_BUSCAR_POR_PSICOLOGO, ex.getCause());
        }

        return fotoEntity.map(mapper::paraDomain);
    }

    @Override
    public Optional<Foto> buscarPorPaciente(Paciente paciente) {
        Optional<FotoEntity> fotoEntity;
        PacienteEntity pacienteEntity = pacienteMapper.paraEntity(paciente);

        try{
            fotoEntity = repository.findByPaciente(pacienteEntity);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_BUSCAR_POR_PACIENTE, ex);
            throw new DataProviderException(MENSAGEM_ERRO_BUSCAR_POR_PACIENTE, ex.getCause());
        }

        return fotoEntity.map(mapper::paraDomain);
    }
}
