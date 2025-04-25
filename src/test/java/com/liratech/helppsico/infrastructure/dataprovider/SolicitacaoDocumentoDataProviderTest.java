package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.SolicitacaoDocumentoBuilder;
import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.SolicitacaoDocumentoEntity;
import com.liratech.helppsico.validators.SolicitacaoDocumentoValidator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class SolicitacaoDocumentoDataProviderTest {

    @Mock
    private final SolicitacaoDocumentoRepository repository;

    @InjectMocks
    private final SolicitacaoDocumentoDataProvider dataProvider;

    private final SolicitacaoDocumento domainTest = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumento();
    private final SolicitacaoDocumentoEntity entityTest = SolicitacaoDocumentoBuilder.criarSolicitacaoDocumentoEntity();

    private final SolicitacaoDocumentoMapper mapper;

    @Test
    void testeSalvarSolicitacaoDocumento() {
        domainTest.setId(null);
        Mockito.when(repository.save(Mockito.any())).thenReturn(entityTest);

        SolicitacaoDocumento resultado = dataProvider.salvar(domainTest);

        Assertions.assertNotNull(resultado.getId());
        SolicitacaoDocumentoValidator.validaSolicitacaoDocumentoDomain(resultado, domainTest);
    }

    @Test
    void testeErroSalvarSolicitacaoDocumento() {
        Mockito.when(repository.save(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException ex = Assertions.assertThrows(DataProviderException.class, () -> dataProvider.salvar(domainTest));
        Assertions.assertEquals(ex.getMessage(), SolicitacaoDocumentoDataProvider.MENSSAGEM_ERRO_SALVAR);
    }

    @Test
    void testeConsultarSolicitacaoDocumentoPorId() {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(mapper.paraEntity(domainTest)));

        Optional<SolicitacaoDocumento> resultado = dataProvider.consultarPorId(domainTest.getId());

        resultado.ifPresent(solicitacaoDocumento -> {
            Assertions.assertEquals(solicitacaoDocumento.getId(), domainTest.getId());
            SolicitacaoDocumentoValidator.validaSolicitacaoDocumentoDomain(solicitacaoDocumento, domainTest);
        });
    }

    @Test
    void testeErroConsultarSolicitacaoDocumentoPorId() {
        Mockito.when(repository.findById(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException ex = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.consultarPorId(domainTest.getId()));

        Assertions.assertEquals(ex.getMessage(), SolicitacaoDocumentoDataProvider.MENSSAGEM_ERRO_CONSULTAR_POR_ID);
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
        Mockito.when(repository.deleteById(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException ex = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.deletar(domainTest.getId()));

        Assertions.assertEquals(ex.getMessage(), SolicitacaoDocumentoDataProvider.MENSSAGEM_ERRO_DELETAR);
    }
}