package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.entrypoint.dto.consulta.ConsultaDto;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.web.servlet.ResultActions;

public class ConsultaValidator {

    public static void validaConsultaDomain(Consulta resultado, Consulta comparacao) {
        PsicologoValidator.validaPsicologoDomain(resultado.getPsicologo(), comparacao.getPsicologo());
        PacienteValidator.validaPacienteDomain(resultado.getPaciente(), comparacao.getPaciente());
        Assertions.assertEquals(resultado.getDataHora(), comparacao.getDataHora());
        Assertions.assertEquals(resultado.getValor(), comparacao.getValor());
        EnderecoValidator.validaEnderecoDomain(resultado.getEndereco(), comparacao.getEndereco());
        Assertions.assertEquals(resultado.getFinalizada(), comparacao.getFinalizada());
    }
}
