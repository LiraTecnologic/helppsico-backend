package com.liratech.helppsico.entrypoint.dto.psicologo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class PsicologoDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("crp")
    private String crp;

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

    @JsonProperty("nota")
    private Double nota;

    @JsonProperty("fotoUrl")
    private EnderecoDto fotoUrl;

    @JsonProperty("foto")
    private MultipartFile foto;
}