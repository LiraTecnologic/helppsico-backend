package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Endereco;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.TipoGenero;

import java.time.LocalDate;
import java.util.UUID;

public class PsicologoBuilder {
    public static Psicologo criarPsicologo() {
        return Psicologo.builder()
                .id(UUID.randomUUID())
                .nome("Dr. João Silva")
                .crp("123456")
                .cpf("12345678901")
                .email("joao.silva@example.com")
                .telefone("(11) 98765-4321")
                .dataNascimento(LocalDate.of(1985, 5, 20))
                .senha("Senha@123")
                .genero(TipoGenero.MASCULINO)
                .enderecoAtendimento(EnderecoBuilder.criarEndereco())
                .fotoUrl("https://example.com/foto.jpg")
                .biografia("Psicólogo com 10 anos de experiência em terapia cognitivo-comportamental.")
                .build();
    }
}
