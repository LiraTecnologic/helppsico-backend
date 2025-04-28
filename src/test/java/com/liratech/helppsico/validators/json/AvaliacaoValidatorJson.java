package com.liratech.helppsico.validators.json;

import com.liratech.helppsico.domain.TipoGenero;
import com.liratech.helppsico.entrypoint.dto.psicologo.AvaliacaoDto;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class AvaliacaoValidatorJson {
    public static void validaAvaliacaoJson(ResultActions resultado, AvaliacaoDto esperado) throws Exception{
        resultado.andExpect(jsonPath("$.dado.id").value(esperado.getId().toString()))
                .andExpect(jsonPath("$.dado.psicologo").value(esperado.getPsicologo().toString()))
                .andExpect(jsonPath("$.dado.paciente").value(esperado.getPaciente().toString()))
                .andExpect(jsonPath("$.dado.nota").value(esperado.getNota()))
                .andExpect(jsonPath("$.dado.comentario").value(esperado.getComentario()))
                .andExpect(jsonPath("$.erro").doesNotExist());
    }

    public static void validaPageResponse(ResultActions resultado) throws Exception{
        resultado.andExpect(jsonPath("$.dado.length()").value(3))

                .andExpect(jsonPath("$.dado.pageable.pageNumber").value(0))
                .andExpect(jsonPath("$.dado.pageable.pageSize").value(10))

                .andExpect(jsonPath("$.dado.last").value(true))
                .andExpect(jsonPath("$.dado.first").value(true))
                .andExpect(jsonPath("$.dado.totalPages").value(1))
                .andExpect(jsonPath("$.dado.empty").value(false))

                .andExpect(jsonPath("$.dado.sort.sorted").value(true))
                .andExpect(jsonPath("$.dado.sort.unsorted").value(false))
                .andExpect(jsonPath("$.dado.sort.empty").value(false))
                .andExpect(jsonPath("$.dado.sort.orders[0].property").value("nome"))
                .andExpect(jsonPath("$.dado.sort.orders[0].direction").value("ASC"));

        for (int i = 0; i < 3; i++){
            resultado.andExpect(jsonPath("$.dado.content["+i+"].id").exists())
                    //Verificação do Psicologo
                    .andExpect(jsonPath("$.dado.content["+i+"].psicologo.nome").value("João Silva"))
                    .andExpect(jsonPath("$.dado.content["+i+"].psicologo.crp").value("123456"))
                    .andExpect(jsonPath("$.dado.content["+i+"].psicologo.cpf").value("12345678901"))
                    .andExpect(jsonPath("$.dado.content["+i+"].psicologo.email").value("joao.silva@example.com"))
                    .andExpect(jsonPath("$.dado.content["+i+"].psicologo.telefone").value("(11) 98765-4321"))
                    .andExpect(jsonPath("$.dado.content["+i+"].psicologo.dataNascimento").value(LocalDate.of(1985, 5, 20)))
                    .andExpect(jsonPath("$.dado.content["+i+"].psicologo.senha").value("Senha@123"))
                    .andExpect(jsonPath("$.dado.content["+i+"].psicologo.genero").value(TipoGenero.MASCULINO))
                    .andExpect(jsonPath("$.dado.content["+i+"].psicologo.endereco.rua").value("Rua Teste"))
                    .andExpect(jsonPath("$.dado.content["+i+"].psicologo.endereco.numero").value(123))
                    .andExpect(jsonPath("$.dado.content["+i+"].psicologo.endereco.cep").value("12345678"))
                    .andExpect(jsonPath("$.dado.content["+i+"].psicologo.endereco.cidade").value("Cidade Teste"))
                    .andExpect(jsonPath("$.dado.content["+i+"].psicologo.endereco.estado").value("Estado Teste"))
                    .andExpect(jsonPath("$.dado.content["+i+"].psicologo.fotoUrl").value("https://example.com/foto.jpg"))
                    .andExpect(jsonPath("$.dado.content["+i+"].psicologo.biografia").value("Psicólogo com 10 anos de experiência em terapia cognitivo-comportamental."))

                    //Verificação do paciente
                    .andExpect(jsonPath("$.dado.content["+i+"].paciente.nome").value("João Silva"))
                    .andExpect(jsonPath("$.dado.content["+i+"].paciente.cpf").value("12345678901"))
                    .andExpect(jsonPath("$.dado.content["+i+"].paciente.email").value("joao.silva@example.com"))
                    .andExpect(jsonPath("$.dado.content["+i+"].paciente.telefone").value("(11) 98765-4321"))
                    .andExpect(jsonPath("$.dado.content["+i+"].paciente.dataNascimento").value(LocalDate.of(1985, 5, 20)))
                    .andExpect(jsonPath("$.dado.content["+i+"].paciente.senha").value("Senha@123"))
                    .andExpect(jsonPath("$.dado.content["+i+"].paciente.genero").value(TipoGenero.MASCULINO))
                    .andExpect(jsonPath("$.dado.content["+i+"].paciente.endereco.rua").value("Rua Teste"))
                    .andExpect(jsonPath("$.dado.content["+i+"].paciente.endereco.numero").value(123))
                    .andExpect(jsonPath("$.dado.content["+i+"].paciente.endereco.cep").value("12345678"))
                    .andExpect(jsonPath("$.dado.content["+i+"].paciente.endereco.cidade").value("Cidade Teste"))
                    .andExpect(jsonPath("$.dado.content["+i+"].paciente.endereco.estado").value("Estado Teste"))
                    .andExpect(jsonPath("$.dado.content["+i+"].paciente.fotoUrl").value("https://example.com/foto.jpg"))

                    .andExpect(jsonPath("$.dado.nota").value(4.5))
                    .andExpect(jsonPath("$.dado.comentario").value("Bom psicologo"))
                    .andExpect(jsonPath("$.erro").doesNotExist());
        }
    }
}
