package com.liratech.helppsico.mapper;

import com.liratech.helppsico.builders.AvaliacaoDtoBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.builders.AvaliacaoBuilder;
import com.liratech.helppsico.builders.PsicologoDtoBuilder;
import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.entrypoint.dto.psicologo.AvaliacaoDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import com.liratech.helppsico.entrypoint.mapper.AvaliacaoMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AvaliacaoMapperTest {

    private final AvaliacaoMapper avaliacaoMapper = Mappers.getMapper(AvaliacaoMapper.class);

    @Test
    @DisplayName("Caso de sucesso na transformação de DTO para Domain")
    void transformacaoDtoParaDomainSucesso() {
        AvaliacaoDto avaliacaoDto = AvaliacaoDtoBuilder.criarAvaliacaoDto();
        Avaliacao avaliacao = avaliacaoMapper.paraDomain(avaliacaoDto);

        assertThat(avaliacao).isNotNull();
        assertThat(avaliacao.getId()).isEqualTo(avaliacaoDto.getId());
        assertThat(avaliacao.getPsicologo().getId()).isEqualTo(avaliacaoDto.getPsicologo().getId());
        assertThat(avaliacao.getNota()).isEqualTo(avaliacaoDto.getNota());
        assertThat(avaliacao.getComentario()).isEqualTo(avaliacaoDto.getComentario());
    }

    @Test
    @DisplayName("Caso de falha na transformação de DTO para Domain (psicologo nulo)")
    void transformacaoDtoParaDomainFalha() {
        AvaliacaoDto avaliacaoDto = AvaliacaoDto.builder()
                .id(UUID.randomUUID())
                .psicologo(null)
                .nota(4.5)
                .comentario("Bom psicologo")
                .build();

        assertThrows(NullPointerException.class, () -> {
            avaliacaoMapper.paraDomain(avaliacaoDto);
        });
    }

    @Test
    @DisplayName("Caso de sucesso na transformação de Domain para DTO")
    void transformacaoDomainParaDtoSucesso() {
        Avaliacao avalicao = AvaliacaoBuilder.criarAvaliacao();
        AvaliacaoDto avaliacaoDto = avaliacaoMapper.paraDto(avalicao);

        assertThat(avaliacaoDto).isNotNull();
        assertThat(avaliacaoDto.getId()).isEqualTo(avalicao.getId());
        assertThat(avaliacaoDto.getPsicologo().getId()).isEqualTo(avalicao.getPsicologo().getId());
        assertThat(avaliacaoDto.getNota()).isEqualTo(avalicao.getNota());
        assertThat(avaliacaoDto.getComentario()).isEqualTo(avalicao.getComentario());
    }

    @Test
    @DisplayName("Caso de falha na transformação de Domain para DTO (nota negativa)")
    void transformacaoDomainParaDtoFalha() {
        Psicologo psicologo = PsicologoBuilder.criarPsicologo();
        Avaliacao avaliacao = Avaliacao.builder()
                .id(UUID.randomUUID())
                .psicologo(psicologo)
                .nota(-1.0)
                .comentario("Comentário inválido")
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            avaliacaoMapper.paraDto(avaliacao);
        });
    }
}
