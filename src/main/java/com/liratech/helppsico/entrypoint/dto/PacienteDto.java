package com.liratech.helppsico.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("email")
    private String email;

    @JsonProperty("telefone")
    private String telefone;

    @JsonProperty("dataNascimento")
    private LocalDate dataNascimento;

    @JsonProperty("senha")
    private String senha;

    @JsonProperty("genero")
    private TipoGeneroDto genero;

    @JsonProperty("endereco")
    private EnderecoDto endereco;

    @JsonProperty("fotoUrl")
    private String fotoUrl;

    @JsonProperty("foto")
    private MultipartFile foto;
}
