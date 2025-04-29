package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import org.junit.jupiter.api.Assertions;

public class SolicitacaoDocumentoValidator {
    public static void validaSolicitacaoDocumentoDomain(SolicitacaoDocumento resultado, SolicitacaoDocumento comparacao){
        PsicologoValidator.validaPsicologoDomain(resultado.getPsicologo(), comparacao.getPsicologo());
        PacienteValidator.validaPacienteDomain(resultado.getPaciente(), comparacao.getPaciente());
        Assertions.assertEquals(resultado.getTipoDocumento(), comparacao.getTipoDocumento());
    }
}
