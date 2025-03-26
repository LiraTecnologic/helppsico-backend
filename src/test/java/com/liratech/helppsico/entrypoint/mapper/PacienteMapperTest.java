package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.mapper.PacienteMapper;
import com.liratech.helppsico.validator.PacienteValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class PacienteMapperTest {

    private final PacienteMapper pacienteMapper = Mappers.getMapper(PacienteMapper.class);

    @Test
    @DisplayName("Caso de sucesso na tranformação de DTO para Domain")
    void testeTransformacaoPacienteDeDtoParaDomain() {
        PacienteDto pacienteDto = PacienteBuilder.criarPacienteDto();
        Paciente paciente = pacienteMapper.paraDomain(pacienteDto);

        Assertions.assertNotNull(paciente.getEndereco());
        PacienteValidator.validaPacienteDtoParaDomain(pacienteDto, paciente);
    }

    @Test
    @DisplayName("Caso de sucesso na tranformação de Domain para Dto")
    void testeTransformacaoPacienteDeDomainParaDto() {
        Paciente paciente = PacienteBuilder.criarPaciente();
        PacienteDto pacienteDto = pacienteMapper.paraDto(paciente);

        Assertions.assertNotNull(pacienteDto);
        PacienteValidator.validaPacienteDomainParaDto(paciente, pacienteDto);
    }
}