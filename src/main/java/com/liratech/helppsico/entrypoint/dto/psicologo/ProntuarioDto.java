package com.liratech.helppsico.entrypoint.dto.psicologo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
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
    @JsonProperty("crp")
    private String crp;

    @NotBlank(message = "O cpf é obrigatório")
    @JsonProperty("cpf")
    private String cpf;

    @NotBlank(message = "O titulo é obrigatório")
    @JsonProperty("titulo")
    private String titulo;

    @NotBlank(message = "O conteudo é obrigatório")
    @JsonProperty("conteudo")
    private String conteudo;
}
