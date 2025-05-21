package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.validacaoCrp.ValidacaoCrpExistenteException;
import com.liratech.helppsico.application.exceptions.validacaoCrp.ValidacaoCrpNaoExistenteException;
import com.liratech.helppsico.application.gateways.ValidacaoCrpGateway;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.StatusPsicologo;
import com.liratech.helppsico.domain.ValidacaoCrp;
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
    public static final String MENSAGEM_VALIDACAO_CRP_NAO_EXISTENTE = "Validação CRP não existe";

    public ValidacaoCrp criar(ValidacaoCrp validacaoCrp){
        log.info("Criando Validação de CRP. Nova Validação: {}", validacaoCrp);

        Psicologo psicologo = psicologoUseCase.consultarPorId(validacaoCrp.getPsicologo().getId());
        validacaoCrp.setPsicologo(psicologo);

        Optional<ValidacaoCrp> validacaoConsulta = gateway.consultarPorPsicologo(psicologo.getId());

        if(validacaoConsulta.isPresent()){
            throw new ValidacaoCrpExistenteException(MENSAGEM_VALIDACAO_CRP_EXISTENTE);
        }

        ValidacaoCrp validacaoSalva = gateway.salvar(validacaoCrp);

        log.info("Validação criada com sucesso. Validação: {}", validacaoSalva);

        return validacaoSalva;
    }

    public ValidacaoCrp validar(ValidacaoCrp validacaoCrpNovo, UUID id){
        log.info("Validando o CRP. Validacao: {}", validacaoCrpNovo);

        ValidacaoCrp validacaoConsultado = consultarPorId(id);
        Psicologo psicologo = psicologoUseCase.consultarPorId(validacaoCrpNovo.getPsicologo().getId());

        validacaoConsultado.setMotivoReprova(validacaoCrpNovo.getMotivoReprova());

        if(validacaoCrpNovo.getMotivoReprova() == null || validacaoCrpNovo.getMotivoReprova().isBlank()){
            psicologo.setStatusPsicologo(StatusPsicologo.APROVADO);
        } else {
            psicologo.setStatusPsicologo(StatusPsicologo.NAO_APROVADO);
        }

        ValidacaoCrp validacaoSalva = gateway.salvar(validacaoConsultado);

        psicologoUseCase.alterar(psicologo, psicologo.getId());

        log.info("Validação realizada com sucesso. Validação: {}", validacaoSalva);

        return validacaoSalva;
    }

    public Page<ValidacaoCrp> listar(Pageable pageable){
        log.info("Listando as validações de crp.");

        Page<ValidacaoCrp> validacaoCrpPage = gateway.listar(pageable);

        log.info("Lista de todas as validações. Lista: {}",validacaoCrpPage);

        return validacaoCrpPage;
    }

    private ValidacaoCrp consultarPorId (UUID id){
        Optional<ValidacaoCrp> validacaoCrpBuscado = gateway.consultarPorId(id);

        if(validacaoCrpBuscado.isEmpty()){
            throw new ValidacaoCrpNaoExistenteException(MENSAGEM_VALIDACAO_CRP_NAO_EXISTENTE);
        }

        return validacaoCrpBuscado.get();
    }
}
