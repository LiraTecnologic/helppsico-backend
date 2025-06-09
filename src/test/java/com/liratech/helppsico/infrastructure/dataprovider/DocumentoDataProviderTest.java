package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.documento.Documento;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.DocumentoMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.DocumentoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.DocumentoEntity;
import com.liratech.helppsico.validators.DocumentoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static com.liratech.helppsico.infrastructure.dataprovider.DocumentoDataProvider.MENSAGEM_ERRO_LISTAR;
import static com.liratech.helppsico.infrastructure.dataprovider.DocumentoDataProvider.MENSAGEM_ERRO_SALVAR;


@ExtendWith(MockitoExtension.class)
class DocumentoDataProviderTest {

    @Mock
    private DocumentoMapperInfra mapper;

    @Mock
    private DocumentoRepository repository;

    @InjectMocks
    private DocumentoDataProvider dataProvider;

    private Documento documentoDomain;
    private DocumentoEntity documentoEntity;
    private Page<Documento> pageDocumentos;
    private Page<DocumentoEntity> pageDocumentosEntity;
    private Pageable pageable;
    private Paciente pacienteTeste;

    @BeforeEach
    void inicializarAtributos(){
        documentoDomain = DocumentoBuilder.criarAtestado();
        documentoEntity = DocumentoBuilder.criarAtestadoEntity();
        pageDocumentos = DocumentoBuilder.criarPageDeDocumento();
        pageDocumentosEntity = DocumentoBuilder.criarPageDeDocumentoEntity();
        pacienteTeste = documentoDomain.getPaciente();

        pageable = PageRequest.of(0,10);
    }

    @Test
    void testeSalvarDocumento(){
        Mockito.when(repository.save(Mockito.any())).thenReturn(documentoEntity);
        Mockito.when(mapper.paraEntity(Mockito.any())).thenReturn(documentoEntity);
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(documentoDomain);

        Documento documento = dataProvider.salvar(documentoDomain);

        Assertions.assertNotNull(documento.getId());
        DocumentoValidator.validaDocumentoDomain(mapper.paraDomain(documentoEntity), documento);
    }

    @Test
    void testeExceptionSalvarDocumento(){
        Mockito.when(repository.save(Mockito.any())).thenThrow(RuntimeException.class);
        Mockito.when(mapper.paraEntity(Mockito.any())).thenReturn(documentoEntity);

        DataProviderException exception = Assertions.assertThrows(
                DataProviderException.class,
                () -> dataProvider.salvar(documentoDomain)
        );

        Assertions.assertEquals(MENSAGEM_ERRO_SALVAR, exception.getMessage());
    }

    @Test
    void testeListarDocumentosPorPaciente(){
        Mockito.when(repository.findAllByPacienteId(Mockito.any(), Mockito.any())).thenReturn(pageDocumentosEntity);
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(documentoDomain);

        Page<Documento> documentos = dataProvider.listarPorPaciente(pacienteTeste.getId(), pageable);

        documentos.map(documento -> {
            Assertions.assertNotNull(documento.getId());
            DocumentoValidator.validaDocumentoDomain(documentoDomain, documento);
            return null;
        });
    }

    @Test
    void testeExceptionListarDocumentosPorPaciente(){
        Mockito.when(repository.findAllByPacienteId(Mockito.any(), Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions.assertThrows(
                DataProviderException.class,
                () -> dataProvider.listarPorPaciente(pacienteTeste.getId(), pageable)
        );

        Assertions.assertEquals(MENSAGEM_ERRO_LISTAR, exception.getMessage());
    }
}