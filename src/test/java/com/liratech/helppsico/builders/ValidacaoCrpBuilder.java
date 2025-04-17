package com.liratech.helppsico.builders;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ValidacaoCrpBuilder {
    public static ValidacaoCrp criarValidacaoCrp(){
        return ValidacaoCrp.builder()
                .id(UUID.randomUUID())
                //esse tem que estar sem o CRP, então acredito que não irá poder ser dessa forma
                .psicologo(PsicologoBuilder.criarPsicologo())
                .crp("08/01234")
                .build();
    }

    public static Page<ValidacaoCrp> criarPageValidacaoCrp(){
        List<ValidacaoCrp> validacaoCrpList = new ArrayList<>();

        for(int i = 0; i < 3; i++){
            validacaoCrpList.add(criarValidacaoCrp());
        }

        return transformarListaEmPage(validacaoCrpList, PageRequest.of(0,10));
    }

    public static Page<ValidacaoCrp> transformarListaEmPage(List<ValidacaoCrp> lista, Pageable pageable){
        int start = (int) pageable.getOffset();
        int end = Math.min((start+pageable.getPageSize()), lista.size());

        List<ValidacaoCrp> subLista = lista.subList(start, end);

        return new PageImpl<>(subLista, pageable, lista.size());
    }
}
