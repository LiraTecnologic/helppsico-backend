package com.liratech.helppsico.entrypoint.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PacienteDto {
    private UUID i;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private String senha;
    private TipoGeneroDto genero;
    private EnderecoDto endereco;
    private String fotoUrl;
    private MultipartFile foto;
}
