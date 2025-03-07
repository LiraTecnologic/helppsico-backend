package com.liratech.helppsico.domain;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Endereco {
    private UUID id;
    private String rua;
    private Integer numero;
    private String cep;
    private String cidade;
    private String estado;
}
