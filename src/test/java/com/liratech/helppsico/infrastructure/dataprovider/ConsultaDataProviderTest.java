package com.liratech.helppsico.infrastructure.dataprovider;

import com.liratech.helppsico.builders.ConsultaBuilder;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.infrastructure.dataprovider.exceptions.DataProviderException;
import com.liratech.helppsico.infrastructure.mapper.ConsultaMapper;
import com.liratech.helppsico.infrastructure.repositories.ConsultaRepository;
import com.liratech.helppsico.infrastructure.repositories.entities.ConsultaEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import com.liratech.helppsico.validators.ConsultaValidator;
import com.liratech.helppsico.validators.PacienteValidator;
import com.liratech.helppsico.validators.PsicologoValidator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
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
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class ConsultaDataProviderTest {

    @Mock
    private final ConsultaRepository repository;

    @InjectMocks
    private final ConsultaDataProvider dataProvider;

    private final ConsultaEntity consultaEntityTeste = ConsultaBuilder.criarConsultaEntity();
    private final Consulta consultaDomainTeste = ConsultaBuilder.criarConsulta();
    private final Page<ConsultaEntity> pageConsultaEntitiesTeste = ConsultaBuilder.criarPageConsultaEntity();
    private final Paciente pacienteDomainTeste = PacienteBuilder.criarPaciente();
    private final Psicologo psicologoDomainTeste = PsicologoBuilder.criarPsicologo();

    private final ConsultaMapper mapper;

    @Test
    void testeSalvar() {
        consultaDomainTeste.setId(null);
        Mockito.when(repository.save(Mockito.any())).thenReturn(consultaEntityTeste);

        Consulta resultado = dataProvider.salvar(consultaDomainTeste);

        Assertions.assertNotNull(resultado.getId());
        ConsultaValidator.validaConsultaDomain(resultado, consultaDomainTeste);
    }

    @Test
    void testaExceptionSalvar() {
        Mockito.when(repository.save(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.salvar(consultaDomainTeste));
        Assertions.assertEquals(exception.getMessage(), ConsultaDataProvider.MENSAGEM_ERRO_SALVAR);
    }

    @Test
    void testeConsultaPorId() {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(mapper.paraEntity(consultaDomainTeste)));

        Optional<Consulta> resultado = dataProvider.consultarPorId(consultaDomainTeste.getId());

        resultado.ifPresent(consulta -> {
            Assertions.assertEquals(consulta.getId(), consultaDomainTeste.getId());
            ConsultaValidator.validaConsultaDomain(consulta, consultaDomainTeste);
        });
    }

    @Test
    void testaExceptionConsultaPorId() {
        Mockito.when(repository.findById(Mockito.any())).thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.consultarPorId(consultaDomainTeste.getId()));

        Assertions.assertEquals(exception.getMessage(), ConsultaDataProvider.MENSAGEM_ERRO_CONSULTAR_POR_ID);
    }

    @Test
    void consultarConsultasFuturas() {
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(repository.consultarConsultasFuturas(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(pageConsultaEntitiesTeste);

        Page<Consulta> resultado = dataProvider.consultarConsultasFuturas(
                        psicologoDomainTeste.getId(), pacienteDomainTeste.getId(), pageable
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
    void testeConsultaHistorico() {
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(repository.consultarHistorico(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(pageConsultaEntitiesTeste);

        Page<Consulta> resultado = dataProvider.consultarHistorico(
                psicologoDomainTeste.getId(), pacienteDomainTeste.getId(), pageable
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
    void testeExceptionConsultarHistorico() {
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(repository.consultarHistorico(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenThrow(RuntimeException.class);

        DataProviderException exception = Assertions.assertThrows(DataProviderException.class,
                () -> dataProvider.consultarHistorico(psicologoDomainTeste.getId(), pacienteDomainTeste.getId(), pageable));

        Assertions.assertEquals(exception.getMessage(), ConsultaDataProvider.MENSAGEM_ERRO_CONSULTAR_HISTORICO);
    }


}