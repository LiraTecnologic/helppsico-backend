package com.liratech.helppsico.mapper;

import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.mapper.PacienteMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class PacienteMapperTest {

    private final PacienteMapper pacienteMapper = Mappers.getMapper(PacienteMapper.class);

    @Test
    @DisplayName("Caso de sucesso na tranformação de DTO para Domain")
    void transformacaoPacienteDeDtoParaDomain() {
        PacienteDto pacienteDto = PacienteBuilder.criarPacienteDto();
        Paciente paciente = pacienteMapper.paraDomain(pacienteDto);

        Assertions.assertNotNull(paciente);
        Assertions.assertEquals(pacienteDto.getId(), paciente.getId());
        Assertions.assertEquals(pacienteDto.getNome(), paciente.getNome());
        Assertions.assertEquals(pacienteDto.getCpf(), paciente.getCpf());
        Assertions.assertEquals(pacienteDto.getEmail(),paciente.getEmail());
        Assertions.assertEquals(pacienteDto.getTelefone(), paciente.getTelefone());
        Assertions.assertEquals(pacienteDto.getDataNascimento(), paciente.getDataNascimento());
        Assertions.assertEquals(pacienteDto.getSenha(), paciente.getSenha());
        Assertions.assertEquals(pacienteDto.getGenero(), paciente.getGenero());

        Assertions.assertNotNull(paciente.getEndereco());
        //Validar todo o Endereco

        Assertions.assertEquals(pacienteDto.getFotoUrl(), paciente.getFotoUrl());
    }

    @Test
    @DisplayName("Caso de sucesso na tranformação de Domain para Dto")
    void transformacaoPacienteDeDomainParaDto() {
    }
}