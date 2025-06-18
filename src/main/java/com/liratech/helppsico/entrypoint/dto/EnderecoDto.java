package com.liratech.helppsico.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnderecoDto{

    @JsonProperty("id")
    private UUID id;

    @NotBlank(message = "A rua é obrigatória")
    @JsonProperty("rua")
    private String rua;

    @NotNull(message = "O número é obrigatório")
    @PositiveOrZero(message = "O número precisa ser maior ou igual a zero")
    @JsonProperty("numero")
    private Integer numero;


    @NotBlank(message = "O cep é obrigatório")
    @Pattern(
            regexp = "^\\d{8}$",
            message = "O cep deve conter exatamente 8 dígitos numéricos, sem formatação"
    )
    @JsonProperty("cep")
    private String cep;

    @NotBlank(message = "A cidade é obrigatória")
    @JsonProperty("cidade")
    private String cidade;

    @NotBlank(message = "O estado é obrigatório")
    @JsonProperty("estado")
    private String estado;
}
