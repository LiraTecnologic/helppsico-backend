package com.liratech.helppsico.entrypoint.dto.psicologo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ProntuarioDto {
    @JsonProperty("id")
    private UUID id;

    @NotBlank(message = "O crp é obrigatório")
    @Pattern(
            regexp = "^[A-Za-z0-9]{6,10}$",
            message = "O crp deve conter entre 6 e 10 caracteres alfanuméricos, sem formatação"
    )
    @JsonProperty("crp")
    private String crp;

    @NotBlank(message = "O cpf é obrigatório")
    @Pattern(
            regexp = "^\\d{11}$",
            message = "O cpf deve conter exatamente 11 dígitos numéricos, sem formatação"
    )
    @JsonProperty("cpf")
    private String cpf;

    @NotBlank(message = "O titulo é obrigatório")
    @JsonProperty("titulo")
    private String titulo;

    @NotBlank(message = "O conteudo é obrigatório")
    @JsonProperty("conteudo")
    private String conteudo;
}
