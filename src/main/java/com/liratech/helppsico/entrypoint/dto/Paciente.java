package com.liratech.helppsico.entrypoint.dto;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record Paciente (
        UUID id,
        String nome,
        String cpf,
        String email,
        String telefone,
        LocalDate dataNascimento,
        String senha,
        TipoGenero genero,
        EnderecoDto endereco,
        String fotoUrl,
        MultipartFile foto
) {
}
