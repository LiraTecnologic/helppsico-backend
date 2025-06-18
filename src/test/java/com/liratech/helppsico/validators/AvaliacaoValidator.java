package com.liratech.helppsico.validators;

import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.entrypoint.dto.psicologo.AvaliacaoDto;
import com.liratech.helppsico.infrastructure.repositories.entities.AvaliacaoEntity;
import org.junit.jupiter.api.Assertions;

public class AvaliacaoValidator {

    public static void validaAvaliacaoDomain (Avaliacao esperado, Avaliacao resultado) {
        PsicologoValidator.validaPsicologoDomain(esperado.getPsicologo(), resultado.getPsicologo());
        PacienteValidator.validaPacienteDomain(esperado.getPaciente(), resultado.getPaciente());
        Assertions.assertEquals(esperado.getNota(), resultado.getNota());
        Assertions.assertEquals(esperado.getComentario(), resultado.getComentario());
    }

    public static void validaAvaliacaoMapperInfra (Avaliacao domain, AvaliacaoEntity entity) {
        PsicologoValidator.validaPsicologoMapperInfra(domain.getPsicologo(), entity.getPsicologo());
        PacienteValidator.validaPacienteMapperInfra(domain.getPaciente(), entity.getPaciente());
        Assertions.assertEquals(domain.getNota(), entity.getNota());
        Assertions.assertEquals(domain.getComentario(), entity.getComentario());
    }

    public static void validaAvaliacaoMapperEntry (Avaliacao domain, AvaliacaoDto dto) {
        PsicologoValidator.validaPsicologoMapperEntry(domain.getPsicologo(), dto.getPsicologo());
        PacienteValidator.validaPacienteMapperEntry(domain.getPaciente(), dto.getPaciente());
        Assertions.assertEquals(domain.getNota(), dto.getNota());
        Assertions.assertEquals(domain.getComentario(), dto.getComentario());
    }
}
