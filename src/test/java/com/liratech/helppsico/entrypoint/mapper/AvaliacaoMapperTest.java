package com.liratech.helppsico.entrypoint.mapper;

import com.liratech.helppsico.builders.AvaliacaoBuilder;
import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.entrypoint.dto.psicologo.AvaliacaoDto;
import com.liratech.helppsico.validators.PacienteValidator;
import com.liratech.helppsico.validators.PsicologoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class AvaliacaoMapperTest {

    private final AvaliacaoMapper avaliacaoMapper = Mappers.getMapper(AvaliacaoMapper.class);

    @Test
    @DisplayName("Caso de sucesso na transformação de DTO para Domain")
    void testeTransformacaoAvalicaoDtoParaDomain() {
        AvaliacaoDto avaliacaoDto = AvaliacaoBuilder.criarAvaliacaoDto();
        Avaliacao avaliacao = avaliacaoMapper.paraDomain(avaliacaoDto);

        Assertions.assertNotNull(avaliacao);
        Assertions.assertEquals(avaliacaoDto.getId(), avaliacao.getId());
        Assertions.assertNotNull(avaliacao.getPsicologo());
        PsicologoValidator.validaPsicologoDtoParaDomain(avaliacaoDto.getPsicologo(), avaliacao.getPsicologo());
        Assertions.assertNotNull(avaliacao.getPaciente());
        PacienteValidator.validaPacienteDtoParaDomain(avaliacaoDto.getPaciente(), avaliacao.getPaciente());
        Assertions.assertEquals(avaliacaoDto.getNota(), avaliacao.getNota());
        Assertions.assertEquals(avaliacaoDto.getComentario(), avaliacao.getComentario());
    }

    @Test
    @DisplayName("Caso de sucesso na transformação de Domain para DTO")
    void testeTransformacaoAvaliacaoDomainParaDto() {
        Avaliacao avaliacao = AvaliacaoBuilder.criarAvaliacao();
        AvaliacaoDto avaliacaoDto = avaliacaoMapper.paraDto(avaliacao);

        Assertions.assertNotNull(avaliacaoDto);
        Assertions.assertEquals(avaliacao.getId(),avaliacaoDto.getId());
        Assertions.assertNotNull(avaliacaoDto.getPsicologo());
        PsicologoValidator.validaPsicologoDomainParaDto(avaliacao.getPsicologo(), avaliacaoDto.getPsicologo());
        Assertions.assertNotNull(avaliacaoDto.getPaciente());
        PacienteValidator.validaPacienteDomainParaDto(avaliacao.getPaciente(),avaliacaoDto.getPaciente());
        Assertions.assertEquals(avaliacao.getNota(), avaliacaoDto.getNota());
        Assertions.assertEquals(avaliacao.getComentario(), avaliacaoDto.getComentario());
    }
}
