package com.liratech.helppsico.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class PacienteDto {

    @JsonProperty("id")
    private UUID id;

    @NotBlank(message = "O nome é obrigatório")
    @JsonProperty("nome")
    private String nome;

    @NotBlank(message = "O cpf é obrigatório")
    @JsonProperty("cpf")
    private String cpf;

    @NotBlank(message = "O email é obrigatório")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "O email é obrigatório")
    @JsonProperty("telefone")
    private String telefone;

    @Past
    @JsonProperty("dataNascimento")
    private LocalDate dataNascimento;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&]).+$",
            message = "A senha deve conter pelo menos uma letra, um número e um caractere especial.")
    @JsonProperty("senha")
    private String senha;

    @NotBlank(message = "O gênero é obrigatório")
    @JsonProperty("genero")
    @Enumerated(EnumType.STRING)
    private TipoGeneroDto genero;

    @JsonProperty("endereco")
    private EnderecoDto endereco;

    @NotBlank(message = "A url da foto é obrigatória")
    @JsonProperty("fotoUrl")
    private String fotoUrl;

    @NotBlank(message = "A foto é obrigatória")
    @JsonProperty("foto")
    private MultipartFile foto;
}
