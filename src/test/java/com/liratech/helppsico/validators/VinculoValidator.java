package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Vinculo;
import com.liratech.helppsico.entrypoint.dto.VinculoDto;
import com.liratech.helppsico.infrastructure.repositories.entities.VinculoEntity;
import org.junit.jupiter.api.Assertions;

public class VinculoValidator {
    public static void validaVinculoDomain(Vinculo esperado, Vinculo resultado){
        PsicologoValidator.validaPsicologoDomain(esperado.getPsicologo(), resultado.getPsicologo());
        PacienteValidator.validaPacienteDomain(esperado.getPaciente(), resultado.getPaciente());
        Assertions.assertEquals(esperado.getStatus(), resultado.getStatus());
    }

    public static void validaVinculoMapperEntry(Vinculo domain, VinculoDto dto){
        PsicologoValidator.validaPsicologoMapperEntry(domain.getPsicologo(), dto.getPsicologo());
        PacienteValidator.validaPacienteMapperEntry(domain.getPaciente(), dto.getPaciente());
        Assertions.assertEquals(domain.getStatus(), dto.getStatus());
    }

    public static void validaVinculoMapperInfra(Vinculo domain, VinculoEntity entity){
        PsicologoValidator.validaPsicologoMapperInfra(domain.getPsicologo(), entity.getPsicologo());
        PacienteValidator.validaPacienteMapperInfra(domain.getPaciente(), entity.getPaciente());
        Assertions.assertEquals(domain.getStatus(), entity.getStatus());
    }
}
