package com.liratech.helppsico.mapper;

import com.liratech.helppsico.domain.Avaliacao;
import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.TipoGenero;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.entrypoint.dto.TipoGeneroDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.AvaliacaoDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AvaliacaoMapperTest {

    private final AvaliacaoMapper avaliacaoMapper = Mappers.getMapper(AvaliacaoMapper.class);

    @Test
    @DisplayName("Caso de sucesso na transformação de DTO para Domain")
    void transformacaoDtoParaDomainSucesso() {
        UUID id = UUID.randomUUID();
        PsicologoDto psicologoDto = criarPsicologoDtoDeTeste();

        AvaliacaoDto avaliacaoDto = AvaliacaoDto.builder()
                .id(id)
                .psicologo(psicologoDto)
                .nota(4.5)
                .comentario("Bom psicologo")
                .build();

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
        Psicologo psicologo = criarPsicologoDeTeste();
        Avaliacao avaliacao = Avaliacao.builder()
                .id(UUID.randomUUID())
                .psicologo(psicologo)
                .nota(4.5)
                .comentario("Bom psicologo")
                .build();

        AvaliacaoDto avaliacaoDto = avaliacaoMapper.paraDto(avaliacao);

        assertThat(avaliacaoDto).isNotNull();
        assertThat(avaliacaoDto.getId()).isEqualTo(avaliacao.getId());
        assertThat(avaliacaoDto.getPsicologo().getId()).isEqualTo(avaliacao.getPsicologo().getId());
        assertThat(avaliacaoDto.getNota()).isEqualTo(avaliacao.getNota());
        assertThat(avaliacaoDto.getComentario()).isEqualTo(avaliacao.getComentario());
    }

    @Test
    @DisplayName("Caso de falha na transformação de Domain para DTO (nota negativa)")
    void transformacaoDomainParaDtoFalha() {
        Psicologo psicologo = criarPsicologoDeTeste();
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

    public static PsicologoDto criarPsicologoDtoDeTeste() {
        UUID id = UUID.randomUUID();
        return PsicologoDto.builder()
                .id(id)
                .nome("Dr. João Silva")
                .crp("123456")
                .cpf("12345678901")
                .email("joao.silva@example.com")
                .telefone("(11) 98765-4321")
                .dataNascimento(LocalDate.of(1985, 5, 20))
                .senha("Senha@123")
                .genero(TipoGeneroDto.MASCULINO)
                .enderecoAtendimento(new EnderecoDto(id, "Rua Teste", 123, "São Paulo", "SP", "01000-000"))
                .fotoUrl("https://example.com/foto.jpg")
                .biografia("Psicólogo com 10 anos de experiência em terapia cognitivo-comportamental.")
                .build();
    }

    public static Psicologo criarPsicologoDeTeste() {
        UUID id = UUID.randomUUID();
        return Psicologo.builder()
                .id(id)
                .nome("Dr. João Silva")
                .crp("123456")
                .cpf("12345678901")
                .email("joao.silva@example.com")
                .telefone("(11) 98765-4321")
                .dataNascimento(LocalDate.of(1985, 5, 20))
                .senha("Senha@123")
                .genero(TipoGenero.MASCULINO)
                .enderecoAtendimento(new Endereco(id, "Rua Teste", 123, "São Paulo", "SP", "01000-000"))
                .fotoUrl("https://example.com/foto.jpg")
                .biografia("Psicólogo com 10 anos de experiência em terapia cognitivo-comportamental.")
                .build();
    }
}
