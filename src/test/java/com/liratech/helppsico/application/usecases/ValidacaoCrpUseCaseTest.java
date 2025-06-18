package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.avaliacao.AvaliacaoNaoEncontradaException;
import com.liratech.helppsico.application.exceptions.validacaoCrp.ValidacaoCrpExistenteException;
import com.liratech.helppsico.application.gateways.ValidacaoCrpGateway;
import com.liratech.helppsico.builders.AvaliacaoBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.builders.ValidacaoCrpBuilder;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.ValidacaoCrp;
import com.liratech.helppsico.validators.ValidacaoCrpValidator;
import org.junit.jupiter.api.Assertions;
import org.springframework.data.domain.Page;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.lang.management.MonitorInfo;
import java.util.Optional;
import java.util.UUID;

import static com.liratech.helppsico.application.usecases.AvaliacaoUseCase.MENSAGEM_AVALIACAO_NAO_ENCONTRADA;
import static com.liratech.helppsico.application.usecases.ValidacaoCrpUseCase.MENSAGEM_VALIDACAO_CRP_EXISTENTE;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidacaoCrpUseCaseTest {

    @Mock
    private ValidacaoCrpGateway gateway;

    @Mock
    private PsicologoUseCase psicologoUseCase;

    @InjectMocks
    private ValidacaoCrpUseCase useCase;

    @Captor
    private ArgumentCaptor<ValidacaoCrp> captor;

    @Test
    void testeValidarValidacaoCrp() {
        Psicologo psicologoTeste = PsicologoBuilder.criarPsicologo();
        ValidacaoCrp validacaoCrpTeste = ValidacaoCrpBuilder.criarValidacaoCrp();

        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(psicologoTeste);
        Mockito.when(gateway.consultarPorId(Mockito.any())).thenReturn(Optional.empty());

        gateway.salvar(validacaoCrpTeste);
        ValidacaoCrp validacaoResultado = captor.capture();

        Mockito.doNothing().when(psicologoUseCase.alterar(Mockito.any(), Mockito.any()));

        Assertions.assertNotNull(validacaoResultado.getId());
        ValidacaoCrpValidator.validaValidacaoCrpDomain(validacaoCrpTeste, validacaoResultado);
    }

    @Test
    void testeListar() {
        Page<ValidacaoCrp> validacaoCrpPage = ValidacaoCrpBuilder.criarPageValidacaoCrp();

        Mockito.when(gateway.listar(Mockito.any())).thenReturn(validacaoCrpPage);

        Page<ValidacaoCrp> validacoesCrp = useCase.listar(PageRequest.of(0,10));

        for(int i = 0; i < validacaoCrpPage.getSize(); i++){
            ValidacaoCrpValidator.validaValidacaoCrpDomain(validacaoCrpPage.toList().get(i), validacoesCrp.toList().get(i));
        }
    }
}