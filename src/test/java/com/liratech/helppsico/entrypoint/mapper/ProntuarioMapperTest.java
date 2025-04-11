package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.ProntuarioBuilder;
import com.liratech.helppsico.domain.Prontuario;
import com.liratech.helppsico.entrypoint.dto.psicologo.ProntuarioDto;
import com.liratech.helppsico.validators.PacienteValidator;
import com.liratech.helppsico.validators.PsicologoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ProntuarioMapperTest {

    private final ProntuarioMapper prontuarioMapper = Mappers.getMapper(ProntuarioMapper.class);

    @Test
    @DisplayName("Caso de sucesso na tranformação de DTO para Domain")
    void testeTransformacaoProntuarioDtoParaDomain() {
        ProntuarioDto prontuarioDto = ProntuarioBuilder.criarProntuarioDto();
        Prontuario prontuario = prontuarioMapper.paraDomain(prontuarioDto);

        Assertions.assertNotNull(prontuario);
        Assertions.assertEquals(prontuarioDto.getId(), prontuario.getId());
        PsicologoValidator.validaPsicologoDtoParaDomain(prontuarioDto.getPsicologo(), prontuario.getPsicologo());
        PacienteValidator.validaPacienteDtoParaDomain(prontuarioDto.getPaciente(),prontuario.getPaciente());
        Assertions.assertEquals(prontuarioDto.getTitulo(), prontuario.getTitulo());
        Assertions.assertEquals(prontuarioDto.getConteudo(), prontuario.getConteudo());
    }

    @Test
    @DisplayName("Caso de sucesso na tranformação de Domain para DTO")
    void testeTransformacaoProntuarioDomainParaDto() {
        Prontuario prontuario = ProntuarioBuilder.criarProntuario();
        ProntuarioDto prontuarioDto = prontuarioMapper.paraDto(prontuario);

        Assertions.assertNotNull(prontuarioDto);
        Assertions.assertEquals(prontuario.getId(), prontuarioDto.getId());
        PsicologoValidator.validaPsicologoDomainParaDto(prontuario.getPsicologo(), prontuarioDto.getPsicologo());
        PacienteValidator.validaPacienteDomainParaDto(prontuario.getPaciente(), prontuarioDto.getPaciente());
        Assertions.assertEquals(prontuario.getTitulo(),prontuarioDto.getTitulo());
        Assertions.assertEquals(prontuario.getConteudo(), prontuarioDto.getConteudo());
    }
}