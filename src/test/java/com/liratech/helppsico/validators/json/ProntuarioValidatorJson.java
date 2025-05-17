package com.liratech.helppsico.validators.json;

import com.liratech.helppsico.domain.TipoGenero;
import com.liratech.helppsico.entrypoint.dto.psicologo.ProntuarioDto;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ProntuarioValidatorJson {
    public static void validaProntuarioJson(ResultActions resultActions, ProntuarioDto esperado) throws Exception {
        resultActions.andExpect(jsonPath("$.dado.id").value(esperado.getId().toString()))
                .andExpect(jsonPath("$.dado.conteudo").value(esperado.getConteudo()))
                .andExpect(jsonPath("$.dado.titulo").value(esperado.getTitulo()))
                .andExpect(jsonPath("$.dado.psicologo.id").value(esperado.getId().toString()))
                .andExpect(jsonPath("$.dado.psicologo.nome").value(esperado.getPsicologo().getNome()))
                .andExpect(jsonPath("$.dado.psicologo.crp").value(esperado.getPsicologo().getCrp()))
                .andExpect(jsonPath("$.dado.psicologo.cpf").value(esperado.getPsicologo().getCpf()))
                .andExpect(jsonPath("$.dado.psicologo.email").value(esperado.getPsicologo().getEmail()))
                .andExpect(jsonPath("$.dado.psicologo.telefone").value(esperado.getPsicologo().getTelefone()))
                .andExpect(jsonPath("$.dado.psicologo.dataNascimento").value(esperado.getPsicologo().getDataNascimento().toString()))
                .andExpect(jsonPath("$.dado.psicologo.senha").value(esperado.getPsicologo().getSenha()))
                .andExpect(jsonPath("$.dado.psicologo.genero").value(esperado.getPsicologo().getGenero().toString()))
                .andExpect(jsonPath("$.dado.psicologo.endereco").value(esperado.getPsicologo().getEnderecoAtendimento().toString()))
                .andExpect(jsonPath("$.dado.psicologo.fotoUrl").value(esperado.getPsicologo().getFotoUrl()))
                .andExpect(jsonPath("$.dado.psicologo.biografia").value(esperado.getPsicologo().getBiografia()))
                .andExpect(jsonPath("$.dado.paciente.id").value(esperado.getId().toString()))
                .andExpect(jsonPath("$.dado.paciente.nome").value(esperado.getPaciente().getNome()))
                .andExpect(jsonPath("$.dado.paciente.cpf").value(esperado.getPaciente().getCpf()))
                .andExpect(jsonPath("$.dado.paciente.email").value(esperado.getPaciente().getEmail()))
                .andExpect(jsonPath("$.dado.paciente.telefone").value(esperado.getPaciente().getTelefone()))
                .andExpect(jsonPath("$.dado.paciente.dataNascimento").value(esperado.getPaciente().getDataNascimento().toString()))
                .andExpect(jsonPath("$.dado.paciente.senha").value(esperado.getPaciente().getSenha()))
                .andExpect(jsonPath("$.dado.paciente.genero").value(esperado.getPaciente().getGenero().toString()))
                .andExpect(jsonPath("$.dado.paciente.endereco").value(esperado.getPaciente().getEndereco().toString()))
                .andExpect(jsonPath("$.dado.paciente.fotoUrl").value(esperado.getPaciente().getFotoUrl()))
                .andExpect(jsonPath("$.erro").doesNotExist());
    }

    public static void validaProntuariosJson(ResultActions resultado) throws Exception {
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
            resultado.andExpect(jsonPath("$.dado.conteudo").value("teste"))
                    .andExpect(jsonPath("$.dado.titulo").value("Teste"))
                    .andExpect(jsonPath("$.dado.content["+i+"].nome").value("João Silva"))
                    .andExpect(jsonPath("$.dado.content["+i+"].crp").value("123456"))
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
                    .andExpect(jsonPath("$.dado.content["+i+"].biografia").value("Psicólogo com 10 anos de experiência em terapia cognitivo-comportamental."))
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
