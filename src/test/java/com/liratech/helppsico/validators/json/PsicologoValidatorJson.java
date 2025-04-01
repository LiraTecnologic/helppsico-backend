package com.liratech.helppsico.validators.json;

import com.liratech.helppsico.entrypoint.dto.psicologo.PsicologoDto;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class PsicologoValidatorJson{
    public static void validaPsicologoJson(ResultActions resultado, PsicologoDto esperado) throws Exception{
        resultado.andExpect(jsonPath("$.dado.id").value(esperado.getId().toString()))
                .andExpect(jsonPath("$.dado.nome").value(esperado.getNome()))
                .andExpect(jsonPath("$.dado.crp").value(esperado.getCrp()))
                .andExpect(jsonPath("$.dado.cpf").value(esperado.getCpf()))
                .andExpect(jsonPath("$.dado.email").value(esperado.getEmail()))
                .andExpect(jsonPath("$.dado.telefone").value(esperado.getTelefone()))
                .andExpect(jsonPath("$.dado.dataNascimento").value(esperado.getDataNascimento().toString()))
                .andExpect(jsonPath("$.dado.senha").value(esperado.getSenha()))
                .andExpect(jsonPath("$.dado.genero").value(esperado.getGenero().toString()))
                .andExpect(jsonPath("$.dado.endereco").value(esperado.getEnderecoAtendimento().toString()))
                .andExpect(jsonPath("$.dado.fotoUrl").value(esperado.getFotoUrl()))
                .andExpect(jsonPath("$.dado.biografia").value(esperado.getBiografia()))
                .andExpect(jsonPath("$.erro").doesNotExist());
    }
}
