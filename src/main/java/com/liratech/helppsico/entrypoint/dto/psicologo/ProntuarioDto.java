package com.liratech.helppsico.entrypoint.dto.psicologo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProntuarioDto {
    private UUID id;
    private String crp;
    private String cpf;
    private String titulo;
    private String conteudo;
}
