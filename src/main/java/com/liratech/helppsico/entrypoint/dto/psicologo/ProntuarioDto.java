package com.liratech.helppsico.entrypoint.dto.psicologo;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("crp")
    private String crp;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("conteudo")
    private String conteudo;
}
