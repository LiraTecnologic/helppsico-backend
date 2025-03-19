package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.TipoGenero;

import java.time.LocalDate;
import java.util.UUID;

public class PacienteDtoBuilder  {
    public static PacienteDto criarPacienteDto(){
        return PacienteDto.builder()
                .id(UUID.randomUUID())
                .nome("João Silva")
                .cpf("123.456.789-00")
                .email("joao.silva@email.com")
                .telefone("(11) 98765-4321")
                .dataNascimento(LocalDate.now())
                .senha("senhaSegura@123")
                .genero(TipoGeneroDto.MASCULINO)
                .endereco(EnderecoDtoBuilder.criarEnderecoDto())
                .fotoUrl("https://example.com/foto-joao.jpg")
                .build();
    }
}
