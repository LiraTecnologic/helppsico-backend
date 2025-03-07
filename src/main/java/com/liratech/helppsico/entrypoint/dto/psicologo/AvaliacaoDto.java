package com.liratech.helppsico.entrypoint.dto.psicologo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class AvaliacaoDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("psicologo")
    private PsicologoDto psicologo;

    @JsonProperty("nota")
    private Double nota;

    @JsonProperty("comentaripo")
    private String comentario;
}
