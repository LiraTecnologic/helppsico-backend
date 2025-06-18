package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.documento.SolicitacaoDocumento;
import com.liratech.helppsico.entrypoint.dto.documento.SolicitacaoDocumentoDto;
import com.liratech.helppsico.infrastructure.repositories.entities.documento.SolicitacaoDocumentoEntity;
import org.junit.jupiter.api.Assertions;

public class SolicitacaoDocumentoValidator {
    public static void validaSolicitacaoDocumentoDomain(SolicitacaoDocumento resultado, SolicitacaoDocumento comparacao){
        PsicologoValidator.validaPsicologoDomain(resultado.getPsicologo(), comparacao.getPsicologo());
        PacienteValidator.validaPacienteDomain(resultado.getPaciente(), comparacao.getPaciente());
        Assertions.assertEquals(resultado.getTipoDocumento(), comparacao.getTipoDocumento());
    }

    public static void validaSolicitacaoDocumentoMapperEntry(SolicitacaoDocumento domain, SolicitacaoDocumentoDto dto){
        PsicologoValidator.validaPsicologoMapperEntry(domain.getPsicologo(), dto.getPsicologo());
        PacienteValidator.validaPacienteMapperEntry(domain.getPaciente(), dto.getPaciente());
        Assertions.assertEquals(domain.getTipoDocumento(), dto.getTipoDocumento());
    }

    public static void validaSolicitacaoDocumentoMapperInfra(SolicitacaoDocumento domain, SolicitacaoDocumentoEntity entity){
        PsicologoValidator.validaPsicologoMapperInfra(domain.getPsicologo(), entity.getPsicologo());
        PacienteValidator.validaPacienteMapperInfra(domain.getPaciente(), entity.getPaciente());
        Assertions.assertEquals(domain.getTipoDocumento(), entity.getTipoDocumento());
    }
}
