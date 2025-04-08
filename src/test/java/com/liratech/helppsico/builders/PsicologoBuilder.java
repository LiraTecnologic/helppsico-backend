package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.TipoGenero;
import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import com.liratech.helppsico.infrastructure.repositories.entities.PsicologoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PsicologoBuilder {
    public static Psicologo criarPsicologo() {
        return Psicologo.builder()
                .id(UUID.randomUUID())
                .nome("João Silva")
                .crp("123456")
                .cpf("12345678901")
                .email("joao.silva@example.com")
                .telefone("(11) 98765-4321")
                .dataNascimento(LocalDate.of(1985, 5, 20))
                .senha("Senha@123")
                .genero(TipoGenero.MASCULINO)
                .enderecoAtendimento(EnderecoBuilder.criarEndereco())
                .fotoUrl("https://example.com/foto.jpg")
                .biografia("Psicólogo com 10 anos de experiência em terapia cognitivo-comportamental.")
                .build();
    }

    public static PsicologoDto criarPsicologoDto() {
        return PsicologoDto.builder()
                .id(UUID.randomUUID())
                .nome("João Silva")
                .crp("123456")
                .cpf("12345678901")
                .email("joao.silva@example.com")
                .telefone("(11) 98765-4321")
                .dataNascimento(LocalDate.of(1985, 5, 20))
                .senha("Senha@123")
                .genero(TipoGenero.MASCULINO)
                .enderecoAtendimento(EnderecoBuilder.criarEnderecoDto())
                .fotoUrl("https://example.com/foto.jpg")
                .biografia("Psicólogo com 10 anos de experiência em terapia cognitivo-comportamental.")
                .build();
    }


    public static List<Psicologo> gerarListaDePsicologos() {
        List<Psicologo> psicologoList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            psicologoList.add(criarPsicologo());
        }

        return psicologoList;
    }

    public static List<PsicologoDto> criarListaPsicologoDto() {
        List<PsicologoDto> psicologoListDtos = new ArrayList<>();

        for(int i =0; i<3; i++){
            psicologoListDtos.add(criarPsicologoDto());
        }

        return psicologoListDtos;
    }

    public static Page<Psicologo> criarPageDePsicologos() {
        List<Psicologo> psicologoList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            psicologoList.add(criarPsicologo());
        }

        return transformarListaEmPagina(psicologoList, PageRequest.of(0,10));
    }

    private static Page<Psicologo> transformarListaEmPagina(List<Psicologo> lista, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), lista.size());

        List<Psicologo> sublist = lista.subList(start, end);

        return new PageImpl<>(sublist, pageable, lista.size());
    }

    public static Page<PsicologoDto> criarPageDePsicologosDto() {
        List<PsicologoDto> psicologoDtoList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            psicologoDtoList.add(criarPsicologoDto());
        }

        return transformarListaEmPaginaDto(psicologoDtoList, PageRequest.of(0,10));
    }

    private static Page<PsicologoDto> transformarListaEmPaginaDto(List<PsicologoDto> lista, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), lista.size());

        List<PsicologoDto> sublist = lista.subList(start, end);

        return new PageImpl<>(sublist, pageable, lista.size());
    }

    public static PsicologoEntity criarPsicologoEntity() {
        return PsicologoEntity.builder()
                .id(UUID.randomUUID())
                .nome("João Silva")
                .crp("123456")
                .cpf("12345678901")
                .email("joao.silva@example.com")
                .telefone("(11) 98765-4321")
                .dataNascimento(LocalDate.of(1985, 5, 20))
                .senha("Senha@123")
                .genero(TipoGenero.MASCULINO)
                .enderecoAtendimento(EnderecoBuilder.criarEnderecoEntity())
                .fotoUrl("https://example.com/foto.jpg")
                .biografia("Psicólogo com 10 anos de experiência em terapia cognitivo-comportamental.")
                .build();
    }

    public static List<PsicologoEntity> criarListaPsicologoEntity() {
        List<PsicologoEntity> psicologoEntities = new ArrayList<>();

        for(int i=0; i<3; i++){
            psicologoEntities.add(criarPsicologoEntity());
        }

        return psicologoEntities;
    }

    public static Psicologo criarPsicologoNovosDados() {
        return Psicologo.builder()
                .id(null)
                .nome("Jonatham Silva")
                .crp(null)
                .cpf(null)
                .email("jonatham.silva@example.com")
                .telefone("(11) 98765-4321")
                .dataNascimento(LocalDate.of(1987, 5, 20))
                .senha(null)
                .genero(TipoGenero.MASCULINO)
                .enderecoAtendimento(EnderecoBuilder.criarEndereco())
                .fotoUrl("https://example.com/foto.jpg")
                .biografia("Psicólogo com 10 anos de experiência em terapia de sono.")
                .build();
    }
}
