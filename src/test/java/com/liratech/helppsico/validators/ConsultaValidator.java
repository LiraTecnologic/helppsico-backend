package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Consulta;
import com.liratech.helppsico.entrypoint.dto.consulta.ConsultaDto;
import com.liratech.helppsico.infrastructure.repositories.entities.ConsultaEntity;
import org.junit.jupiter.api.Assertions;

public class ConsultaValidator {

    public static void validaConsultaDomain(Consulta esperado, Consulta resultado) {
        PsicologoValidator.validaPsicologoDomain(esperado.getPsicologo(), resultado.getPsicologo());
        PacienteValidator.validaPacienteDomain(esperado.getPaciente(), resultado.getPaciente());
        HorarioValidator.validaHorarioDomain(esperado.getHorario(), resultado.getHorario());
        Assertions.assertEquals(esperado.getData(), resultado.getData());
        Assertions.assertEquals(esperado.getValor(), resultado.getValor());
        EnderecoValidator.validaEnderecoDomain(esperado.getEndereco(), resultado.getEndereco());
        Assertions.assertEquals(esperado.getFinalizada(), resultado.getFinalizada());
    }
    public static void validaConsultaMapperEntry(Consulta domain, ConsultaDto dto) {
        PsicologoValidator.validaPsicologoMapperEntry(domain.getPsicologo(), dto.getPsicologo());
        PacienteValidator.validaPacienteMapperEntry(domain.getPaciente(), dto.getPaciente());
        HorarioValidator.validaHorarioMapperEntry(domain.getHorario(), dto.getHorario());
        Assertions.assertEquals(domain.getData(), dto.getData());
        Assertions.assertEquals(domain.getValor(), dto.getValor());
        EnderecoValidator.validaEnderecoMapperEntry(domain.getEndereco(), dto.getEndereco());
        Assertions.assertEquals(domain.getFinalizada(), dto.getFinalizada());
    }

    public static void validaConsultaMapperInfra(Consulta domain, ConsultaEntity entity) {
        PsicologoValidator.validaPsicologoMapperInfra(domain.getPsicologo(), entity.getPsicologo());
        PacienteValidator.validaPacienteMapperInfra(domain.getPaciente(), entity.getPaciente());
        HorarioValidator.validaHorarioMapperInfra(domain.getHorario(), entity.getHorario());
        Assertions.assertEquals(domain.getData(), entity.getData());
        Assertions.assertEquals(domain.getValor(), entity.getValor());
        EnderecoValidator.validaEnderecoMapperInfra(domain.getEndereco(), entity.getEndereco());
        Assertions.assertEquals(domain.getFinalizada(), entity.getFinalizada());
    }
}
