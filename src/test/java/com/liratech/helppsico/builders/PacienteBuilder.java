package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.TipoGenero;

import java.time.LocalDate;
import java.util.UUID;

public class PacienteBuilder {

    public static Paciente gerarPaciente() {
        return Paciente.builder()
                .id(UUID.randomUUID())
                .cpf("14584747899")
                .email("emailteste@gmail.com")
                .telefone("44558723651")
                .dataNascimento(LocalDate.now())
                .senha("senhateste!@0")
                .genero(TipoGenero.MASCULINO)
                .endereco(EnderecoBuilder.gerarEndereco())
                .fotoUrl("fotourlteste")
                .build();
    }
}