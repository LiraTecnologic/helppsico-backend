package com.liratech.helppsico.mapper;

import com.liratech.helppsico.builders.AvaliacaoBuilder;
import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.entrypoint.dto.psicologo.AvaliacaoDto;
import com.liratech.helppsico.entrypoint.mapper.AvaliacaoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class AvaliacaoMapperTest {

    private final AvaliacaoMapper avaliacaoMapper = Mappers.getMapper(AvaliacaoMapper.class);

    @Test
    @DisplayName("Caso de sucesso na transformação de DTO para Domain")
    void transformacaoAvalicaoDtoParaDomain() {
        AvaliacaoDto avaliacaoDto = AvaliacaoBuilder.criarAvaliacaoDto();
        Avaliacao avaliacao = avaliacaoMapper.paraDomain(avaliacaoDto);

        Assertions.assertNotNull(avaliacao);
        Assertions.assertEquals(avaliacaoDto.getId(), avaliacao.getId());

        Assertions.assertNotNull(avaliacao.getPsicologo());
        //Comparar todos os campos de Psicologo

        Assertions.assertNotNull(avaliacao.getPaciente());
        //Comparar todos os campos de Paciente

        Assertions.assertEquals(avaliacaoDto.getNota(), avaliacao.getNota());
        Assertions.assertEquals(avaliacaoDto.getComentario(), avaliacao.getComentario());
    }

    @Test
    @DisplayName("Caso de sucesso na transformação de Domain para DTO")
    void transformacaoAvaliacaoDomainParaDto() {
        Avaliacao avaliacao = AvaliacaoBuilder.criarAvaliacao();
        AvaliacaoDto avaliacaoDto = avaliacaoMapper.paraDto(avaliacao);

        Assertions.assertNotNull(avaliacaoDto);
        Assertions.assertEquals(avaliacao.getId(),avaliacaoDto.getId());

        Assertions.assertNotNull(avaliacaoDto.getPsicologo());
        //Comparar todos os campos de Psicologo

        Assertions.assertNotNull(avaliacaoDto.getPaciente());
        //Comparar todos os campos de Paciente

        Assertions.assertEquals(avaliacao.getNota(), avaliacaoDto.getNota());
        Assertions.assertEquals(avaliacao.getComentario(), avaliacaoDto.getComentario());
    }
}
