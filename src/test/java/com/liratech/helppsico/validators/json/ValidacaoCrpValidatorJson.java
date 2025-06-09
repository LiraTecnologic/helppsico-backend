package com.liratech.helppsico.validators.json;

import com.liratech.helppsico.entrypoint.dto.ValidacaoCrpDto;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ValidacaoCrpValidatorJson {
    public static void verificaValidacaoJson(ResultActions resultado, ValidacaoCrpDto esperado) throws Exception{
        resultado.andExpect(jsonPath("$.dado.id").value(esperado.getId().toString()))
                .andExpect(jsonPath("$.dado.psicologo").value(esperado.getPsicologo().toString()))
                .andExpect(jsonPath("$.dado.crp").value(esperado.getPsicologo().toString()))
                .andExpect(jsonPath("$.dado.motivoReprova").value(esperado.getPsicologo().toString()))
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
                .andExpect(jsonPath("$.erro").doesNotExist());
    }
}
