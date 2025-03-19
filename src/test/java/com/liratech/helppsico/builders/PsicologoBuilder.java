package com.liratech.helppsico.builders;

import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.domain.TipoGenero;

import java.time.LocalDate;
import java.util.UUID;

public class PsicologoBuilder {
    
    public static Psicologo gerarPsicologo() {
        return Psicologo.builder()
                .id(UUID.randomUUID())
                .nome("Psicologo teste")
                .crp("0100000")
                .cpf("12332114763")
                .email("emailteste@gmail.com")
                .telefone("44987415623")
                .dataNascimento(LocalDate.now())
                .senha("senhateste123!")
                .genero(TipoGenero.MASCULINO)
                .enderecoAtendimento(EnderecoBuilder.gerarEndereco())
                .fotoUrl("urltestefoto")
                .biografia("Biografia teste")
                .build();
    }
}
