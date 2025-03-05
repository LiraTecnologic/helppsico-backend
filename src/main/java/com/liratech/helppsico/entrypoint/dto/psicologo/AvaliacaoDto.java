package com.liratech.helppsico.entrypoint.dto.psicologo;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AvaliacaoDto {
    private UUID id;
    private PsicologoDto psicologo;
    private Double nota;
    private String comentario;
}
