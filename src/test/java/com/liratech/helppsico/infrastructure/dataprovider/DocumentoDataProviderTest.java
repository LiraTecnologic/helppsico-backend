package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.DocumentoBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.documento.Documento;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;

import com.liratech.helppsico.infrastructure.mapper.DocumentoMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.DocumentoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.DocumentoEntity;
import com.liratech.helppsico.validators.DocumentoValidator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
@AllArgsConstructor
class DocumentoDataProviderTest {

    @Mock
    private final DocumentoRepository repository;

    @InjectMocks
    private final DocumentoDataProvider dataProvider;

    private Documento documentoDomain;
    private DocumentoEntity documentoEntity;
    private DocumentoMapperInfra mapper;
    private Page<Documento> pageDocumentos;
    private Page<DocumentoEntity> pageDocumentosEntity;
    private Pageable pageable;
    private Paciente pacienteTeste;

    @BeforeEach
    void inicializarAtributos(){
        documentoDomain = DocumentoBuilder.criarAtestado();
        documentoEntity = DocumentoBuilder.criarAtestadoEntity();
        pageDocumentos = DocumentoBuilder.criarPageDeDocumento();
        pageDocumentosEntity = pageDocumentos.map(mapper::paraEntity);
        pacienteTeste = documentoDomain.getPaciente();

        pageable = PageRequest.of(0,10);
    }

    @Test
    void testeSalvarDocumento(){
        Mockito.when(repository.save(Mockito.any())).thenReturn(documentoEntity);

        Documento documento = dataProvider.salvar(documentoDomain);

        Assertions.assertNotNull(documento.getId());
        DocumentoValidator.validaDocumentoDomain(mapper.paraDomain(documentoEntity), documento);
    }

    @Test
    void testeExceptionSalvarDocumento(){
        Mockito.when(repository.save(Mockito.any())).thenThrow(Exception.class);

        DataProviderException exception = Assertions.assertThrows(
                DataProviderException.class,
                () -> dataProvider.salvar(documentoDomain)
        );

        Assertions.assertEquals(MENSAGEM_ERRO_SALVAR, exception.getMessage());
    }

    @Test
    void testeListarDocumentosPorPaciente(){
        Mockito.when(repository.findAllByPacienteId(Mockito.any(), Mockito.any())).thenReturn(pageDocumentosEntity);

        Page<Documento> documentos = dataProvider.listarPorPaciente(pacienteTeste.getId(), pageable);

        documentos.map(documento -> {
            Assertions.assertNotNull(documento.getId());
            DocumentoValidator.validaDocumentoDomain(documentoDomain, documento);
            return null;
        });
    }

    @Test
    void testeExceptionListarDocumentosPorPaciente(){
        Mockito.when(repository.findAll()).thenThrow(Exception.class);

        DataProviderException exception = Assertions.assertThrows(
                DataProviderException.class,
                () -> dataProvider.listarPorPaciente(pacienteTeste.getId(), pageable)
        );

        Assertions.assertEquals(MENSAGEM_ERRO_LISTAR, exception.getMessage());
    }
}