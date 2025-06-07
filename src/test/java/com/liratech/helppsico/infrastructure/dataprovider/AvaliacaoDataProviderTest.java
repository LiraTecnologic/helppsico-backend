package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.AvaliacaoBuilder;
import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.AvaliacaoMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.AvaliacaoRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.AvaliacaoEntity;
import com.liratech.helppsico.validators.AvaliacaoValidator;
import lombok.AllArgsConstructor;
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

import static com.liratech.helppsico.infrastructure.dataprovider.AvaliacaoDataProvider.MENSAGEM_ERRO_CONSULTAR_POR_PACIENTE;

@ExtendWith(MockitoExtension.class)
@AllArgsConstructor
public class AvaliacaoDataProviderTest {

    @Mock
    private AvaliacaoRepository repository;

    @InjectMocks
    private AvaliacaoDataProvider dataProvider;

    private AvaliacaoMapperInfra mapper;
    private Avaliacao avaliacaoTeste;
    private AvaliacaoEntity avaliacaoEntityTeste;
    private Page<Avaliacao> avaliacaoPage;
    private Pageable pageable;

    @BeforeEach
    void inicializarAtributos() {
        avaliacaoTeste = AvaliacaoBuilder.criarAvaliacao();
        avaliacaoEntityTeste = mapper.paraEntity(avaliacaoTeste);
        avaliacaoPage = AvaliacaoBuilder.criarPageDeAvaliacoes();

        pageable = PageRequest.of(0,10);
    }

    @Test
    void testeSalvarAvaliacao() {
        avaliacaoTeste.setId(null);

        Mockito.when(repository.save(Mockito.any())).thenReturn(avaliacaoEntityTeste);

        Avaliacao avaliacaoResultado = dataProvider.salvar(avaliacaoTeste);
        AvaliacaoValidator.validaAvaliacaoDomain(mapper.paraDomain(avaliacaoEntityTeste), avaliacaoResultado);
    }

    @Test
    void testeExceptionSalvarAvaliacao() {
        Mockito.when(repository.save(Mockito.any())).thenThrow(DataProviderException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.salvar(AvaliacaoBuilder.criarAvaliacao()));
        
        Assertions.assertEquals(AvaliacaoDataProvider.MENSAGEM_ERRO_SALVAR, exception.getMessage());
    }

    @Test
    void testeBuscarPorId(){
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(avaliacaoEntityTeste));

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
        UUID idProcurado = avaliacaoPage.getContent().get(1).getPsicologo().getId();

        Mockito.when(repository.findAllByPsicologoId(Mockito.any(), Mockito.any())).thenReturn(avaliacaoPage.map(mapper::paraEntity));

        Page<Avaliacao> avaliacaoResultado = dataProvider.listarPorPsicologo(idProcurado, pageable);

        for (int i = 0; i < avaliacaoResultado.getNumberOfElements(); i++) {
            AvaliacaoValidator.validaAvaliacaoDomain(avaliacaoPage.getContent().get(i), avaliacaoResultado.getContent().get(i));
        }
    }

    @Test
    void testeExceptionListarPorPsicologo() {
        Mockito.when(repository.findAllByPsicologoId(Mockito.any(), Mockito.any())).thenThrow(DataProviderException.class);

        DataProviderException exception = Assertions
                .assertThrows(DataProviderException.class, () -> dataProvider.listarPorPsicologo(AvaliacaoBuilder.criarAvaliacao().getId(), pageable));

        Assertions.assertEquals(AvaliacaoDataProvider.MENSAGEM_ERRO_LISTAR_POR_PSICOLOGO, exception.getMessage());
    }

    @Test
    void testeConsultarPorPacientePsicologo(){
        Mockito.when(repository.findByPacienteIdAndPsicologoId(Mockito.any(), Mockito.any())).thenReturn(Optional.of(avaliacaoEntityTeste));

        Optional<Avaliacao> avaliacaoResposta = dataProvider.consultarPorPacientePsicologo(avaliacaoTeste.getPaciente().getId(), avaliacaoTeste.getPsicologo().getId());

        avaliacaoResposta.ifPresent(avaliacao ->
                AvaliacaoValidator.validaAvaliacaoDomain(avaliacaoTeste, avaliacao)
        );
    }

    @Test
    void testeExceptionConsultarPorPacientePsicologo(){
        Mockito.when(repository.findByPacienteIdAndPsicologoId(Mockito.any(), Mockito.any()))
                .thenThrow(DataProviderException.class);

        DataProviderException exception = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.consultarPorPacientePsicologo(avaliacaoTeste.getPaciente().getId(), avaliacaoTeste.getPsicologo().getId()));

        Assertions.assertEquals(MENSAGEM_ERRO_CONSULTAR_POR_PACIENTE, exception.getMessage());
    }

    @Test
    void testeDeletarAvaliacao() {
        UUID idGerado = avaliacaoEntityTeste.getId();

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
