package com.liratech.helppsico.entrypoint.dto.psicologo;

import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PsicologoDto {
    private UUID id;
    private String nome;
    private String crp;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private String senha;
    private Double nota;
    private EnderecoDto fotoUrl;
    private MultipartFile foto;
}