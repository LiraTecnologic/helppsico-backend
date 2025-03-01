package com.liratech.helppsico.domain.documento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Declaracao {
    private String motivo;
    private String descricao;
    private String finalidade;
}
