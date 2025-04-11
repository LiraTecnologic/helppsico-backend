package com.liratech.helppsico.validators.json;

import com.liratech.helppsico.domain.TipoGenero;
import com.liratech.helppsico.entrypoint.dto.PacienteDto;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class PacienteValidatorJson {
        public static void validaPacienteJson(ResultActions resultado, PacienteDto esperado) throws Exception{
            resultado.andExpect(jsonPath("$.dado.id").value(esperado.getId().toString()))
                    .andExpect(jsonPath("$.dado.nome").value(esperado.getNome()))
                    .andExpect(jsonPath("$.dado.cpf").value(esperado.getCpf()))
                    .andExpect(jsonPath("$.dado.email").value(esperado.getEmail()))
                    .andExpect(jsonPath("$.dado.telefone").value(esperado.getTelefone()))
                    .andExpect(jsonPath("$.dado.dataNascimento").value(esperado.getDataNascimento().toString()))
                    .andExpect(jsonPath("$.dado.senha").value(esperado.getSenha()))
                    .andExpect(jsonPath("$.dado.genero").value(esperado.getGenero().toString()))
                    .andExpect(jsonPath("$.dado.endereco").value(esperado.getEndereco().toString()))
                    .andExpect(jsonPath("$.dado.fotoUrl").value(esperado.getFotoUrl()))
                    .andExpect(jsonPath("$.erro").doesNotExist());
        }

        public static void validaPageResponse(ResultActions resultado) throws Exception {
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
                        .andExpect(jsonPath("$.dado.content["+i+"].nome").value("João Silva"))
                        .andExpect(jsonPath("$.dado.content["+i+"].cpf").value("12345678901"))
                        .andExpect(jsonPath("$.dado.content["+i+"].email").value("joao.silva@example.com"))
                        .andExpect(jsonPath("$.dado.content["+i+"].telefone").value("(11) 98765-4321"))
                        .andExpect(jsonPath("$.dado.content["+i+"].dataNascimento").value(LocalDate.of(1985, 5, 20)))
                        .andExpect(jsonPath("$.dado.content["+i+"].senha").value("Senha@123"))
                        .andExpect(jsonPath("$.dado.content["+i+"].genero").value(TipoGenero.MASCULINO))
                        .andExpect(jsonPath("$.dado.content["+i+"].endereco.rua").value("Rua Teste"))
                        .andExpect(jsonPath("$.dado.content["+i+"].endereco.numero").value(123))
                        .andExpect(jsonPath("$.dado.content["+i+"].endereco.cep").value("12345678"))
                        .andExpect(jsonPath("$.dado.content["+i+"].endereco.cidade").value("Cidade Teste"))
                        .andExpect(jsonPath("$.dado.content["+i+"].endereco.estado").value("Estado Teste"))
                        .andExpect(jsonPath("$.dado.content["+i+"].fotoUrl").value("https://example.com/foto.jpg"))
                        .andExpect(jsonPath("$.erro").doesNotExist());
            }
        }
}
