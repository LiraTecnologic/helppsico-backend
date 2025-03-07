package com.liratech.helppsico.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import java.util.UUID;

@AllArgsConstructor
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
    @JsonProperty("cep")
    private String cep;

    @NotBlank(message = "A cidade é obrigatória")
    @JsonProperty("cidade")
    private String cidade;

    @NotBlank(message = "O estado é obrigatório")
    @JsonProperty("estado")
    private String estado;
}
