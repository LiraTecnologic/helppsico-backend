package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import static com.liratech.helppsico.infrastructure.dataprovider.FotoDataProvider.*;

@ExtendWith(MockitoExtension.class)
class FotoDataProviderTest {

    @Mock
    private MultipartFile foto;

    private FotoDataProvider dataProvider;

    private String nomeArquivo;
    private File arquivoTemporario;
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
}