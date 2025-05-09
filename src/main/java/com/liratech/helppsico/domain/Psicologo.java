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
    private StatusPsicologo statusPsicologo;

    public void alterarDados(Psicologo psicologo) {
        this.nome = psicologo.getNome();
        this.email = psicologo.getEmail();
        this.telefone = psicologo.getTelefone();
        this.dataNascimento = psicologo.getDataNascimento();
        this.genero = psicologo.getGenero();
        this.enderecoAtendimento = psicologo.getEnderecoAtendimento();
        this.fotoUrl = psicologo.getFotoUrl();
        this.biografia = psicologo.getBiografia();
        this.statusPsicologo = psicologo.getStatusPsicologo();
    }
}
