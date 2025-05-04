package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.FotoBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Foto;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.FotoMapper;
import com.liratech.helppsico.infrastructure.repositories.FotoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.FotoEntity;
import com.liratech.helppsico.validators.FotoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

import static com.liratech.helppsico.infrastructure.dataprovider.FotoDataProvider.*;

@ExtendWith(MockitoExtension.class)
class FotoDataProviderTest {

    @Mock
    private FotoRepository repository;

    @Mock
    private MultipartFile foto;

    @InjectMocks
    private FotoDataProvider dataProvider;

    private String nomeArquivo;
    private File arquivoTemporario;
    private Foto fotoDomain;
    private FotoEntity fotoEntity;
    private FotoMapper mapper;
    private static final String DIRETORIO_TESTE = "C:/temp/teste_fotos";


    @BeforeEach
    void inicializacao() throws IOException {
        dataProvider = new FotoDataProvider() {
            private static final String DIR = DIRETORIO_TESTE;

            @Override
            public String salvarLocal(MultipartFile foto) {
                try {
                    Path diretorio = Paths.get(DIR);
                    if (Files.notExists(diretorio)) {
                        Files.createDirectories(diretorio);
                    }

                    Path caminhoArquivo = diretorio.resolve(UUID.randomUUID() + "_" + foto.getOriginalFilename());

                    foto.transferTo(caminhoArquivo.toFile());

                    return caminhoArquivo.toString();
                } catch (IOException e) {
                    throw new DataProviderException(MENSAGEM_ERRO_SALVAR_FOTO_LOCAL, e);
                }
            }
        };

        nomeArquivo = "foto_teste.png";
        arquivoTemporario = File.createTempFile("test_", ".png");
        fotoDomain = FotoBuilder.criarFotoDomain();
        fotoEntity = mapper.paraEntity(fotoDomain);
    }

    @Test
    void testeSalvarLocal() throws IOException {
        arquivoTemporario = File.createTempFile("test_", ".png");
        Mockito.when(foto.getOriginalFilename()).thenReturn(nomeArquivo);
        Mockito.doAnswer(invoc -> {
            File destino = invoc.getArgument(0);
            Files.copy(arquivoTemporario.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return null;
        }).when(foto).transferTo(Mockito.any(File.class));

        String caminhoSalvo = dataProvider.salvarLocal(foto);

        Assertions.assertNotNull(caminhoSalvo);
        Assertions.assertTrue(caminhoSalvo.contains(nomeArquivo));
        Assertions.assertTrue(Files.exists(Paths.get(caminhoSalvo)));

        Files.deleteIfExists(Paths.get(caminhoSalvo));
    }

    @Test
    void testeExceptionSalvarLocal() throws IOException {
        Mockito.when(foto.getOriginalFilename()).thenReturn("erro_teste.png");

        Mockito.doThrow(new IOException())
                .when(foto).transferTo(Mockito.any(File.class));

        DataProviderException ex = Assertions.assertThrows(
                DataProviderException.class,
                () -> dataProvider.salvarLocal(foto)
        );

        Assertions.assertEquals(MENSAGEM_ERRO_SALVAR_FOTO_LOCAL, ex.getMessage());
    }

    @Test
    void testeSalvarEntidade(){
        Mockito.when(repository.save(Mockito.any())).thenReturn(fotoEntity);

        Foto fotoResultado = dataProvider.salvarEntidade(fotoDomain);

        Assertions.assertNotNull(fotoResultado.getId());
        FotoValidator.validaFotoDomain(fotoDomain, fotoResultado);
    }

    @Test
    void testeExceptionSalvarEntidade(){
        Mockito.when(repository.save(Mockito.any())).thenThrow(DataProviderException.class);

        DataProviderException exception = Assertions.assertThrows(
                DataProviderException.class,
                () -> dataProvider.salvarEntidade(fotoDomain)
        );

        Assertions.assertEquals(MENSAGEM_ERRO_SALVAR_FOTO, exception.getMessage());
    }

    @Test
    void testeBuscarPorPsicologo(){
        Mockito.when(repository.findByPsicologo(Mockito.any())).thenReturn(Optional.of(fotoEntity));

        Optional<Foto> fotoResultado = dataProvider.buscarPorPsicologo(PsicologoBuilder.criarPsicologo());

        fotoResultado.ifPresent(foto -> {
            Assertions.assertEquals(fotoEntity.getId(), foto.getId());
            FotoValidator.validaFotoDomain(fotoDomain, foto);
        });
    }

    @Test
    void testeExceptionBuscarPorPsicologo(){
        Mockito.when(repository.findByPsicologo(Mockito.any())).thenThrow(DataProviderException.class);

        DataProviderException exception = Assertions.assertThrows(
                DataProviderException.class,
                () -> dataProvider.buscarPorPsicologo(PsicologoBuilder.criarPsicologo())
        );

        Assertions.assertEquals(MENSAGEM_ERRO_BUSCAR_POR_PSICOLOGO, exception.getMessage());
    }

    @Test
    void testeBuscarPorPaciente(){
        Mockito.when(repository.findByPaciente(Mockito.any())).thenReturn(Optional.of(fotoEntity));

        Optional<Foto> fotoResultado = dataProvider.buscarPorPaciente(PacienteBuilder.criarPaciente());

        fotoResultado.ifPresent(foto -> {
            Assertions.assertEquals(fotoEntity.getId(), foto.getId());
            FotoValidator.validaFotoDomain(fotoDomain, foto);
        });
    }

    @Test
    void testeExceptionBuscarPorPaciente(){
        Mockito.when(repository.findByPaciente(Mockito.any())).thenThrow(DataProviderException.class);

        DataProviderException exception = Assertions.assertThrows(
                DataProviderException.class,
                () -> dataProvider.buscarPorPaciente(PacienteBuilder.criarPaciente())
        );

        Assertions.assertEquals(MENSAGEM_ERRO_BUSCAR_POR_PACIENTE, exception.getMessage());
    }
}