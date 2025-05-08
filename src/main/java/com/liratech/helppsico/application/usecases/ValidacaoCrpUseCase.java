package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.validacaoCrp.ValidacaoCrpExistenteException;
import com.liratech.helppsico.application.gateways.ValidacaoCrpGateway;
import com.liratech.helppsico.domain.Psicologo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ValidacaoCrpUseCase {

    private final ValidacaoCrpGateway gateway;
    private final PsicologoUseCase psicologoUseCase;

    public static final String MENSAGEM_VALIDACAO_CRP_EXISTENTE = "Validação CRP já existente";

    public ValidacaoCrp criar(ValidacaoCrp validacaoCrp){
        log.info("Criando Validação de CRP. Nova Validação: {}", validacaoCrp);

        Psicologo psicologo = psicologoUseCase.consultarPorCrp(validacaoCrp.getCrp());
        validacaoCrp.setPsicologo(psicologo);

        Optional<ValidacaoCrp> validacaoConsulta = gateway.consultarPorPsicologoId(psicologo.getId());

        if(validacaoConsulta.isPresent()){
            throw new ValidacaoCrpExistenteException(MENSAGEM_VALIDACAO_CRP_EXISTENTE);
        }

        ValidacaoCrp validacaoSalva = gateway.salvar(validacaoCrp);

        log.info("Validação criada com sucesso. Validação: {}", validacaoSalva);

        return validacaoSalva;
    }

    public ValidacaoCrp validar(ValidacaoCrp validacaoCrp, UUID id){

    }

    public Page<ValidacaoCrp> listar(Pageable pageable){

    }
}
