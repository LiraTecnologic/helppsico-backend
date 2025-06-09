package com.liratech.helppsico.validators.json;

import com.liratech.helppsico.entrypoint.dto.FotoDto;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class FotoValidatorJson {
    public static void validaFotoPacienteJson(ResultActions resultActions, FotoDto esperado) throws Exception {
        resultActions.andExpect(jsonPath("$.dado.fotoUrl").value(esperado.getFotoUrl()))
                .andExpect(jsonPath("$.dado.paciente.id").value(esperado.getPaciente().getId().toString()))
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

    public static void validaFotoPsicologoJson(ResultActions resultActions, FotoDto esperado) throws Exception{
        resultActions
                .andExpect(jsonPath("$.dado.fotoUrl").value(esperado.getFotoUrl()))
                .andExpect(jsonPath("$.dado.psicologo.id").value(esperado.getPsicologo().getId().toString()))
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
                .andExpect(jsonPath("$.erro").doesNotExist());
    }
}
