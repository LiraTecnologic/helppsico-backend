package com.liratech.helppsico.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ValidacaoCrp {
    private UUID id;
    private Psicologo psicologo;
    private String crp;
    private String motivoReprova;
}
