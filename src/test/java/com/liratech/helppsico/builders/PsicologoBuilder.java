package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.TipoGenero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PsicologoBuilder {
    
    public static Psicologo gerarPsicologo() {
        return Psicologo.builder()
                .id(UUID.randomUUID())
                .nome("Psicologo teste")
                .crp("0100000")
                .cpf("12332114763")
                .email("emailteste@gmail.com")
                .telefone("44987415623")
                .dataNascimento(LocalDate.now())
                .senha("senhateste123!")
                .genero(TipoGenero.MASCULINO)
                .enderecoAtendimento(EnderecoBuilder.gerarEndereco())
                .fotoUrl("urltestefoto")
                .biografia("Biografia teste")
                .build();
    }

    public static List<Psicologo> gerarListaDePsicologos() {
        List<Psicologo> psicologoList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            psicologoList.add(gerarPsicologo());
        }

        return psicologoList;
    }

    public static Page<Psicologo> gerarPageDePsicologos() {
        List<Psicologo> psicologoList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            psicologoList.add(gerarPsicologo());
        }

        return transformarListaEmPagina(psicologoList, PageRequest.of(0,10));
    }

    private static Page<Psicologo> transformarListaEmPagina(List<Psicologo> lista, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), lista.size());

        List<Psicologo> sublist = lista.subList(start, end);

        return new PageImpl<>(sublist, pageable, lista.size());
    }
}
