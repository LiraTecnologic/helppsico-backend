package com.liratech.helppsico.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
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
    private BigDecimal nota;
    private TipoGenero genero;
    private Endereco enderedoAtendimento;
    private String fotoUrl;
}
