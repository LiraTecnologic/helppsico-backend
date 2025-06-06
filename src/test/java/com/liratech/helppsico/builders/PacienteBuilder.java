package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.TipoGenero;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import com.liratech.helppsico.entrypoint.dto.TipoGeneroDto;
import com.liratech.helppsico.infrastructure.repositories.entities.PacienteEntity;
import com.liratech.helppsico.infrastructure.repositories.entities.TipoGeneroEntity;

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
                .fotoUrl("url-salvo")
                .build();
    }

    public static PacienteDto criarPacienteDto(){
        return PacienteDto.builder()
                .id(UUID.randomUUID())
                .nome("João Silva")
                .cpf("123.456.789-00")
                .email("joao.silva@email.com")
                .telefone("(11) 98765-4321")
                .dataNascimento(LocalDate.now())
                .senha("senhaSegura@123")
                .genero(TipoGenero.MASCULINO)
                .endereco(EnderecoBuilder.criarEnderecoDto())
                .fotoUrl("url-salvo")
                .build();
    }

    public static PacienteEntity criarPacienteEntity() {
        return PacienteEntity.builder()
                .id(UUID.randomUUID())
                .nome("João Silva")
                .cpf("123.456.789-00")
                .email("joao.silva@email.com")
                .telefone("(11) 98765-4321")
                .dataNascimento(LocalDate.now())
                .senha("senhaSegura@123")
                .genero(TipoGenero.MASCULINO)
                .endereco(EnderecoBuilder.criarEnderecoEntity())
                .fotoUrl("url-salvo")
                .build();
    }
}