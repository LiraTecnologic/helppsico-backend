package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.ValidacaoCrp;
import org.springframework.data.domain.Page;
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
                .psicologo(PsicologoBuilder.criarPsicologo())
                .crp(PsicologoBuilder.criarPsicologo().getCrp())
                .motivoReprova("Psicologo inativo")
                .build();
    }

    public static ValidacaoCrpDto criarValidacaoCrpDto(){
        return ValidacaoCrpDto.builder()
                .id(UUID.randomUUID())
                .psicologo(PsicologoBuilder.criarPsicologoDto())
                .crp(PsicologoBuilder.criarPsicologoDto())
                .motivoReprova("Psicologo inativo")
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
