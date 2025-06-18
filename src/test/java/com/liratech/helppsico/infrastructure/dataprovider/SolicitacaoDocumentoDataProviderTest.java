package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.SolicitacaoDocumentoBuilder;
import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.SolicitacaoDocumentoMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.SolicitacaoDocumentoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.SolicitacaoDocumentoEntity;
import com.liratech.helppsico.validators.SolicitacaoDocumentoValidator;
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

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class SolicitacaoDocumentoDataProviderTest {

    @Mock
    private SolicitacaoDocumentoMapperInfra mapper;

    @Mock
    private SolicitacaoDocumentoRepository repository;

    @InjectMocks
    private SolicitacaoDocumentoDataProvider dataProvider;

    private SolicitacaoDocumento domainTest;
    private SolicitacaoDocumentoEntity entityTest;
    private Page<SolicitacaoDocumentoEntity> solicitacaoDocumentoPageEntity;
    private Pageable pageable;

    @BeforeEach
    void inicializandoAtributos() {
        domainTest = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumento();
        entityTest = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumentoEntity();

        solicitacaoDocumentoPageEntity = SolicitacaoDocumentoBuilder.criarPageDeSolicitacaoDocumentoEntity();
        pageable = PageRequest.of(0,10);
    }

    @Test
    void testeSalvarSolicitacaoDocumento() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(entityTest);
        Mockito.when(mapper.paraEntity(Mockito.any())).thenReturn(entityTest);
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(domainTest);

        SolicitacaoDocumento resultado = dataProvider.salvar(domainTest);

        Assertions.assertNotNull(resultado.getId());
        SolicitacaoDocumentoValidator.validaSolicitacaoDocumentoDomain(resultado, domainTest);
    }

    @Test
    void testeExceptionSalvarSolicitacaoDocumento() {
        Mockito.when(repository.save(Mockito.any())).thenThrow(RuntimeException.class);
        Mockito.when(mapper.paraEntity(Mockito.any())).thenReturn(entityTest);

        DataProviderException ex = Assertions.assertThrows(DataProviderException.class, () -> dataProvider.salvar(domainTest));
        Assertions.assertEquals(ex.getMessage(), SolicitacaoDocumentoDataProvider.MENSAGEM_ERRO_SALVAR);
    }

    @Test
    void testeConsultarSolicitacaoDocumentoPorId() {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(entityTest));
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(domainTest);

        Optional<SolicitacaoDocumento> resultado = dataProvider.consultarPorId(domainTest.getId());

        resultado.ifPresent(solicitacaoDocumento -> {
            Assertions.assertEquals(solicitacaoDocumento.getId(), domainTest.getId());
            SolicitacaoDocumentoValidator.validaSolicitacaoDocumentoDomain(solicitacaoDocumento, domainTest);
        });
    }

    @Test
    void testeExceptionConsultarSolicitacaoDocumentoPorId() {
        Mockito.when(repository.findById(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException ex = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.consultarPorId(domainTest.getId()));

        Assertions.assertEquals(ex.getMessage(), SolicitacaoDocumentoDataProvider.MENSAGEM_ERRO_CONSULTAR_POR_ID);
    }

    @Test
    void testeListarSolicitacoesPorPsicologo() {
        Mockito.when(repository.findAllByPsicologoId(Mockito.any(), Mockito.any())).thenReturn(solicitacaoDocumentoPageEntity);
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(domainTest);

        Page<SolicitacaoDocumento> solicitacaoDocumentosResultado = dataProvider.listarPorPsicologo(domainTest.getPsicologo().getId(), pageable);

        solicitacaoDocumentosResultado.forEach(solicitacaoDocumento -> {
            SolicitacaoDocumentoValidator.validaSolicitacaoDocumentoDomain(solicitacaoDocumento, domainTest);
        });
    }

    @Test
    void testeExceptionListarSolicitacoesPorPsicologo(){
        Mockito.when(repository.findAllByPsicologoId(Mockito.any(), Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException ex = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.listarPorPsicologo(domainTest.getId(), pageable));

        Assertions.assertEquals(ex.getMessage(), SolicitacaoDocumentoDataProvider.MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO);
    }

    @Test
    void testeDeletarSolicitacaoDocumento() {
        UUID id = domainTest.getId();

        Mockito.doNothing().when(repository).deleteById(Mockito.any());
        dataProvider.deletar(id);

        Mockito.verify(repository, Mockito.times(1)).deleteById(id);
    }

    @Test
    void testeErroDeletarSolicitacaoDocumento() {
        Mockito.doThrow(DataProviderException.class).when(repository).deleteById(Mockito.any());

        DataProviderException ex = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.deletar(domainTest.getId()));

        Assertions.assertEquals(ex.getMessage(), SolicitacaoDocumentoDataProvider.MENSAGEM_ERRO_DELETAR);
    }
}