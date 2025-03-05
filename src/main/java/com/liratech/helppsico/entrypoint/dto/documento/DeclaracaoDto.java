package com.liratech.helppsico.entrypoint.dto.documento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
public class DeclaracaoDto extends DocumentoDto{
    private String motivo;
    private String descricao;
    private String finalidade;

}
