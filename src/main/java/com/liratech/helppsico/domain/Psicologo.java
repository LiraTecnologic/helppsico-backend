package com.liratech.helppsico.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Psicologo {
    private UUID id;
    private String nome;
    private String crp;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private String senha;
    private TipoGenero genero;
    private Endereco enderecoAtendimento;
    private String fotoUrl;
    private String biografia;
}
