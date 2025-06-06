package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.validators.PacienteValidator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

@AllArgsConstructor
class PacienteMapperTest {

    private PacienteMapper mapper;
    private Paciente domainTest;
    private PacienteDto dtoTest;

    @Test
    void testePacienteDomainParaDto() {
        domainTest = PacienteBuilder.criarPaciente();
        dtoTest = mapper.paraDto(domainTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        PacienteValidator.validaPacienteMapperEntry(domainTest, dtoTest);
    }

    @Test
    void testePacienteDtoParaDomain() {
        dtoTest = PacienteBuilder.criarPacienteDto();
        domainTest = mapper.paraDomain(dtoTest);

        Assertions.assertEquals(domainTest.getId(), dtoTest.getId());
        PacienteValidator.validaPacienteMapperEntry(domainTest, dtoTest);
    }
}