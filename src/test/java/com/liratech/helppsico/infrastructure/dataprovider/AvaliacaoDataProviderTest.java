package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.AvaliacaoBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.AvaliacaoMapper;
import com.liratech.helppsico.infrastructure.repositories.AvaliacaoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.AvaliacaoEntity;
import com.liratech.helppsico.validators.AvaliacaoValidator;
import com.liratech.helppsico.validators.PsicologoValidator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.DatagramPacket;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class AvaliacaoDataProviderTest {

    @Mock
    private final AvaliacaoRepository repository;

    @InjectMocks
    private final AvaliacaoDataProvider dataProvider;

    private final AvaliacaoMapper mapper;

    @Test
    void testeSalvarPsicologo() {
        Avaliacao avaliacao = AvaliacaoBuilder.criarAvaliacao();
        avaliacao.setId(null);

        AvaliacaoEntity avaliacaoSalvo = mapper.paraEntity(avaliacao);
        UUID idGerado = UUID.randomUUID();
        avaliacaoSalvo.setId(idGerado);

        Mockito.when(repository.save(Mockito.any())).thenReturn(avaliacaoSalvo);

        Avaliacao avaliacaoResultado = dataProvider.salvar(avaliacao);
        AvaliacaoValidator.validaAvaliacaoDomain(mapper.paraDomain(avaliacaoSalvo), avaliacaoResultado);
    }

    @Test
    void testeExceptionSalvarPsicologo() {
        Mockito.when(repository.save(Mockito.any())).thenThrow(DataProviderException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.salvar(AvaliacaoBuilder.criarAvaliacao()));
        
        Assertions.assertEquals(AvaliacaoDataProvider.MENSAGEM_ERRO_SALVAR, exception.getMessage());
    }

    @Test
    void testeBuscarPorId(){
        Avaliacao avaliacaoTeste = AvaliacaoBuilder.criarAvaliacao();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(mapper.paraEntity(avaliacaoTeste)));

        Optional<Avaliacao> avaliacaoResultado = dataProvider.buscarPorId(avaliacaoTeste.getId());

        avaliacaoResultado.ifPresent(avaliacao -> {
            AvaliacaoValidator.validaAvaliacaoDomain(avaliacaoTeste, avaliacao);
        });
    }
    
    @Test
    void testeExceptionBuscarPorId() {
        Mockito.when(repository.findById(Mockito.any())).thenThrow(DataProviderException.class);
        
        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.buscarPorId(AvaliacaoBuilder.criarAvaliacao().getId()));
        
        Assertions.assertEquals(AvaliacaoDataProvider.MENSAGEM_ERRO_BUSCAR_POR_ID, exception.getMessage());
    }

    @Test
    void testeListarPorPsicologo(){
        List<AvaliacaoEntity> avaliacaoTeste = AvaliacaoBuilder.criarListaDeAvaliacaoEntity();
        UUID idProcurado = avaliacaoTeste.getFirst().getId();
        avaliacaoTeste.get(1).setId(idProcurado);
        avaliacaoTeste.get(2).setId(idProcurado);

        Mockito.when(repository.findAllById(Mockito.any())).thenReturn(avaliacaoTeste);

        List<Avaliacao> avaliacaoResultado = dataProvider.listarPorPsicologo(idProcurado);

        List<Avaliacao> avaliacaoTesteDomain = avaliacaoTeste.stream().map(mapper::paraDomain).toList();

        for (int i = 0; i < avaliacaoResultado.size(); i++) {
            AvaliacaoValidator.validaAvaliacaoDomain(avaliacaoTesteDomain.get(i), avaliacaoResultado.get(i));
        }
    }

    @Test
    void testeExceptionListarPorPsicologo() {
        Mockito.when(repository.listarPorPsicologos(Mockito.any())).thenThrow(DataProviderException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.listarPorPsicologo(AvaliacaoBuilder.criarAvaliacao().getId()));

        Assertions.assertEquals(AvaliacaoDataProvider.MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO, exception.getMessage());
    }

    @Test
    void testeDeletarAvaliacao() {
        UUID idGerado = AvaliacaoBuilder.criarAvaliacao().getId();

        Mockito.doNothing().when(repository).deleteById(Mockito.any());

        dataProvider.deletar(idGerado);

        Mockito.verify(repository, Mockito.times(1)).deleteById(idGerado);
    }

    @Test
    void testeExceptionDeletarAvaliacao() {
        Mockito.doThrow(DataProviderException.class).when(repository).deleteById(Mockito.any());

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.deletar(AvaliacaoBuilder.criarAvaliacao().getId()));

        Assertions.assertEquals(AvaliacaoDataProvider.MENSAGEM_ERRO_DELETAR, exception.getMessage());
    }
}
