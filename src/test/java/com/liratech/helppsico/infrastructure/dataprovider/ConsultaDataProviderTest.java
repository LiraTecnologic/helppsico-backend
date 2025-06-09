package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.ConsultaBuilder;
import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.ConsultaMapperInfra;
import com.liratech.helppsico.infrastructure.repositories.ConsultaRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.ConsultaEntity;
import com.liratech.helppsico.validators.ConsultaValidator;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
class ConsultaDataProviderTest {

    @Mock
    private ConsultaRepository repository;

    @Mock
    private ConsultaMapperInfra mapper;

    @InjectMocks
    private ConsultaDataProvider dataProvider;

    private ConsultaEntity consultaEntityTeste;
    private Consulta consultaDomainTeste;
    private Page<ConsultaEntity> pageConsultaEntitiesTeste;
    private Paciente pacienteDomainTeste;
    private Psicologo psicologoDomainTeste;
    private Pageable pageable;

    @BeforeEach
    void inicializar(){
        consultaEntityTeste = ConsultaBuilder.criarConsultaEntity();
        consultaDomainTeste = ConsultaBuilder.criarConsulta();
        pageConsultaEntitiesTeste = ConsultaBuilder.criarPageConsultaEntity();
        pacienteDomainTeste = consultaDomainTeste.getPaciente();
        psicologoDomainTeste = consultaDomainTeste.getPsicologo();
        pageable = PageRequest.of(0, 10);
    }

    @Test
    void testeSalvar() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(consultaEntityTeste);
        Mockito.when(mapper.paraEntity(Mockito.any())).thenReturn(consultaEntityTeste);
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(consultaDomainTeste);

        Consulta resultado = dataProvider.salvar(consultaDomainTeste);

        Assertions.assertNotNull(resultado.getId());
        ConsultaValidator.validaConsultaDomain(resultado, consultaDomainTeste);
    }

    @Test
    void testaExceptionSalvar() {
        Mockito.when(repository.save(Mockito.any())).thenThrow(RuntimeException.class);
        Mockito.when(mapper.paraEntity(Mockito.any())).thenReturn(consultaEntityTeste);

        DataProviderException exception = Assertions.assertThrows(
                DataProviderException.class,
                () -> dataProvider.salvar(consultaDomainTeste));
        Assertions.assertEquals(exception.getMessage(), ConsultaDataProvider.MENSAGEM_ERRO_SALVAR);
    }

    @Test
    void testeConsultaPorId() {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(consultaEntityTeste));
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(consultaDomainTeste);

        Optional<Consulta> resultado = dataProvider.consultarPorId(consultaDomainTeste.getId());

        resultado.ifPresent(consulta -> {
            Assertions.assertEquals(consulta.getId(), consultaDomainTeste.getId());
            ConsultaValidator.validaConsultaDomain(consulta, consultaDomainTeste);
        });
    }

    @Test
    void testeExceptionConsultaPorId() {
        Mockito.when(repository.findById(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.consultarPorId(consultaDomainTeste.getId()));

        Assertions.assertEquals(exception.getMessage(), ConsultaDataProvider.MENSAGEM_ERRO_CONSULTAR_POR_ID);
    }

    @Test
    void testeConsultarConsultasFuturasPorPaciente() {
        Mockito.when(repository.consultarConsultasFuturasPaciente(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(pageConsultaEntitiesTeste);
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(consultaDomainTeste);

        Page<Consulta> resultado = dataProvider.consultarConsultasFuturasPaciente(
                        pacienteDomainTeste.getId(), psicologoDomainTeste.getId(), pageable
                );

        Assertions.assertEquals(resultado.getTotalElements(), pageConsultaEntitiesTeste.getTotalElements());

        List<Consulta> resultadoList = resultado.getContent();

        IntStream.range(0, resultadoList.size())
                .forEach(i -> ConsultaValidator.validaConsultaDomain(
                        mapper.paraDomain(pageConsultaEntitiesTeste.getContent().get(i)),
                        resultadoList.get(i)
                ));
    }

    @Test
    void testeExceptionConsultarConsultasFuturasPorPaciente() {
        Mockito.when(repository.consultarConsultasFuturasPaciente(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.consultarConsultasFuturasPaciente(pacienteDomainTeste.getId(), psicologoDomainTeste.getId(), pageable));

        Assertions.assertEquals(exception.getMessage(), ConsultaDataProvider.MENSAGEM_ERRO_CONSULTAR_SESSOES_FUTURAS);
    }

    @Test
    void testeConsultarConsultasFuturasPorPsicologo() {
        Mockito.when(repository.consultarConsultasFuturasPsicologo(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(pageConsultaEntitiesTeste);
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(consultaDomainTeste);

        Page<Consulta> resultado = dataProvider.consultarConsultasFuturasPsicologo(
                psicologoDomainTeste.getId(), pageable
        );

        Assertions.assertEquals(resultado.getTotalElements(), pageConsultaEntitiesTeste.getTotalElements());

        List<Consulta> resultadoList = resultado.getContent();

        IntStream.range(0, resultadoList.size())
                .forEach(i -> ConsultaValidator.validaConsultaDomain(
                        mapper.paraDomain(pageConsultaEntitiesTeste.getContent().get(i)),
                        resultadoList.get(i)
                ));
    }

    @Test
    void testeExceptionConsultarConsultasFuturasPorPsicologo() {
        Mockito.when(repository.consultarConsultasFuturasPsicologo(Mockito.any(), Mockito.any(), Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.consultarConsultasFuturasPsicologo(psicologoDomainTeste.getId(), pageable));

        Assertions.assertEquals(exception.getMessage(), ConsultaDataProvider.MENSAGEM_ERRO_CONSULTAR_SESSOES_FUTURAS);
    }

    @Test
    void testeConsultarHistoricoPorPaciente() {
        Mockito.when(repository.consultarHistoricoPaciente(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(pageConsultaEntitiesTeste);
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(consultaDomainTeste);

        Page<Consulta> resultado = dataProvider.consultarHistoricoPaciente(
                pacienteDomainTeste.getId(), psicologoDomainTeste.getId(), pageable
        );

        Assertions.assertEquals(resultado.getTotalElements(), pageConsultaEntitiesTeste.getTotalElements());

        List<Consulta> resultadoList = resultado.getContent();

        IntStream.range(0, resultadoList.size())
                .forEach(i -> ConsultaValidator.validaConsultaDomain(
                        mapper.paraDomain(pageConsultaEntitiesTeste.getContent().get(i)),
                        resultadoList.get(i)
                ));
    }

    @Test
    void testeExceptionConsultarHistoricoPaciente() {
        Mockito.when(repository.consultarHistoricoPaciente(Mockito.any(), Mockito.any(), Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.consultarHistoricoPaciente(pacienteDomainTeste.getId(), psicologoDomainTeste.getId(), pageable));

        Assertions.assertEquals(exception.getMessage(), ConsultaDataProvider.MENSAGEM_ERRO_CONSULTAR_HISTORICO);
    }

    @Test
    void testeConsultarHistoricoPorPsicologo() {
        Mockito.when(repository.consultarHistoricoPsicologo(Mockito.any(), Mockito.any())).thenReturn(pageConsultaEntitiesTeste);
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(consultaDomainTeste);

        Page<Consulta> resultado = dataProvider.consultarHistoricoPsicologo(
                psicologoDomainTeste.getId(), pageable
        );

        Assertions.assertEquals(resultado.getTotalElements(), pageConsultaEntitiesTeste.getTotalElements());

        List<Consulta> resultadoList = resultado.getContent();

        IntStream.range(0, resultadoList.size())
                .forEach(i -> ConsultaValidator.validaConsultaDomain(
                        mapper.paraDomain(pageConsultaEntitiesTeste.getContent().get(i)),
                        resultadoList.get(i)
                ));
    }

    @Test
    void testeExceptionConsultarHistoricoPsicologo() {
        Mockito.when(repository.consultarHistoricoPsicologo(Mockito.any(), Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.consultarHistoricoPsicologo(psicologoDomainTeste.getId(), pageable));

        Assertions.assertEquals(exception.getMessage(), ConsultaDataProvider.MENSAGEM_ERRO_CONSULTAR_HISTORICO);
    }

    @Test
    void testeConsultarSessoesDoMesmoDia() {
        List<ConsultaEntity> listTeste = ConsultaBuilder.criarListaConsultaEntity();
        List<Consulta> listDomain = ConsultaBuilder.criarListaConslta();

        Mockito.when(repository.consultarConsultasMesmoDia(Mockito.any(Integer.class), Mockito.any())).thenReturn(listTeste);
        Mockito.when(mapper.paraDomain(Mockito.any())).thenReturn(consultaDomainTeste);

        List<Consulta> resultado = dataProvider.consultarConsultasMesmoDia(12, consultaDomainTeste.getPsicologo().getId());

        Assertions.assertEquals(listTeste.size(), resultado.size());

        IntStream.range(0, resultado.size())
                .forEach(i -> ConsultaValidator.validaConsultaDomain(
                        listDomain.get(i),
                        resultado.get(i)
                ));
    }

    @Test
    void testeExceptionConsultaSessoesDoMesmoDia() {
        Mockito.when(repository.consultarConsultasMesmoDia(Mockito.any(Integer.class), Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.consultarConsultasMesmoDia(12, consultaDomainTeste.getPsicologo().getId()));

        Assertions.assertEquals(exception.getMessage(), ConsultaDataProvider.MENSAGEM_ERRO_CONSULTAR_SESSOES_MESMO_DIA);
    }

    @Test
    void testeDelecaoDeConsulta() {
        UUID idTeste = consultaEntityTeste.getId();

        Mockito.doNothing().when(repository).deleteById(Mockito.any());

        dataProvider.deletar(idTeste);

        Mockito.verify(repository, Mockito.times(1)).deleteById(idTeste);
    }

    @Test
    void testeExceptionDelecaoDeConsulta() {
        UUID idTeste = consultaEntityTeste.getId();
        Mockito.doThrow(DataProviderException.class).when(repository).deleteById(Mockito.any());

        DataProviderException exception = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.deletar(idTeste));

        Assertions.assertEquals(exception.getMessage(), ConsultaDataProvider.MENSAGEM_ERRO_DELETAR_CONSULTA);
    }
}