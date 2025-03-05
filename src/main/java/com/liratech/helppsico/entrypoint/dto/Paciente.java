package com.liratech.helppsico.entrypoint.dto;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public class Paciente {
    private UUID i;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private String senha;
    private TipoGenero genero;
    private EnderecoDto endereco;
    private String fotoUrl;
    private MultipartFile foto;
}
