package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.entrypoint.dto.psicologo.ProntuarioDto;
import com.liratech.helppsico.infrastructure.repositories.entities.ProntuarioEntity;
import org.junit.jupiter.api.Assertions;

public class ProntuarioValidator {

    public static void validaProntuarioDomain(Prontuario resultado, Prontuario esperado) {
        PsicologoValidator.validaPsicologoDomain(resultado.getPsicologo(), esperado.getPsicologo());
        PacienteValidator.validaPacienteDomain(resultado.getPaciente(), esperado.getPaciente());
        ConsultaValidator.validaConsultaDomain(resultado.getConsulta(), esperado.getConsulta());
        Assertions.assertEquals(resultado.getTitulo(), esperado.getTitulo());
        Assertions.assertEquals(resultado.getConteudo(), esperado.getConteudo());
        Assertions.assertEquals(resultado.getDataCriacao(), esperado.getDataCriacao());
        Assertions.assertEquals(resultado.getDataEdicao(), esperado.getDataEdicao());
    }

    public static void validaProntuarioMapperEntry(Prontuario domain, ProntuarioDto dto) {
        PsicologoValidator.validaPsicologoMapperEntry(domain.getPsicologo(), dto.getPsicologo());
        PacienteValidator.validaPacienteMapperEntry(domain.getPaciente(), dto.getPaciente());
        ConsultaValidator.validaConsultaMapperEntry(domain.getConsulta(), dto.getConsulta());
        Assertions.assertEquals(domain.getTitulo(), dto.getTitulo());
        Assertions.assertEquals(domain.getConteudo(), dto.getConteudo());
        Assertions.assertEquals(domain.getDataCriacao(), dto.getDataCriacao());
        Assertions.assertEquals(domain.getDataEdicao(), dto.getDataEdicao());
    }

    public static void validaProntuarioMapperInfra(Prontuario domain, ProntuarioEntity entity) {
        PsicologoValidator.validaPsicologoMapperInfra(domain.getPsicologo(), entity.getPsicologo());
        PacienteValidator.validaPacienteMapperInfra(domain.getPaciente(), entity.getPaciente());
        ConsultaValidator.validaConsultaMapperInfra(domain.getConsulta(), entity.getConsulta());
        Assertions.assertEquals(domain.getTitulo(), entity.getTitulo());
        Assertions.assertEquals(domain.getConteudo(), entity.getConteudo());
        Assertions.assertEquals(domain.getDataCriacao(), entity.getDataCriacao());
        Assertions.assertEquals(domain.getDataEdicao(), entity.getDataEdicao());
    }

}
