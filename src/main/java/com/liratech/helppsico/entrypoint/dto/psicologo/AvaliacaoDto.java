package com.liratech.helppsico.entrypoint.dto.psicologo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class AvaliacaoDto {

    @JsonProperty("id")
    private UUID id;

    @NotNull(message = "O psicologo é obrigatório")
    @JsonProperty("psicologo")
    private PsicologoDto psicologo;

    @NotNull(message = "A nota é obrigatória")
    @PositiveOrZero(message = "O número precisa ser maior ou igual a zero")
    @JsonProperty("nota")
    private Double nota;

    @NotBlank(message = "O comentário é obrigatório")
    @JsonProperty("comentario")
    private String comentario;
}
