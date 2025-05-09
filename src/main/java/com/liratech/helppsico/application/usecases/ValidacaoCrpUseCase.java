package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.validacaoCrp.ValidacaoCrpExistenteException;
import com.liratech.helppsico.application.exceptions.validacaoCrp.ValidacaoCrpSolicitadaException;
import com.liratech.helppsico.application.gateways.ValidacaoCrpGateway;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.StatusPsicologo;
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
    public static final String MENSAGEM_VALIDACAO_CRP_JA_SOLICITADA = "Validação CRP já foi solicitada";

    public ValidacaoCrp criar(ValidacaoCrp validacaoCrp){
        log.info("Criando Validação de CRP. Nova Validação: {}", validacaoCrp);

        Psicologo psicologo = psicologoUseCase.consultarPorId(validacaoCrp.getPsicologo().getId());
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
        log.info("Validando o CRP. Validacao: {}", validacaoCrp);

        Psicologo psicologo = psicologoUseCase.consultarPorId(validacaoCrp.getPsicologo().getId());
        validacaoCrp.setPsicologo(psicologo);

        Optional<ValidacaoCrp> validacaoConsulta = gateway.consultarPorId(id);

        if(validacaoConsulta.isPresent()){
            throw new ValidacaoCrpSolicitadaException(MENSAGEM_VALIDACAO_CRP_JA_SOLICITADA);
        }

        if(validacaoCrp.getMotivoReprova() == null || validacaoCrp.getMotivoReprova().isBlank()){
            psicologo.setStatusPsicologo(StatusPsicologo.APROVADO);
        } else {
            psicologo.setStatusPsicologo(StatusPsicologo.NAO_APROVADO);
        }

        ValidacaoCrp validacaoSalva = gateway.salvar(validacaoCrp);
        // Atualiza o Psicologo ...

        log.info("Validação realizada com sucesso. Validação: {}", validacaoSalva);

        return validacaoSalva;
    }

    public Page<ValidacaoCrp> listar(Pageable pageable){

    }
}
