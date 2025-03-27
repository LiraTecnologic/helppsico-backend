package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.TipoGenero;

import java.time.LocalDate;
import java.util.UUID;

public class PacienteBuilder {

    public static Paciente criarPaciente() {
        return Paciente.builder()
                .id(UUID.randomUUID())
                .nome("João Silva")
                .cpf("123.456.789-00")
                .email("joao.silva@email.com")
                .telefone("(11) 98765-4321")
                .dataNascimento(LocalDate.now())
                .senha("senhaSegura@123")
                .genero(TipoGenero.MASCULINO)
                .endereco(EnderecoBuilder.criarEndereco())
                .fotoUrl("https://example.com/foto-joao.jpg")
                .build();
    }
}