package com.liratech.helppsico.validators;


import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import org.junit.jupiter.api.Assertions;

public class SolicitacaoDocumentoValidator {
    public static void validaSolicitacao (SolicitacaoDocumento esperado, SolicitacaoDocumento resultado){
        Assertions.assertEquals(esperado.getId(), resultado.getId());
        PsicologoValidator.validaPsicologoDomain(esperado.getPsicologo(), resultado.getPsicologo());
        PacienteValidator.validaPacienteDomain(esperado.getPaciente(), esperado.getPaciente());
        Assertions.assertEquals(esperado.getTipoDocumento(), resultado.getTipoDocumento());
    }
}
