package com.liratech.helppsico.entrypoint.dto.psicologo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import com.liratech.helppsico.entrypoint.dto.TipoGeneroDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "O nome é obrigatório")
    @JsonProperty("nome")
    private String nome;

    @NotBlank(message = "O crp é obrigatório")
    @Pattern(
            regexp = "^[A-Za-z0-9]{6,10}$",
            message = "O CRP deve conter entre 6 e 10 caracteres alfanuméricos, sem formatação"
    )
    @JsonProperty("crp")
    private String crp;

    @NotBlank(message = "O cpf é obrigatório")
    @Pattern(
            regexp = "^\\d{11}$",
            message = "O CPF deve conter exatamente 11 dígitos numéricos, sem formatação"
    )
    @JsonProperty("cpf")
    private String cpf;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    @JsonProperty("telefone")
    private String telefone;

    @NotNull(message = "A nota é obrigatória")
    @Past(message = "A data obrigatóriamente tem que ser do passado")
    @JsonProperty("dataNascimento")
    private LocalDate dataNascimento;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&]).+$",
            message = "A senha deve conter pelo menos uma letra, um número e um caractere especial.")
    @JsonProperty("senha")
    private String senha;

    @NotNull(message = "O gênero é obrigatório")
    @JsonProperty("genero")
    @Enumerated(EnumType.STRING)
    private TipoGeneroDto genero;

    @NotNull(message = "O endereço é obrigatório")
    @JsonProperty("endereco")
    private EnderecoDto enderecoAtendimento;

    @NotBlank(message = "A url da foto é obrigatória")
    @JsonProperty("fotoUrl")
    private String fotoUrl;

    @JsonProperty("foto")
    private MultipartFile foto;

    @NotBlank(message = "A biografia é obrigatória")
    @JsonProperty("biografia")
    private String biografia;
}